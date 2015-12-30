package cn.cttic.sysframe.frame.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import cn.cttic.sysframe.frame.model.SmDocInfoModel;

/**
 * 附件管理接口服务. <br>
 * 类详细说明.
 * 
 * @author fujiazhen@cttic.cn
 * @date 2014-4-20 上午11:29:03 <br>
 *       Copyright: Copyright (c) 2014 CTTIC
 */
public interface FileUploadService {

	/**
	 * 文件上传接口 form提交请求，form中必须有enctype="multipart/form-data"
	 * request中要有bizType（必须）、bizId、serialNo属性
	 * 
	 * @param request MultipartHttpServletRequest
	 * @return
	 * @throws Exception
	 * @author fujz
	 * @date 2014-4-20 上午11:29:27
	 */
	public String upload(HttpServletRequest request) throws Exception;

	/**
	 * 附件上传接口
	 * @param request MultipartHttpServletRequest
	 * @param bizType 业务类型
	 * @param bizId 业务编码
	 * @param serialNo 操作流水
	 * @return ftpFileName 上传ftp服务器文件名，多个文件名之间用“,”隔开
	 * @throws Exception
	 * @author fujz
	 * @date 2014-4-20 上午11:34:41
	 */
	public String upload(HttpServletRequest request, String bizType, String bizId, String serialNo) throws Exception;

	/**
	 * 附件列表查询接口
	 * 
	 * @param bizType 业务类型
	 * @param bizId 业务编码
	 * @param serialNo 操作流水
	 * @param deleteFlag 删除标识，0：不展示删除链接，其他展示删除链接
	 * @return
	 * @author fujz
	 * @date 2014-4-20 上午11:52:08
	 */
	public JSONObject filelistQuery(String contextPath, String bizType, String bizId, String serialNo,String deleteFlag);

	/**
	 * 附件删除
	 * 
	 * @param documentId 文档编码
	 * @return
	 * @author fujz
	 * @date 2014-4-20 下午12:03:03
	 */
	public JSONObject deleteFile(long documentId);
	
	/**
	 * 获取文档信息
	 * @param bizType 业务类型，不允许为空
	 * @param bizId 业务编码，不允许为空
	 * @param serialNo 流水号，可以为空
	 * @return Map<Strig,String><br>
	 * 				RET_CODE 返回编码：0000 成功，其他失败<br>
	 * 				RET_MSG  返回信息<br>
	 * 				FTP_PATH_CODE ftp路径编码<br>
	 * 				FILE_NAME ftp文件名，如果存在多个，文件名之间用英文“,”隔开<br>
	 * @author fujz
	 * @date 2014-5-22 上午11:19:44
	 */
	public Map<String,String> getDocInfo(String bizType,String bizId,String serialNo);
	
	/**
	 * 获取文件信息列表
	 * @param contextPath 上下文路径，用于显示附件图标
	 * @param bizType 业务类型
	 * @param bizId 业务编码
	 * @param serialNo 流水号
	 * @param deleteFlag 删除链接标识，true 显示删除链接
	 * @return 
	 * @author fujz
	 * @date 2014-6-25 上午10:00:33
	 */
	public List<SmDocInfoModel> selectFileList(String contextPath, String bizType, String bizId, String serialNo, String deleteFlag) ;
	
	/**
	 * 文件上传
	 * @param bizType 业务类型，不允许为空
	 * @param bizId 业务编码，不允许为空
	 * @param serialNo 流水号，可以为空
	 * @param fileList 上传文件列表
	 * @return 
	 * @author fujz
	 * @date 2014-6-30 下午5:00:14
	 */
	public String upload(String bizType,String bizId,String serialNo, List<File> fileList) throws Exception;
	
	/**
	 * 文件上传
	 * @param bizType 业务类型，不允许为空
	 * @param bizId 业务编码，不允许为空
	 * @param serialNo 流水号，可以为空
	 * @param remoteFileName 远程文件名
	 * @param input 字节输入流
	 * @param operId 操作员标识
	 * @param orgId 组织标识
	 * @return 
	 * @throws Exception 
	 * @author fujz
	 * @date 2014-6-30 下午5:57:25
	 */
	public String upload(String bizType, String bizId, String serialNo,String remoteFileName, InputStream input, String operId, String orgId) throws Exception;
	
	
	public String getParamNameDefault(String typeCode,String paramCode,String defaultName);

}
