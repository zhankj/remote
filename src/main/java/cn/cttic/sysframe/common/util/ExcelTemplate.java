package cn.cttic.sysframe.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 操作Excel表格 工具
 */
public class ExcelTemplate {

	private static final Log log = LogFactory.getLog(ExcelTemplate.class);

	private static final short CAMBRIDGE_BLUR = 11;// 浅蓝色

	private static final short LIGIHT_BLUE = 12;// 浅灰色

	private static final short ZERO = 0;

	private static final short ONE = 1;

	private HSSFWorkbook hSSFWorkbook;

	private HSSFPalette palette;// 调色板

	// 正在编辑的sheet页面
	private HSSFSheet openedSheet;

	// sheet页的集合
	private Map<String, HSSFSheet> sheets = new HashMap<String, HSSFSheet>();

	// 编辑的行数
	private int rowNum = 0;

	// 统计表把这个参数配置成 true
	private boolean isValMatchType = true;

	private boolean showXh = true;

	private HSSFCellStyle headStyle; // 首行样式

	private HSSFCellStyle oddStyle; // 奇数行样式

	private HSSFCellStyle evenStyle; // 偶数行样式

	private HSSFFont headFont; // 首行字体

	private HSSFFont dataFont; // 数据部分字体

	private static final int STYLE_HEAD = 0; // 首行

	private static final int STYLE_ODD = 1; // 奇数行

	private static final int STYLE_EVEN = 2; // 偶数行

	private static final int STYLE_TOTAL = 3; // 合计行

	private HSSFPatriarch patriarch; // 插入图片patriarch

	/**
	 * add by yangrui
	 */
	private ColumnData columnData;

	private List<?> gridList;

	private String fileName;

	private Map<String, String> typeCodeField;

	private Map<String, String> dateField;

	private Map<String, FieldFormatter> customField;

	/**
	 * 构造函数、实例化HSSFWorkBook
	 */
	public ExcelTemplate() {
		hSSFWorkbook = new HSSFWorkbook();
		palette = hSSFWorkbook.getCustomPalette();
		addHSSFColor();
		headFont = getDefHeadFont();
		dataFont = getDefDataFont();
		headStyle = getDefHeadStyle();
		evenStyle = getDefEvenStyle();
		oddStyle = getDefOddStyle();
		typeCodeField = new HashMap<String, String>();
		dateField = new HashMap<String, String>();
		customField = new HashMap<String, FieldFormatter>();
	}

	public final String PER_2C = "${0.00%}";

	private String titleName = null;

	/**
	 * 调颜色
	 */
	private void addHSSFColor() {
		palette.setColorAtIndex(CAMBRIDGE_BLUR, (byte) 141, (byte) 180, (byte) 227);
		palette.setColorAtIndex(LIGIHT_BLUE, (byte) 197, (byte) 217, (byte) 241);
	}

	/**
	 * 是否显示序号
	 */
	public void isShowXh(boolean isShow) {
		showXh = isShow;
	}

	/**
	 * 创建一个sheet页 并切换到sheet此页
	 */
	public ExcelTemplate createNewSheet(String sheetName) {
		openedSheet = hSSFWorkbook.createSheet(sheetName);

		// 每个单元格的宽度为20
		openedSheet.setDefaultColumnWidth((short) 20);

		sheets.put(sheetName, openedSheet);

		setRowNum(0);
		clearTitleListGridList();
		return this;
	}

	/**
	 * 通过sheet页的名字
	 */
	public void changeSheet(String sheetName) {
		openedSheet = sheets.get(sheetName);
	}

	/**
	 * 光标移到下一行
	 */
	public void nextRow() {
		rowNum = rowNum + 1;
	}

