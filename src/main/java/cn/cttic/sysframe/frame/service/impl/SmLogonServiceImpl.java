package cn.cttic.sysframe.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.frame.dao.SmLogonLogMapper;
import cn.cttic.sysframe.frame.dao.SmLogonMapper;
import cn.cttic.sysframe.frame.domain.SmLogon;
import cn.cttic.sysframe.frame.domain.SmLogonLog;
import cn.cttic.sysframe.frame.service.SmLogonService;

@Service
public class SmLogonServiceImpl implements SmLogonService {
	
	@Autowired
	private SmLogonMapper smLogonMapper;
	
	@Autowired
	private SmLogonLogMapper smLogonLogMapper;

	@Override
	public int insertSelective(SmLogon record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SmLogon selectByPrimaryKey(String operId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SmLogon record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveLog(SmLogon logon, SmLogonLog logonLog, boolean exist) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
