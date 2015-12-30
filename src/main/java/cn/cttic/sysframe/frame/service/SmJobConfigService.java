package cn.cttic.sysframe.frame.service;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.frame.domain.SmFile;
import cn.cttic.sysframe.frame.domain.SmFileExample;
import cn.cttic.sysframe.frame.domain.SmJobConfig;
import cn.cttic.sysframe.frame.domain.SmJobLog;
import cn.cttic.sysframe.frame.domain.SmJobLogExample;

public interface SmJobConfigService {
	/**
	 * 
	 
	* @Title: SmFtpService
	 
	* @Description: TODO(将Sm_Job_Config表加入缓存)
	 
	* @param @return    设定文件
	 
	* @return    返回值
	 
	* @throws
	 */
	public HashMap<String, List<SmJobConfig>> loadSmJobConfigCache() ;
	
	public List<SmJobConfig> getSmJobConfig(String jobCode);
	
	 PageList<SmFile> selectByExample(SmFileExample example,PageBounds bounds);
	 
	 PageList<SmJobLog> selectByExample(SmJobLogExample example,PageBounds bounds);
}
