package cn.cttic.sysframe.frame.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.cttic.sysframe.common.constants.CommonConstants;
import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.frame.domain.SmDocFtpBizRel;
import cn.cttic.sysframe.frame.domain.SmDocInfo;
import cn.cttic.sysframe.frame.domain.SmDocInfoExample;
import cn.cttic.sysframe.frame.domain.SmDocInfoExample.Criteria;
import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.facade.SysDictFacadeService;
import cn.cttic.sysframe.frame.model.SmDocInfoModel;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.FileUploadService;
import cn.cttic.sysframe.frame.service.SmDocFtpBizRelService;
import cn.cttic.sysframe.frame.service.SmDocInfoService;
import cn.cttic.sysframe.frame.service.SmSysDictService;
import cn.cttic.sysframe.frame.service.SystemService;
import cn.cttic.sysframe.frame.util.FtpUtil;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private SmDocFtpBizRelService smDocFtpBizRelService;

	@Autowired
	private SmDocInfoService smDocInfoService;

	@Autowired
	private FtpUtil ftpUtil;

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private SmSysDictService smSysDictService;

	@Autowired
	private SysDictFacadeService sysDictFacadeService;
	
	@Override
	public String upload(HttpServletRequest request) throws Exception {
		try {
			String bizType = request.getParameter("bizType");
			String bizId = request.getParameter("bizId");
			String serialNo = request.getParameter("serialNo");
			return upload(request, bizType, bizId, serialNo);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public String upload(HttpServletRequest request, String bizType, String bizId, String serialNo) throws Exception {
		String ftpFileName = "";
		try {
			if (StringUtils.isBlank(bizType)) {
				return "未知业务类型";
			}
			SmDocFtpBizRel bizRel = smDocFtpBizRelService.selectSmDocFtpBizRelByBizType(bizType);
			if (bizRel == null) {
				return "未配置文档ftp业务关系,业务类型为bizType=" + bizType;
			}
			// 将当前上下文初始化给 CommonsMultipartResolver (多部分解析器)
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分 request
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				try {
					ftpUtil.setFtpPathCode(bizRel.getFtpPathCode());
					ftpUtil.bin();
					// 获取multipartRequest中所有的文件名
					Iterator iter = multipartRequest.getFileNames();
					while (iter.hasNext()) {
						InputStream input = null;
						MultipartFile file = multipartRequest.getFile(iter.next().toString());
						String time = DateUtil.format(systemService.getSystemDate(), DateUtil.PATTERN_YYYYMMDDHHMMSSSSS);
						String originalFilelName = file.getOriginalFilename();
						String type = StringUtils.substring(originalFilelName, originalFilelName.lastIndexOf("."), originalFilelName.length());
						String upNewName = time + "-" + ((int) (Math.random() * 10000)) + type;
						String tmpName = "tmp_" + upNewName;
						input = file.getInputStream();
						if (StringUtils.isBlank(ftpFileName)) {
							ftpFileName = upNewName;
						} else {
							ftpFileName += "," + upNewName;
						}
						SmOperModel operModel = SessionInfo.getOperInfo();
						String orgId = "";
						String operId = "";
						if (operModel!=null) {
							operId = operModel.getOperId();
							orgId = operModel.getOrgId();
						}
						// 保存文档信息日志
						HashMap<String, String> map = smDocInfoService.createSmDocInfo(originalFilelName, upNewName, bizId, bizType, serialNo,
						                                                               file.getSize(), "U", orgId, operId, upNewName);
						// 上传文件
						ftpUtil.upload(tmpName, input);
						ftpUtil.rename(tmpName, upNewName);

						if (input != null) {
							input.close();
						}
					}

				}
				catch (Exception e) {
					throw e;
				}
				finally {
					if (ftpUtil != null) {
						ftpUtil.close();
					}
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return ftpFileName;
	}

	@Override
	public JSONObject filelistQuery(String contextPath, String bizType, String bizId, String serialNo, String deleteFlag) {
		JSONObject rtnJson = new JSONObject();

		SmDocInfoExample smDocInfoExample = new SmDocInfoExample();
		Criteria criteria = smDocInfoExample.createCriteria();
		if (StringUtils.isNotBlank(bizId)) {
			criteria.andBizIdEqualTo(bizId);
		}
		if (StringUtils.isNotBlank(bizType)) {
			criteria.andBizTypeEqualTo(bizType);
		}
		if (StringUtils.isNotBlank(serialNo)) {
			criteria.andSerialNoEqualTo(serialNo);
		}
		criteria.andStateEqualTo("U");

		List<SmDocInfo> smDocInfoList = smDocInfoService.selectByExample(smDocInfoExample);
		List<SmDocInfoModel> smDocInfoModelList = new ArrayList<SmDocInfoModel>();
		SmDocInfoModel smDocInfoModel = null;
		for (SmDocInfo bean : smDocInfoList) {
			smDocInfoModel = new SmDocInfoModel();
			BeanUtils.copyProperties(bean, smDocInfoModel);
			String img = "";
			String documentType = bean.getDocumentType();
			if (StringUtils.isNotBlank(documentType)) {
				if (StringUtils.containsIgnoreCase(documentType, "avi") || StringUtils.containsIgnoreCase(documentType, "mpg")) {
					img = "<img src='" + contextPath + "/images/dot_swf.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "xls")) {
					img = "<img src='" + contextPath + "/images/dot_xls.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "doc")) {
					img = "<img src='" + contextPath + "/images/dot_word.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "ppt")) {
					img = "<img src='" + contextPath + "/images/dot_ppt.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "pdf")) {
					img = "<img src='" + contextPath + "/images/dot_pdf.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "swf")) {
					img = "<img src='" + contextPath + "/images/dot_swf.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "exe")) {
					img = "<img src='" + contextPath + "/images/dot_exe.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "gif")) {
					img = "<img src='" + contextPath + "/images/dot_gif.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "hlp") || StringUtils.containsIgnoreCase(documentType, "chm")) {
					img = "<img src='" + contextPath + "/images/dot_hlp.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "htm")) {
					img = "<img src='" + contextPath + "/images/dot_htm.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "jpg")) {
					img = "<img src='" + contextPath + "/images/dot_jpg.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "mdb")) {
					img = "<img src='" + contextPath + "/images/dot_mdb.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "txt")) {
					img = "<img src='" + contextPath + "/images/dot_txt.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "mp3") || StringUtils.containsIgnoreCase(documentType, "wav")) {
					img = "<img src='" + contextPath + "/images/dot_mid.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "rar") || StringUtils.containsIgnoreCase(documentType, "zip")) {
					img = "<img src='" + contextPath + "/images/dot_rar.gif' height=18px width=18px >";
				} else {
					img = "<img src='" + contextPath + "/images/dot_annex.gif' height=18px width=18px >";
				}
			} else {
				img = "<img src='" + contextPath + "/images/dot_annex.gif' height=18px width=18px >";
			}
			smDocInfoModel.setDocumentName(img + bean.getDocumentName());
			smDocInfoModel.setUploadTimeStr(DateUtil.format(bean.getUploadDate(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			String downloadStr  = getParamNameDefault("VIEWS.FRAME", "DOWNLOAD", "下载");
			String deleteStr  = getParamNameDefault("VIEWS.FRAME", "DELETE", "删除");
			String download = "<a href='" + contextPath + "/attach/download.htm?documentId=" + bean.getDocumentId()+"'>"+downloadStr+"</a>";
			String delete = "&nbsp;&nbsp;<a href='#' onClick='deleteFile(" + bean.getDocumentId() + ")'>"+deleteStr+"</a>";
			if (StringUtils.equals(deleteFlag, "0")) {
				smDocInfoModel.setOperate(download);
			} else {
				smDocInfoModel.setOperate(download + delete);
			}
			smDocInfoModelList.add(smDocInfoModel);
		}
		JSONArray jsonArray = JSONArray.fromObject(smDocInfoModelList);
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_CODE, CommonConstants.AjaxStatus.STATUS_SUCCESS);
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_INFO, CommonConstants.Message.QUERY_SUCCESS);
		rtnJson.put("total", smDocInfoModelList.size());
		rtnJson.put("rows", jsonArray);

		return rtnJson;
	}

	public String getParamNameDefault(String typeCode,String paramCode,String defaultName) {
		SmSysDict smSysDict = sysDictFacadeService.getSmSysDict(typeCode, paramCode);
		if (smSysDict!=null) {
			return StringUtils.isNotBlank(smSysDict.getParamDesc()) ? smSysDict.getParamDesc() : defaultName ;
		}
		return defaultName;
	}
	
	@Override
	public JSONObject deleteFile(long documentId) {
		JSONObject rtnJson = new JSONObject();

		SmDocInfo record = smDocInfoService.selectByPrimaryKey(documentId);
		record.setState("E");
		smDocInfoService.updateByPrimaryKey(record);
		String deleteSuccess  = getParamNameDefault("VIEWS.REMINDER", "DELETE_SUCCESS", "删除成功");
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_CODE, CommonConstants.AjaxStatus.STATUS_SUCCESS);
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_INFO, deleteSuccess);

		return rtnJson;
	}

	@Override
    public Map<String, String> getDocInfo(String bizType, String bizId, String serialNo) {
		Map<String,String> retMap = new HashMap<String, String>();
		if (StringUtils.isBlank(bizType)) {
			retMap.put("RET_CODE", "0001");
			retMap.put("RET_MSG", "业务类型不能为空");
			return retMap;
		}
		
		if (StringUtils.isBlank(bizId)) {
			retMap.put("RET_CODE", "0002");
			retMap.put("RET_MSG", "业务编码不能为空");
			return retMap;
		}
		
		SmDocFtpBizRel bizRel = smDocFtpBizRelService.selectSmDocFtpBizRelByBizType(bizType);
		if (bizRel == null) {
			retMap.put("RET_CODE", "0003");
			retMap.put("RET_MSG", "未知业务类型："+bizType);
			return retMap;
		}
		SmDocInfoExample example = new SmDocInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andBizTypeEqualTo(bizType);
		criteria.andBizIdEqualTo(bizId);
		if (StringUtils.isNotBlank(serialNo)) {
			criteria.andSerialNoEqualTo(serialNo);
		}
		List<SmDocInfo> smDocInfoList = smDocInfoService.selectByExample(example);
		if (smDocInfoList == null || smDocInfoList.size() == 0) {
			retMap.put("RET_CODE", "0004");
			retMap.put("RET_MSG", "未找到相关文档信息");
			return retMap;
		}
		String fileName = "";
		for (SmDocInfo smDocInfo : smDocInfoList) {
	        if (StringUtils.isBlank(fileName)) {
	        	fileName = smDocInfo.getTransDocName();
	        } else {
	        	fileName += "," + smDocInfo.getTransDocName();
	        }
        }
		retMap.put("RET_CODE", "0000");
		retMap.put("RET_MSG", "查询成功");
		retMap.put("FTP_PATH_CODE", bizRel.getFtpPathCode());
		retMap.put("FILE_NAME", fileName);
	    return retMap;
    }

	@Override
	public List<SmDocInfoModel> selectFileList(String contextPath, String bizType, String bizId, String serialNo,String deleteFlag) {
		SmDocInfoExample smDocInfoExample = new SmDocInfoExample();
		Criteria criteria = smDocInfoExample.createCriteria();
		if (StringUtils.isNotBlank(bizId)) {
			criteria.andBizIdEqualTo(bizId);
		}
		if (StringUtils.isNotBlank(bizType)) {
			criteria.andBizTypeEqualTo(bizType);
		}
		if (StringUtils.isNotBlank(serialNo)) {
			criteria.andSerialNoEqualTo(serialNo);
		}
		criteria.andStateEqualTo("U");

		List<SmDocInfo> smDocInfoList = smDocInfoService.selectByExample(smDocInfoExample);
		List<SmDocInfoModel> smDocInfoModelList = new ArrayList<SmDocInfoModel>();
		SmDocInfoModel smDocInfoModel = null;
		if (StringUtils.isBlank(contextPath)) {
			contextPath = getContextPath();
		}
		for (SmDocInfo bean : smDocInfoList) {
			smDocInfoModel = new SmDocInfoModel();
			BeanUtils.copyProperties(bean, smDocInfoModel);
			String img = "";
			String documentType = bean.getDocumentType();
			if (StringUtils.isNotBlank(documentType)) {
				if (StringUtils.containsIgnoreCase(documentType, "avi") || StringUtils.containsIgnoreCase(documentType, "mpg")) {
					img = "<img src='" + contextPath + "/images/dot_swf.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "xls")) {
					img = "<img src='" + contextPath + "/images/dot_xls.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "doc")) {
					img = "<img src='" + contextPath + "/images/dot_word.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "ppt")) {
					img = "<img src='" + contextPath + "/images/dot_ppt.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "pdf")) {
					img = "<img src='" + contextPath + "/images/dot_pdf.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "swf")) {
					img = "<img src='" + contextPath + "/images/dot_swf.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "exe")) {
					img = "<img src='" + contextPath + "/images/dot_exe.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "gif")) {
					img = "<img src='" + contextPath + "/images/dot_gif.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "hlp") || StringUtils.containsIgnoreCase(documentType, "chm")) {
					img = "<img src='" + contextPath + "/images/dot_hlp.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "htm")) {
					img = "<img src='" + contextPath + "/images/dot_htm.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "jpg")) {
					img = "<img src='" + contextPath + "/images/dot_jpg.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "mdb")) {
					img = "<img src='" + contextPath + "/images/dot_mdb.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "txt")) {
					img = "<img src='" + contextPath + "/images/dot_txt.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "mp3") || StringUtils.containsIgnoreCase(documentType, "wav")) {
					img = "<img src='" + contextPath + "/images/dot_mid.gif' height=18px width=18px >";
				} else if (StringUtils.containsIgnoreCase(documentType, "rar") || StringUtils.containsIgnoreCase(documentType, "zip")) {
					img = "<img src='" + contextPath + "/images/dot_rar.gif' height=18px width=18px >";
				} else {
					img = "<img src='" + contextPath + "/images/dot_annex.gif' height=18px width=18px >";
				}
			} else {
				img = "<img src='" + contextPath + "/images/dot_annex.gif' height=18px width=18px >";
			}
			smDocInfoModel.setDocumentName(img + bean.getDocumentName());
			smDocInfoModel.setUploadTimeStr(DateUtil.format(bean.getUploadDate(), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			String download = "<a href='" + contextPath + "/attach/download.htm?documentId=" + bean.getDocumentId()+"'>"+img + bean.getDocumentName()+"</a>";
			String delete = "&nbsp;&nbsp;&nbsp;<img src='" + contextPath + "/css/themes/easyui/icons/silk/delete.png' height=16px width=16px title='删除' onClick='deleteFile(" + bean.getDocumentId() + ")'>";
			
			if(StringUtils.equalsIgnoreCase(deleteFlag, "true")) {
				smDocInfoModel.setOperate(download + delete);
			} else {
				smDocInfoModel.setOperate(download);
			}
			smDocInfoModelList.add(smDocInfoModel);
		}

		return smDocInfoModelList;
	}

	private String getContextPath() {
		SmSysDict smSysDict = smSysDictService.getSmSysDict("CS_CONTEXT_PATH", "/s4boss-crm");
		if (smSysDict!=null) {
			return StringUtils.isNotBlank(smSysDict.getParamDesc()) ? smSysDict.getParamDesc() : "/s4boss-crm" ;
		} else {
			return "/s4boss-crm";
		}
	}

	@Override
    public String upload(String bizType, String bizId, String serialNo, List<File> fileList ) throws Exception{
		if (StringUtils.isBlank(bizType)) {
			return "未知业务类型";
		}
		SmDocFtpBizRel bizRel = smDocFtpBizRelService.selectSmDocFtpBizRelByBizType(bizType);
		if (bizRel == null) {
			return "未配置文档ftp业务关系,业务类型为bizType=" + bizType;
		}
		FileInputStream fin = null;
		InputStream in = null;
		try {
			ftpUtil.setFtpPathCode(bizRel.getFtpPathCode());
			ftpUtil.bin();
			if (fileList != null && fileList.size()>0) {
				for (File localFile : fileList) {
					fin = new FileInputStream(localFile);
					in = new BufferedInputStream(fin);
					String remoteFileName = localFile.getName();
					ftpUtil.upload(localFile.getName(), fin,FtpUtil.BIN);
					// 保存文档信息日志
					smDocInfoService.createSmDocInfo(remoteFileName, remoteFileName, bizId, bizType, serialNo,
					                                 localFile.length(), "U", "", "", remoteFileName);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			IOUtils.closeQuietly(fin);
			IOUtils.closeQuietly(in);
			ftpUtil.close();
		}
		
	    return "SUCCESS";
    }
	
	public String upload(String bizType, String bizId, String serialNo,String remoteFileName, InputStream input,String operId,String orgId) throws Exception{
		if (StringUtils.isBlank(bizType)) {
			return "未知业务类型";
		}
		SmDocFtpBizRel bizRel = smDocFtpBizRelService.selectSmDocFtpBizRelByBizType(bizType);
		if (bizRel == null) {
			return "未配置文档ftp业务关系,业务类型为bizType=" + bizType;
		}
		try {
			ftpUtil.setFtpPathCode(bizRel.getFtpPathCode());
			ftpUtil.bin();
			ftpUtil.upload(remoteFileName, input);
			// 保存文档信息日志
			int size = 0;
			int fileSize = 0; 
			byte[] buffer = new byte[10 * 1024];
			while((size = input.read(buffer)) != -1) {    
				fileSize += size;
			}
			smDocInfoService.createSmDocInfo(remoteFileName, remoteFileName, bizId, bizType, serialNo,
			                                 fileSize, "U", orgId, operId, remoteFileName);
        }
        catch (Exception ex) {
	        throw ex;
        }
        finally {
        	ftpUtil.close();
        }
		return "SUCCESS";
	}
	
}
