package cn.cttic.sysframe.frame.service;


import cn.cttic.sysframe.common.exception.SystemException;


public interface SmJobLogService {
	
	public  int insertSelective(long fileId,String desc)  throws SystemException;
}
