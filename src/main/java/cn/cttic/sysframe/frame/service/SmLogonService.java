package cn.cttic.sysframe.frame.service;

import cn.cttic.sysframe.frame.domain.SmLogon;
import cn.cttic.sysframe.frame.domain.SmLogonLog;



public interface SmLogonService {
	
	
	int insertSelective(SmLogon record);
	
	SmLogon selectByPrimaryKey(String operId);
	
	int updateByPrimaryKeySelective(SmLogon record);

	void saveLog(SmLogon logon, SmLogonLog logonLog, boolean exist);

}
