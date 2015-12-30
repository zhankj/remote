package cn.cttic.sysframe.frame.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.constants.CommonConstants.SmFileState;
import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.ClassUtil;
import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.domain.BatFile;
import cn.cttic.sysframe.frame.domain.IntfSms;
import cn.cttic.sysframe.frame.domain.SmFile;
import cn.cttic.sysframe.frame.domain.SmFileExample;
import cn.cttic.sysframe.frame.domain.SmJobConfig;
import cn.cttic.sysframe.frame.domain.SmFileExample.Criteria;
import cn.cttic.sysframe.frame.service.BatFileService;
import cn.cttic.sysframe.frame.service.FileUploadService;
import cn.cttic.sysframe.frame.service.IntfSmsService;
import cn.cttic.sysframe.frame.service.SmFileService;
import cn.cttic.sysframe.frame.service.SmJobConfigService;
import cn.cttic.sysframe.frame.service.SmJobLogService;
import cn.cttic.sysframe.frame.service.SystemService;
@Service
public class BpFileDealTask {
	
	@Autowired
	private SmFileService SmFileService;
	
	@Autowired
	private SmJobConfigService SmJobConfigService;
	
	@Autowired
	private SmJobLogService SmJobLogService; 
	
	@Autowired
	private  FileUploadService FileUploadService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private IntfSmsService intfSmsService;
	
	private transient static Log log = LogFactory.getLog(BpFileDealTask.class);
	
	public  void dealFile(Long fileId) {
		try{
			 String jobCodeString="";
			 SmJobConfig[] smJobConfig=null;
			 SmFileExample smFileExample=new SmFileExample();
			 Criteria criteria =smFileExample.or();
			 criteria.andDealStateEqualTo(SmFileState.SM_fILE_STATE_U);
			 if(fileId!=null){
				 criteria.andFileIdEqualTo(fileId);
			 }
			 List<SmFile> SmFile=	SmFileService.selectByExample(smFileExample);
			 for(SmFile smFile:SmFile ){
				 
				 try{
					 
					 jobCodeString=smFile.getJobCode();
					 List<SmJobConfig> smJobConfigList= SmJobConfigService.getSmJobConfig(jobCodeString);
					 if(smJobConfigList!=null && smJobConfigList.size()>0){
						 smJobConfig=smJobConfigList.toArray(new SmJobConfig[0]);
					 }else{
						 throw new SystemException(StatusCodeForFrame.INSIDE_ERROR, "处理文件中的JOB_CODE"+jobCodeString+"在SM_JOB_CONFIG表中不存在!");
					 }
					 //更新处理中状态
					 smFile.setDealState(SmFileState.SM_fILE_STATE_P);
					 smFile.setDealDate(DateUtil.getServerDate());
					 SmFileService.updateByPrimaryKeySelective(smFile);
					 
					 //调用数据
					 int[] reInt= getMethod(smJobConfig[0],smFile);
					 //更新结束状态
					 smFile.setDealState(SmFileState.SM_fILE_STATE_S);
					 smFile.setDealDate(DateUtil.getServerDate());
					 smFile.setSucCount(Long.valueOf(reInt[0]));
					 smFile.setFailCount(Long.valueOf(reInt[1]));
					 SmFileService.updateByPrimaryKeySelective(smFile);
					 //记录错误日志
					 SmJobLogService.insertSelective(smFile.getFileId(),"success");
					 //发送短信
					 if(smFile.getPhone()!=null && StringUtils.isNotBlank(smFile.getPhone())){
						 doSendNote(smFile.getFileName(),smFile.getPhone(),reInt);
					 }
				}catch(Exception e){
					 log.error(e);
					 if(smFile.getPhone()!=null && StringUtils.isNotBlank(smFile.getPhone())){
						 doSendFailNote(smFile.getFileName(),smFile.getPhone());
					 }	 
					 SmJobLogService.insertSelective(smFile.getFileId(),e.toString());
					 smFile.setDealState(SmFileState.SM_fILE_STATE_F);
					 SmFileService.updateByPrimaryKeySelective(smFile); 
					  continue;
				}
			} 
		}catch(SystemException e){
			 log.error(e);
			 throw e;
		}	 
	}
	public void doSendNote(String fileName,String phoneNum,int[] count ){
		try{
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("您好：您导入的文件").append(fileName).append("系统已经处理完毕,其中成功")
					.append(count[0]).append("条,失败").append(count[1])
					.append("条。");
			intfSmsService.saveIntfSms((short) 1, FrameConstants.BatchDeal.DEAL_NOTE, null, null, null, phoneNum, sBuffer.toString(), systemService.getSystemDate(),
                    null);
		}catch(SystemException e){
			log.error(e);
		}
	}
	public void doSendFailNote(String fileName,String phoneNum){
		try{
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("您好：您导入的文件").append(fileName).append("系统处理执行失败，请查看原因！");
					
			intfSmsService.saveIntfSms((short) 1, FrameConstants.BatchDeal.DEAL_NOTE, null, null, null, phoneNum, sBuffer.toString(), systemService.getSystemDate(),
                    null);
		}catch(SystemException e){
			log.error(e);
		}
	}
	public int[] getMethod(SmJobConfig smJobConfig,SmFile smFile) throws Exception{
		Object object=ClassUtil.getClassInstance(smJobConfig.getClassPath());
		//BatFileService file=(BatFileService)object;
		BatFileService file=(BatFileService)SpringContextUtil.getBean(((BatFileService)object).getClass());
		BatFile batFile=new BatFile();
		batFile.setFileId(smFile.getFileId());
		Map<String,String>   map=FileUploadService.getDocInfo(smFile.getJobCode(), String.valueOf(smFile.getFileId()), "");
		if(map.containsKey("RET_CODE") && "0000".equals(map.get("RET_CODE"))){
			batFile.setFtpPathCode(map.get("FTP_PATH_CODE"));
			batFile.setFileName(map.get("FILE_NAME"));
		}else{
			 throw new SystemException(StatusCodeForFrame.INSIDE_ERROR, map.get("RET_MSG"));
		}
		batFile.setOpId(smFile.getCreateOperId());
		batFile.setOrgId(smFile.getCreateOrgId());
		//batFile.setOpCode(opCode);
		int[] results=file.addDealBat(batFile);
		return results;
	}
	public static void main(String[] args) {
//		try {
//			 SpringContextUtil a=new SpringContextUtil();
//			a.setApplicationContext(new ClassPathXmlApplicationContext(new String[] { "boss-data.xml","boss-service.xml","boss-servlet.xml","boss-cache.xml"}));
//
//			BpFileDealTask service = (BpFileDealTask) SpringContextUtil.getBean(BpFileDealTask.class);
//			while (true) {
//				service.dealFile();
//				System.out.println("[" + FrameConstants.SDF_YYYY_MM_DD_HH_MM_SS.format(new Date()) + "]------after " + 30 + " second next------");
//				java.util.concurrent.TimeUnit.SECONDS.sleep(30);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}


}

