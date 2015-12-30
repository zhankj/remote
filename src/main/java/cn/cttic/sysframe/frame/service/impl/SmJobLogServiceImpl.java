package cn.cttic.sysframe.frame.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.frame.dao.SmJobLogMapper;
import cn.cttic.sysframe.frame.domain.SmJobLog;
import cn.cttic.sysframe.frame.service.SmJobLogService;
@Service
public class SmJobLogServiceImpl implements SmJobLogService {

	@Autowired
	private  SmJobLogMapper SmJobLogMapper;
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int insertSelective(long fileId,String desc) throws SystemException{
		SmJobLog jobLog=new SmJobLog();
		jobLog.setFileId(fileId);
		jobLog.setCreateDate(DateUtil.getServerDate());
		jobLog.setLogDesc(desc);
		return SmJobLogMapper.insertSelective(jobLog);
	}
	
	
	
	
}
