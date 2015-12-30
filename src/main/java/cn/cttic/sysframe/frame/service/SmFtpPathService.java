package cn.cttic.sysframe.frame.service;

import java.util.HashMap;
import java.util.List;

import cn.cttic.sysframe.frame.domain.SmFtpPath;

public interface SmFtpPathService {
	/**
	 * 
	 
	* @Title: SmFtpService
	 
	* @Description: TODO(将sm_ftp_path加入缓存)
	 
	* @param @return    设定文件
	 
	* @return    返回值
	 
	* @throws
	 */
	public HashMap<String, List<SmFtpPath>> loadSmFtpPathCache() ;
	
	public List<SmFtpPath> getSmFtpPath(String ftpPathCode);
}
