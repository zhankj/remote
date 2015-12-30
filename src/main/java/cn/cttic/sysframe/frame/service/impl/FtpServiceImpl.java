package cn.cttic.sysframe.frame.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.cttic.sysframe.common.constants.CommonConstants;
import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.ftp.ConcurrentCapacity;
import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.domain.SmFile;
import cn.cttic.sysframe.frame.domain.SmFtp;
import cn.cttic.sysframe.frame.domain.SmFtpPath;
import cn.cttic.sysframe.frame.domain.SmJobConfig;
import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.FileUploadService;
import cn.cttic.sysframe.frame.service.FtpService;
import cn.cttic.sysframe.frame.service.SmFileService;
import cn.cttic.sysframe.frame.service.SmFtpPathService;
import cn.cttic.sysframe.frame.service.SmFtpService;
import cn.cttic.sysframe.frame.service.SmJobConfigService;
import cn.cttic.sysframe.frame.service.SmSysDictService;
@Service
@Scope("prototype")
public class FtpServiceImpl implements FtpService {
	private static transient Log log = LogFactory.getLog(FtpServiceImpl.class);
	public static final int BIN = 0;
	public static final int ASC = 1;
	private FTPClient client = null;
	private String localPath = null;
	private String remotePathHis = null;
	private String ftpPathCode = null;

	private  int TIMEOUT_SECONDS = 120;
	private  int CONCURRENT_CAPACITY = 50;
	private  int CONCURRENT_ACQUIRE_TIMEOUT_SECONDS = 10;
	private  ConcurrentCapacity SEM =null;
	@Autowired
	private SmFtpPathService SmFtpPathService;
	
	@Autowired
	private SmFtpService SmFtpService;
	
	@Autowired
	private SmJobConfigService  SmJobConfigService;

	@Autowired
	private SmFileService  SmFileService;
	 
	@Autowired
	private FileUploadService FileUploadService;
	
	/**
	 * @Description: 
	 *	获得服务实例
	 * @return
	 * @author Super
	 * @date 2014年8月22日 下午12:59:58
	 */
	public FtpService getInstance() throws Exception{
		return SpringContextUtil.getBean(FtpService.class);
	}
	
	  public void FtpUtils(String ftpPathCode)
	    throws Exception
	  {
		  this.SEM=getCon();
		  List<SmFtpPath> smFtpPathList=  SmFtpPathService.getSmFtpPath(ftpPathCode);
		  SmFtpPath []   smFtpPath =smFtpPathList.toArray(new SmFtpPath[0]);
		  
		  List<SmFtp> smFtpList=  SmFtpService.getSmFtp(smFtpPath[0].getFtpCode());
		  SmFtp[] smFtp=smFtpList.toArray(new SmFtp[0]);
		  

	    this.ftpPathCode = ftpPathCode;
	    this.client = new FTPClient();
	    this.client.setDefaultTimeout(TIMEOUT_SECONDS * 1000);

	    boolean remoteVerificationEnabled = true;

	    String ip = smFtp[0].getHostIp();
	    if (ip.startsWith("{PASV}"))
	    {
	      remoteVerificationEnabled = false;
	      ip = StringUtils.substringAfter(ip, "{PASV}");
	    }
	    else if (ip.startsWith("{PORT}"))
	    {
	      remoteVerificationEnabled = true;
	      ip = StringUtils.substringAfter(ip, "{PORT}");
	    }
	    this.client.connect(ip, smFtp[0].getPort());
	    this.client.setFileType(FTPClient.BINARY_FILE_TYPE);
	    this.client.setRemoteVerificationEnabled(remoteVerificationEnabled);
	    this.client.login( smFtp[0].getUsername(),  smFtp[0].getPassword());
	    this.client.changeWorkingDirectory(wrapper( smFtpPath[0].getRemotePath()));
	    this.localPath =  smFtpPath[0].getLocalPath();
	    this.remotePathHis =  smFtpPath[0].getRemotePathHis();
	  }

	  public SmFtpPath getSmFtpPath()
	    throws Exception
	  {
		  List<SmFtpPath> smFtpPathList=  SmFtpPathService.getSmFtpPath(ftpPathCode);
		  return smFtpPathList.toArray(new SmFtpPath[0])[0];
	  }

