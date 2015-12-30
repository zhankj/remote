package cn.cttic.sysframe.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.dao.SmRegisteredServiceMapper;
import cn.cttic.sysframe.frame.domain.SmRegisteredService;
import cn.cttic.sysframe.frame.domain.SmRegisteredServiceExample;
import cn.cttic.sysframe.frame.service.SmRegisteredServiceService;

@Service
public class SmRegisteredServiceServiceImpl implements SmRegisteredServiceService {

	@Autowired
	private SmRegisteredServiceMapper registeredServiceMapper;
	
	
	public List<SmRegisteredService> selectByExample(SmRegisteredServiceExample example){
		
		return registeredServiceMapper.selectByExample(example);
	}
}