	/**
	 * 创建一行
	 */
	private void createRow(Object[] values, int rowType, HSSFCellStyle useStyle) {
		switch (rowType) {
			case STYLE_HEAD:
				useStyle = this.headStyle;
				break;
			case STYLE_ODD:
				useStyle = this.oddStyle;
				break;
			case STYLE_EVEN:
				useStyle = this.evenStyle;
				break;
			case STYLE_TOTAL:
				useStyle = this.headStyle;
				break;
		}
		HSSFRow row = openedSheet.createRow((short) rowNum);
		int j = 0;
		if (showXh) {
			HSSFCell cell = row.createCell(ZERO);
			if (rowType == this.STYLE_HEAD) {
				cell.setCellValue("序号");
			} else {
				cell.setCellValue(rowNum);
			}
			cell.setCellStyle(useStyle);
			j = 1;
			setColumnWidth(0, 2000);
		}

		for (int i = 0; i < values.length; i++, j++) {
			HSSFCell cell = row.createCell((short) j);
			cell.setCellStyle(useStyle);
			Object celVal = values[i] == null ? "" : values[i];
			if (celVal instanceof Double) {
				cell.setCellValue((Double) celVal);
				HSSFCellStyle t = hSSFWorkbook.createCellStyle();
				t.cloneStyleFrom(useStyle);
				t.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				t.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
				cell.setCellStyle(t);
			} else if (celVal instanceof BigDecimal) {
				cell.setCellValue(celVal.toString());
				HSSFCellStyle t = hSSFWorkbook.createCellStyle();
				t.cloneStyleFrom(useStyle);
				t.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				t.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
				cell.setCellStyle(t);
			} else if (celVal instanceof Integer) {
				cell.setCellValue((Integer) celVal);
				HSSFCellStyle t = hSSFWorkbook.createCellStyle();
				t.cloneStyleFrom(useStyle);
				t.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				cell.setCellStyle(t);
			} else if (celVal instanceof Long) {
				if (String.valueOf(celVal).length() > 11) {
					cell.setCellValue(String.valueOf(celVal));
				} else {
					cell.setCellValue((Long) celVal);
				}
				HSSFCellStyle t = hSSFWorkbook.createCellStyle();
				t.cloneStyleFrom(useStyle);
				t.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				cell.setCellStyle(t);
			} else if (celVal instanceof Date || celVal instanceof Timestamp) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cell.setCellValue(simpleDateFormat.format((Date) celVal));
			} else if (celVal instanceof String) {
				String celValue = String.valueOf(celVal);
				if (celValue.endsWith(PER_2C)) {
					if (celValue.length() == PER_2C.length()) {
						cell.setCellValue(new Double("0.00"));
					} else {
						cell.setCellValue(Double.parseDouble(celValue.substring(0, celValue.length() - PER_2C.length())) / 100);
					}
					HSSFCellStyle t = hSSFWorkbook.createCellStyle();
					t.cloneStyleFrom(useStyle);
					t.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					t.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
					cell.setCellStyle(t);
				} else {
					cell.setCellValue(celValue);
				}
			} else {
				cell.setCellValue(celVal.toString());
			}
		}
		nextRow();
	}

	/**
	 * 用自己的风格创建一行TODO
	 */
	public void createRow(String[] values, HSSFCellStyle useStyle) {
		createRow(values, 100, useStyle);
	}

	/**
	 * 创建题头行
	 */
	public void createHeadRow(String[] titles) {
		createRow(titles, STYLE_HEAD, null);
	}

	public void createHeadRow(List<String> titles) {
		this.createHeadRow(titles.toArray(new String[] {}));
	}

	/**
	 * 创建奇数行
	 */
	public void createOddRow(Object[] values) {
		createRow(values, STYLE_ODD, null);
	}

	public void createOddRow(List<Object> values) {
		this.createOddRow(values.toArray(new Object[] {}));
	}

	/**
	 * 创建偶数行
	 */
	public void createEvenRow(Object[] values) {
		createRow(values, STYLE_EVEN, null);
	}

	public void createEvenRow(List<Object> values) {
		this.createEvenRow(values.toArray(new Object[] {}));
	}

	/**
	 * 创建合计行
	 */
	public void createTotalRow(Object[] values) {
		createRow(values, STYLE_HEAD, null);
	}

	/**
	 * 校验能否转换成double
	 */
	private boolean isDouble(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		if (isValMatchType) {
			return str.matches("(^0|(^[1-9][0-9]*))(\\.[0-9]*)?$");
		} else {
			return false;
		}
	}

	/**
	 * 设置列宽
	 */
	public void setColWidth(int width) {
		openedSheet.setDefaultColumnWidth((short) width);

	}

	/**
	 * 设置列宽
	 * 
	 * @param colNum 列数
	 * @param width 宽度 （最大65280 单位像素）
	 */
	public void setColumnWidth(int colNum, int width) {
		openedSheet.setColumnWidth((short) colNum, (short) width);
	}

	/**
	 * 默认表头字体
	 */
	private HSSFFont getDefHeadFont() {
		HSSFFont font = hSSFWorkbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.BLACK.index);
		font.setItalic(false);
		font.setFontHeight((short) 220);
		font.setFontName("宋体");
		return font;
	}

	/**
	 * 默认内容部分字体
	 */
	private HSSFFont getDefDataFont() {
		HSSFFont font = hSSFWorkbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setItalic(false);
		font.setFontHeight((short) 190);
		font.setFontName("宋体");
		return font;
	}

	/**
	 * 默认表头行样式
	 */
	public HSSFCellStyle getDefHeadStyle() {
		HSSFCellStyle cellStyle = hSSFWorkbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		cellStyle.setBorderTop(ONE);
		cellStyle.setBorderBottom(ONE);
		cellStyle.setBorderLeft(ONE);
		cellStyle.setBorderRight(ONE);
		cellStyle.setFont(headFont);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(this.CAMBRIDGE_BLUR);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	/**
	 * 默认表头行样式
	 */
	public HSSFCellStyle getMyHeadStyle() {
		HSSFCellStyle cellStyle = hSSFWorkbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		cellStyle.setBorderTop(ONE);
		cellStyle.setBorderBottom(ONE);
		cellStyle.setBorderLeft(ONE);
		cellStyle.setBorderRight(ONE);
		cellStyle.setFont(headFont);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor((short) 01);
		return cellStyle;
	}

	/**
	 * 默认奇数行样式
	 */
	private HSSFCellStyle getDefOddStyle() {
		HSSFCellStyle cellStyle = hSSFWorkbook.createCellStyle();
		cellStyle.setBorderTop(ONE);
		cellStyle.setBorderBottom(ONE);
		cellStyle.setBorderLeft(ONE);
		cellStyle.setBorderRight(ONE);
		cellStyle.setFont(dataFont);
		cellStyle.setFillForegroundColor(this.LIGIHT_BLUE);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	/**
	 * 默认偶数行样式
	 */
	private HSSFCellStyle getDefEvenStyle() {
		HSSFCellStyle cellStyle = hSSFWorkbook.createCellStyle();
		cellStyle.setBorderTop(ONE);
		cellStyle.setBorderBottom(ONE);
		cellStyle.setBorderLeft(ONE);
		cellStyle.setBorderRight(ONE);
		cellStyle.setFont(dataFont);
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	
	private HSSFCellStyle getDefTitleStyle() {
		HSSFCellStyle cellStyle = hSSFWorkbook.createCellStyle();
		cellStyle.setBorderTop(ONE);
		cellStyle.setBorderBottom(ONE);
		cellStyle.setBorderLeft(ONE);
		cellStyle.setBorderRight(ONE);
		cellStyle.setFont(dataFont);
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}
	/**
	 * 功能描述：合并多个单元格
	 * 
	 * @param firstRow 要合并单元格的起始行
	 * @param lastRow 要合并单元格至哪一行
	 * @param firstColumn 要合并单元格的起始列
	 * @param lastCloumn 要合并单元格至哪一列
	 */
	public void mergingCells(int firstRow, int lastRow, int firstColumn, int lastCloumn) {
		openedSheet.addMergedRegion(new Region(firstRow, // first row (0-based)
		        (short) firstColumn, // first column (0-based)
		        lastRow, // last row (0-based)
		        (short) lastCloumn // last column (0-based)
		        ));

	}

	/**
	 * 功能描述：纵向 合并内容相同的单元格
	 * 
	 * @param colsNum 要合并单元格的列
	 */
	public void mergingSameCol(int colsNum, int... beginAt) {
		String cellText = "";
		int begin = 1;
		if (rowNum > 0) {
			int g = 0;
			if (beginAt != null && beginAt.length > 0 && beginAt[0] < rowNum) {
				g = beginAt[0];
				rowNum = beginAt[1];
			}

			for (int i = g; i < rowNum; i++) {
				HSSFRow row = openedSheet.getRow(i);
				HSSFCell cell = row.getCell(colsNum);
				cell.getCellStyle().setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				String nowCellText = this.getRightCellValue(cell);
				if (cellText.equals(nowCellText) && !"".equals(nowCellText)) {
					begin++;
					cell.setCellValue(nowCellText);
				} else {
					if (begin != 1) {
						mergingCells(i - begin, i - 1, colsNum, colsNum);
					}
					cellText = nowCellText;
					begin = 1;
				}
			}
			if (begin != 1) {
				mergingCells(rowNum - begin, rowNum - 1, colsNum, colsNum);
			}
		}
	}

	/**
	 * 功能描述：横向 合并内容相同的单元格
	 * 
	 * @param colsNum 要合并单元格的行
	 */
	public void mergingSameRow(int rNumSta, int... beginAt) {
		String cellText = "";
		HSSFRow row = openedSheet.getRow(rNumSta);
		int begin = 1;
		if (row != null) {
			int g = 0;
			int colNum = row.getLastCellNum();
			if (beginAt != null && beginAt.length > 0 && beginAt[0] < rowNum) {
				g = beginAt[0];
				colNum = beginAt[1];
			}

			for (int i = g; i < colNum; i++) {
				HSSFCell cell = row.getCell(i);
				String nowCellText = this.getRightCellValue(cell);
				if (cellText.equals(nowCellText)) {
					begin++;
				} else {
					if (begin != 1) {
						mergingCells(rNumSta, rNumSta, i - (begin), i - 1);
					}
					cellText = nowCellText;
					begin = 1;
				}
			}
			if (begin != 1) {
				mergingCells(rNumSta, rNumSta, colNum - (begin), colNum - 1);
			}
		}
	}

	/**
	 * 功能描述：得到单元格内正确的值
	 * 
	 * @param HSSFCell cell单元格
	 */
	private String getRightCellValue(HSSFCell cell) {
		String returnVal = "";
		int cellType = cell.getCellType();
		switch (cellType) {
			case HSSFCell.CELL_TYPE_BOOLEAN:
				returnVal = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				returnVal = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				returnVal = String.valueOf(cell.getErrorCellValue());
				break;
			default:
				returnVal = cell.getStringCellValue();
				break;
		}
		return returnVal;
	}

	/**
	 * 功能描述：删除一列
	 * 
	 * @param colsNum 要删除的列
	 */
	public void deleteCol(int colsNum) {
		for (int i = 0; i < rowNum; i++) {
			HSSFRow row = openedSheet.getRow(i);
			HSSFCell cell = row.getCell(colsNum);
			row.removeCell(cell);
		}
	}

	/**
	 * 自定义奇数行样式 参数为null的时候返回默认奇数行样式
	 */
	public void writeExcel(HttpServletResponse response) {
		String filename = "";
		OutputStream os = null;
		try {
			if (null == fileName) {
				fileName = "module";
			}
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			filename = encodeFilename(fileName, request) + ".xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + filename);

			// 弹出提示框
			os = response.getOutputStream();
			hSSFWorkbook.write(os);
		}
		catch (Exception e) {
			log.error("输出Excel出错", e);
		}
		finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 */
	public static String encodeFilename(String filename, HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa
		 * Toolbar) 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717
		 * Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE") || agent.contains("Chrome") || agent.contains("Safari"))) {
				String newFileName = URLEncoder.encode(filename, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Firefox")))
				return MimeUtility.encodeText(filename, "UTF-8", "B");

			return filename;
		}
		catch (Exception ex) {
			return filename;
		}
	}

	/**
	 * 功能：临时把文件写到本地
	 * 
	 * @param file 本地文件
	 * @param fileName 文件名
	 * @return
	 */
	public File writeExcelToLocal(File file) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			hSSFWorkbook.write(os);
		}
		catch (Exception e) {
			log.error("输出Excel出错", e);
		}
		finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				}
				catch (IOException e) {
				}
			}
		}
		return file;
	}

	/**
	 * 功能：创建patriarch
	 * 
	 * @return
	 */
	public void createPatriarch() {
		patriarch = openedSheet.createDrawingPatriarch();
	}

	/**
	 * 功能：插入图片
	 * 
	 * @author yangr
	 * @return
	 */
	public void insertImg(int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2, String jbPath) {
		ByteArrayOutputStream byteArrayOut = null;
		InputStream is = null;
		OutputStream os = null;
		File file = null;
		try {
			byteArrayOut = new ByteArrayOutputStream();
			// 将格式转换为png
			StringBuilder newPath = new StringBuilder();
			String root = System.getProperty("java.io.tmpdir");
			String fileName = UUID.randomUUID().toString().replace("-", "");
			newPath.append(root).append(fileName).append(".png");
			file = new File(newPath.toString());
			is = new FileInputStream(new File(jbPath));
			os = new FileOutputStream(file);
			byte[] data = new byte[2048];
			int len = 0;
			while ((len = is.read(data)) > 0) {
				os.write(data, 0, len);
			}
			BufferedImage bufferImg = ImageIO.read(file);
			ImageIO.write(bufferImg, "png", byteArrayOut);
			HSSFClientAnchor anchor = new HSSFClientAnchor(dx1, dy1, dx2, dy2, (short) col1, row1, (short) col2, row2);
			patriarch.createPicture(anchor, hSSFWorkbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				byteArrayOut.close();
				is.close();
				os.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			file.delete();
		}
	}

	/**
	 * 功能描述:文件名编码转换（文件下载时用）<br>
	 * 
	 * @param fileName
	 * @param checkSpecial 是否检查特殊字符（当文件名是通过程序赋值时，确认其不包含特殊字符时，该值可设为false）
	 * @throws UnsupportedEncodingException
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public String fileNameEncode(String fileName, boolean checkSpecial) throws UnsupportedEncodingException {

		String encodeName = fileName;

		if (checkSpecial) {
			for (int i = 0; i < fileName.length(); i++) {
				char c = fileName.charAt(i);
				try {
					if (new String((c + "").getBytes(), "iso-8859-1").equals("?")) {
						encodeName = fileName.substring(0, i) + "_" + fileName.substring(i + 1);
					}
				}
				catch (Exception e) {
					encodeName = fileName.substring(0, i) + "_" + fileName.substring(i + 1);
				}
			}
		}
		return new String(encodeName.getBytes(), "iso-8859-1");
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public void setHeadStyle(HSSFCellStyle headStyle) {

		this.headStyle = headStyle;
	}

	public void setEvenStyle(HSSFCellStyle evenStyle) {

		this.evenStyle = evenStyle;
	}

	public void setOddStyle(HSSFCellStyle oddStyle) {

		this.oddStyle = oddStyle;
	}

	public void setHeadFont(HSSFFont headFont) {

		this.headFont = headFont;
	}

	public void setDataFont(HSSFFont dataFont) {

		this.dataFont = dataFont;
	}

	public void setValMatchType(boolean isValMatchType) {
		this.isValMatchType = isValMatchType;
	}

	public ExcelTemplate setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	/**
	 * 传入标题名称和标题字符串的映射
	 * 
	 * @param titleMap
	 * @author yangrui
	 * @date 2014-7-2 下午2:38:21
	 */
	public ExcelTemplate addTitleList(ColumnData columnMap) {
		this.columnData = columnMap;
		if (null != gridList) {
			autoComplete();
		}
		return this;
	}

	public ExcelTemplate addGridList(List<?> gridList) {
		this.gridList = gridList;
		if (null != columnData) {
			autoComplete();
		}
		return this;
	}

	public void clearTitleListGridList() {
		this.columnData = null;
		this.gridList = null;
	}

	private void autoComplete() {
		this.isShowXh(false);
		String[] titles = columnData.getTitleArray();
		String[] fields = columnData.getFieldArray();
		Map<String, String> map = columnData.getTypeCodeMap();
		//创建标题数组
		String[] titleNameArr = new String[titles.length];
		for(int i = 0;i < titles.length;i++){
			if(StringUtil.isBlank(this.titleName)){
				this.titleName = this.fileName;
			}
			titleNameArr[i] = this.titleName;
		}
		//创建style
		this.createRow(titleNameArr,getMyHeadStyle());
		this.mergingSameRow(0);
		this.createHeadRow(titles);
		
		// -------------
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class, new JsonDateProcessor());
		config.registerJsonValueProcessor(BigDecimal.class, new JsonCommonProcessor());
		config.registerJsonValueProcessor(Long.class, new JsonCommonProcessor());
		config.registerJsonValueProcessor(Double.class, new JsonCommonProcessor());
		config.registerJsonValueProcessor(Float.class, new JsonCommonProcessor());
		config.registerJsonValueProcessor(Short.class, new JsonCommonProcessor());
		config.registerJsonValueProcessor(Integer.class, new JsonCommonProcessor());
		config.registerJsonValueProcessor(Character.class, new JsonCommonProcessor());

		JSONArray arr = JSONArray.fromObject(gridList, config);
		// 每一行的结果集
		List<Object> rowList = null;
		// 遍历数据集
		Iterator<?> it = arr.iterator();
		while (it.hasNext()) {
			JSONObject json = (JSONObject) it.next();
			rowList = new ArrayList<Object>();
			for (String key : fields) {
				Object value = (Object) json.get(key);
				if (value instanceof String) {
					if (!StringUtil.isBlank((String) value)) {
						String typeCode = map != null ? map.get(key) : null;
						// 前台没有传入typeCode的话，就从typeCodeField中取
						if (null == typeCode) {
							typeCode = this.typeCodeField.get(key);
						}
						if (null != typeCode) {
							String desc = TypeCodeUtil.getCodeDesc(typeCode, (String) value);
							value = desc != null ? desc : "";
						}
						// 如果是typeCode还为空的话，判断是否date类型
						if (null == typeCode) {
							String pattern = dateField.get(key);
							if (null != pattern && !StringUtil.isBlank((String) value)) {
								try {
									Date tempDate = DateUtil.parse((String) value, DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS);
									value = DateUtil.format(tempDate, pattern);
								}
								catch (ParseException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}

				// 判断是不是自定义类型
				FieldFormatter formt = customField.get(key);
				if (null != formt) {
					value = formt.translate(value);
				}
				rowList.add(value == null ? "" : value);
			}
			this.createEvenRow(rowList);
		}

	}

	public ExcelTemplate addTypeCodeField(String field, String typeCode) {
		this.typeCodeField.put(field, typeCode);
		return this;
	}

	public ExcelTemplate addDateField(String field, String pattern) {
		this.dateField.put(field, pattern);
		return this;
	}

	public ExcelTemplate formatter(String field, FieldFormatter fieldFormatter) {
		this.customField.put(field, fieldFormatter);
		return this;
	}

	public ExcelTemplate setTitleName(String titleName) {
		this.titleName  = titleName;
		return this;
	}
}
