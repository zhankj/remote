package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmDocDoneLog;
import cn.cttic.sysframe.frame.domain.SmDocDoneLogExample;

/**
 * 附件管理-文档下载日志记录接口. <br>
 * 类详细说明.
 * 
 * @author fujiazhen@cttic.cn
 * @date 2014-4-19 下午2:40:59 <br>
 *       Copyright: Copyright (c) 2014 CTTIC
 */
public interface SmDocDoneLogService {

	/**
	 * 新增文档下载日志
	 * 
	 * @param record
	 *            下载日志
	 * @return
	 * @author fujz
	 * @date 2014-4-19 下午2:44:29
	 */
	public int insert(SmDocDoneLog record);

	/**
	 * 按条件获取日志记录
	 * 
	 * @param example
	 * @return
	 * @author fujz
	 * @date 2014-4-19 下午2:45:39
	 */
	public List<SmDocDoneLog> selectByExample(SmDocDoneLogExample example);
}
