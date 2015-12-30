package cn.cttic.sysframe.frame.service;

import java.util.HashMap;
import java.util.List;

import cn.cttic.sysframe.frame.domain.SmFtp;

public interface SmFtpService {
	/**
	 * 
	 
	* @Title: SmFtpService
	 
	* @Description: TODO(将ftp_path表加入缓存)
	 
	* @param @return    设定文件
	 
	* @return    返回值
	 
	* @throws
	 */
	public HashMap<String, List<SmFtp>> loadSmFtpCache() ;
	
	public List<SmFtp> getSmFtp(String ftpCode);
	

	

	
	
		
}