	  private String wrapper(String str)
	    throws Exception
	  {
	    return new String(str.getBytes(), "ISO-8859-1");
	  }

	  public void setEncoding(String encoding)
	  {
	    this.client.setControlEncoding(encoding);
	  }

	  public void bin()
	    throws Exception
	  {
	    this.client.setFileType(2);
	  }

	  public void asc()
	    throws Exception
	  {
	    this.client.setFileType(0);
	  }

	  public String[] list(String pathName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      List list = new ArrayList();

	      FTPFile[] ftpFiles = this.client.listFiles(pathName);

	      for (int i = 0; i < ftpFiles.length; ++i) {
	        if (ftpFiles[i].isFile()) {
	          list.add(ftpFiles[i].getName());
	        }
	      }

	      return  (String[])list.toArray(new String[0]);
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public String[] listNames()
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      String[] fileNames = this.client.listNames();

	      String[] arrayOfString1 = fileNames;

	      return arrayOfString1;
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public String[] listNames(String pathName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      String[] fileNames = this.client.listNames(pathName);

	      String[] arrayOfString1 = fileNames;

	      return arrayOfString1;
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public String[] list()
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      List list = new ArrayList();

	      FTPFile[] ftpFiles = this.client.listFiles();

	      for (int i = 0; i < ftpFiles.length; ++i) {
	        if (ftpFiles[i].isFile()) {
	          list.add(ftpFiles[i].getName());
	        }
	      }

	      return (String[])list.toArray(new String[0]);
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public FTPFile getFtpFile(String fileName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      FTPFile rtn = null;

	      FTPFile[] ftpFiles = this.client.listFiles(fileName);

	      for (int i = 0; i < ftpFiles.length; ++i) {
	        if ((ftpFiles[i].isFile()) && (ftpFiles[i].getName().equals(fileName))) {
	          rtn = ftpFiles[i];
	          break;
	        }
	      }

	      if (rtn == null) {
	        throw new Exception("无法在目录:" + this.client.printWorkingDirectory() + "下找到文件:" + fileName);
	      }

	      return rtn;
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public String[] listDir()
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      List list = new ArrayList();

	      FTPFile[] ftpFiles = this.client.listFiles();

	      for (int i = 0; i < ftpFiles.length; ++i) {
	        if (ftpFiles[i].isDirectory()) {
	          list.add(ftpFiles[i].getName());
	        }
	      }
	      return (String[])list.toArray(new String[0]);
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public String getCurrentWorkingDirectory()
	    throws Exception
	  {
	    return this.client.printWorkingDirectory();
	  }

	  public boolean mkdir(String dir)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      boolean bool1 = this.client.makeDirectory(dir);

	      return bool1;
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public void changeWorkingDirectory(String dir)
	    throws Exception
	  {
	    this.client.changeWorkingDirectory(wrapper(dir));
	  }

	  public void upload(String remoteFileName, InputStream input, int mode)
	    throws Exception
	  {
	    if (mode == 0) {
	      this.client.setFileType(2);
	    }
	    else if (mode == 1) {
	      this.client.setFileType(0);
	    }
	    else {
	      throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,mode);
	    }
	    upload(remoteFileName, input);
	  }

	  public void upload(String remoteFileName, InputStream input)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      this.client.storeFile(wrapper(remoteFileName), input);
	    }
	    finally {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public void upload(String remoteFileName, String localFileName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      InputStream is = null;
	      try {
	        is = new BufferedInputStream(new FileInputStream(this.localPath + "/" + localFileName));

	        this.client.storeFile(wrapper(remoteFileName), is);
	      }
	      finally {
	        if (is != null)
	          is.close();
	      }
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public void upload(String remoteFileName, String localFileName, int mode)
	    throws Exception
	  {
	    if (mode == 0) {
	      this.client.setFileType(2);
	    }
	    else if (mode == 1) {
	      this.client.setFileType(0);
	    }
	    else {
	    	throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,mode);
	    }
	    upload(remoteFileName, localFileName);
	  }

	  public void download(String remoteFileName, OutputStream output, int mode)
	    throws Exception
	  {
	    if (mode == 0) {
	      this.client.setFileType(2);
	    }
	    else if (mode == 1) {
	      this.client.setFileType(0);
	    }
	    else {
	    	throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,mode);
	    }
	    download(remoteFileName, output);
	  }

	  public void download(String remoteFileName, OutputStream output)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      boolean rtn = this.client.retrieveFile(wrapper(remoteFileName), output);
	      if (!(rtn))
	    	  throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,remoteFileName);
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public void download(String remoteFileName, String localFileName, int mode)
	    throws Exception
	  {
	    if (mode == 0) {
	      this.client.setFileType(2);
	    }
	    else if (mode == 1) {
	      this.client.setFileType(0);
	    }
	    else {
	    	throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,mode);
	    }
	    download(remoteFileName, localFileName);
	  }

	  public void download(String remoteFileName, String localFileName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      OutputStream os = null;
	      try {
	        os = new BufferedOutputStream(new FileOutputStream(this.localPath + "/" + localFileName));
	        boolean rtn = this.client.retrieveFile(wrapper(remoteFileName), os);
	        if (!(rtn))
	        	throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,localFileName);
	      }
	      finally
	      {
	        if (os != null)
	          os.close();
	      }
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public InputStream readRemote(String remoteFileName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      InputStream localInputStream = this.client.retrieveFileStream(wrapper(remoteFileName));

	      return localInputStream;
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public int getReplay()
	    throws Exception
	  {
	    return this.client.getReply();
	  }

	  public InputStream readRemote(String remoteFileName, int mode)
	    throws Exception
	  {
	    if (mode == 0) {
	      this.client.setFileType(2);
	    }
	    else if (mode == 1) {
	      this.client.setFileType(0);
	    }
	    else {
	    	throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,mode);
	    }
	    return readRemote(remoteFileName);
	  }

	  public void rename(String oldRemoteFileName, String newRemoteFileName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      this.client.rename(wrapper(oldRemoteFileName), wrapper(newRemoteFileName));
	    }
	    finally {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public void delete(String remoteFileName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      boolean rtn = this.client.deleteFile(wrapper(remoteFileName));
	      if (!(rtn))
	    		throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,remoteFileName);
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public void completePendingCommand()
	    throws Exception
	  {
	    this.client.completePendingCommand();
	  }

	  public void close()
	    throws Exception
	  {
	    if (this.client.isConnected())
	      this.client.disconnect();
	  }

	  public OutputStream getOutputStream(String fileName)
	    throws Exception
	  {
	    boolean isAcquire = false;
	    try {
	      isAcquire = SEM.acquire();

	      OutputStream localOutputStream = this.client.storeFileStream(wrapper(fileName));

	      return localOutputStream;
	    }
	    finally
	    {
	      if (isAcquire)
	        SEM.release();
	    }
	  }

	  public void moveFileToRemoteHisDir(String fileName)
	    throws Exception
	  {
	    if (this.client.listFiles(fileName).length == 0) {
	    	throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,fileName);
	      
	    }

	    if (StringUtils.isBlank(this.remotePathHis)) {
	    	throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,"remotePathHis");
	    }

	    StringBuffer newFileName = new StringBuffer();
	    newFileName.append(this.remotePathHis);
	    newFileName.append("/");
	    newFileName.append(fileName);
	    rename(fileName, newFileName.toString());
	  }
	  public void moveFileToRemoteHis(String fileName,String pathDate)
			    throws Exception
		{
		 if (this.client.listFiles(fileName).length == 0) {
				throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,fileName);
		 }
		if (StringUtils.isBlank(this.remotePathHis)) {
			throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR,"remotePathHis");
		}

		StringBuffer newFileName = new StringBuffer();
		newFileName.append(this.remotePathHis+"/"+pathDate);
		newFileName.append("/");
		newFileName.append(fileName);
		rename(fileName, newFileName.toString());
	}

	public  ConcurrentCapacity getCon(){
		log.debug("ftp超时" + TIMEOUT_SECONDS + "秒");
	    SmSysDictService SmSysDictService= SpringContextUtil.getBean(SmSysDictService.class);
	    //读取数据库配置
	    List<SmSysDict> timeOutList=  SmSysDictService.getSmSysDictList(CommonConstants.ftpParam.FTP_TIMEOUT_SECONDS);
	    if(timeOutList!=null && timeOutList.size()>0){
	    	TIMEOUT_SECONDS = Integer.getInteger("appframe.ftp.timeoutSeconds", Integer.parseInt(timeOutList.get(0).getParamCode())).intValue();
	    }else{
	    	TIMEOUT_SECONDS = Integer.getInteger("appframe.ftp.timeoutSeconds", 120).intValue();
	    }
	    List<SmSysDict> capacityList= SmSysDictService.getSmSysDictList(CommonConstants.ftpParam.FTP_CONCURRENT_CAPACITY);
	    if(capacityList!=null && capacityList.size()>0){
	    	CONCURRENT_CAPACITY = Integer.getInteger("appframe.ftp.concurrentCapacity", Integer.parseInt(capacityList.get(0).getParamCode())).intValue();
	    }else{
	    	CONCURRENT_CAPACITY = Integer.getInteger("appframe.ftp.concurrentCapacity", 50).intValue();
	    }
	    List<SmSysDict> concuList=SmSysDictService.getSmSysDictList(CommonConstants.ftpParam.FTP_CONCURRENT_ACQUIRE_TIMEOUT_SECONDS);
	    if(concuList!=null && concuList.size()>0){
	    	 CONCURRENT_ACQUIRE_TIMEOUT_SECONDS = Integer.getInteger("appframe.ftp.concurrentCapacityAcquireTimeoutSeconds", Integer.parseInt(concuList.get(0).getParamCode())).intValue();
	    }else{
	    	 CONCURRENT_ACQUIRE_TIMEOUT_SECONDS = Integer.getInteger("appframe.ftp.concurrentCapacityAcquireTimeoutSeconds", 10).intValue();
	    }
	    
	    log.debug("ftp并发容量" + CONCURRENT_CAPACITY + "个");
	    log.debug("ftp并发容量acquire超时(秒)" + CONCURRENT_ACQUIRE_TIMEOUT_SECONDS);
	    return new ConcurrentCapacity(CONCURRENT_CAPACITY, CONCURRENT_ACQUIRE_TIMEOUT_SECONDS);
	  }

	@Override
	public Long fileUpload(HttpServletRequest request,String phone, String note, String jobCode) throws Exception {
		SmFile smfile=null;
		
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 获取multipartRequest中所有的文件名
				Iterator iter = multipartRequest.getFileNames();
				 while (iter.hasNext()) {
					MultipartFile file = multipartRequest.getFile(iter.next().toString());
					if(file==null){
						throw new SystemException(StatusCodeForFrame.FILE_UPLOAD_FAILURE,"上传文件不存在!");
					}
					List<SmJobConfig> smJobConfigs=SmJobConfigService.getSmJobConfig(jobCode);
					if(smJobConfigs!=null && smJobConfigs.size()>0){
						//链接ftp
						//上传文件
						smfile=new SmFile();
						smfile.setFileName(file.getOriginalFilename());
						smfile.setCreateDate(DateUtil.getServerDate());
						smfile.setDealState(CommonConstants.SmFileState.SM_fILE_STATE_U);
						//操作员信息
						SmOperModel operModel=SessionInfo.getOperInfo();
						if(operModel!=null){
							smfile.setCreateOperId(String.valueOf(operModel.getOperId()));
							smfile.setCreateOrgId(operModel.getOrgId());
						}
						smfile.setJobCode(jobCode);
						smfile.setNote(note);
						smfile.setPhone(phone);
						// 创建保存的记录
						SmFileService.insert(smfile);
						//上传文件
						FileUploadService.upload(request, jobCode, String.valueOf(smfile.getFileId()), "");
						
					}
				}	
			}	
			return smfile.getFileId();
		} catch (IOException e) {
			throw e;
		}finally{
			if(this.client!=null){
				this.close();
			}
		}
	}
}
