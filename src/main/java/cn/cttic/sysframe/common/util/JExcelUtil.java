package cn.cttic.sysframe.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 * 
 
* @ClassName: JExcelUtil
 
* @Description: TODO(生成excel工具类)
 
* @author xingyh 
 
* @date 2014-5-15 下午03:46:54
 
*
 */
public class JExcelUtil {

/**
 * 
 
* @Title: JExcelUtil
 
* @Description: TODO(这里用一句话描述这个方法的作用)
 
* @param @param os 输出流
* @param @param workName 工作区的名称
* @param @param list 表头列的名称列表
* @param @return
* @param @throws IOException
* @param @throws RowsExceededException
* @param @throws WriteException    设定文件
 
* @return    返回值
 
* @throws
 */
	public static WritableWorkbook getExcelTitle(OutputStream os,String workName,List list) throws IOException, RowsExceededException, WriteException{
		// 创建工作区  
	    WritableWorkbook workbook = Workbook.createWorkbook(os);
	    // 创建新的一页，sheet只能在工作簿中使用  
	    //workName标识生成sheet页的名称
	    WritableSheet sheet = workbook.createSheet(workName, 0);  
	    // 通过函数WritableFont（）设置字体样式  
	    // 第一个参数表示所选字体  
	    // 第二个参数表示字体大小  
	    // 第三个参数表示粗体样式，有BOLD和NORMAL两种样式  
	    // 第四个参数表示是否斜体,此处true表示为斜体  
	    // 第五个参数表示下划线样式  
	    // 第六个参数表示颜色样式，此处为Red  

	    CellFormat cf = new WritableCellFormat(getTitleFont());  
	    // 创建单元格即具体要显示的内容，new Label(0,0,"用户") 第一个参数是column 第二个参数是row  
	    // 第三个参数是content，第四个参数是可选项,为Label添加字体样式  
	    //设置excel的表头
	     if(list!=null && list.size()>0){
	    	 for(int i=0;i<list.size();i++){
	    		 sheet.setColumnView(i, 20); // 设置列的宽度
	    		 WritableCell title = new Label(i, 0, list.get(i).toString(), cf);  
	    		    // 通过sheet的addCell方法添加Label，注意一个cell/label只能使用一次addCell  
	    		 sheet.addCell(title);  
	    	 }
	     }
	    return  workbook;
	}
	//设定表头样式
	public static WritableFont getTitleFont(){
		 WritableFont wf = new WritableFont(WritableFont.TIMES, 12,  
		            WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		            Colour.BLACK);
		 return wf;
	}
	//设置内容样式
	public static WritableFont getContentFont(){
		 WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 10,  
				 	WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE
		            );
		 return wf;
	}
}
