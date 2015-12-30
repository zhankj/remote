package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmRegisteredService;
import cn.cttic.sysframe.frame.domain.SmRegisteredServiceExample;


public interface SmRegisteredServiceService {
	
	public  List<SmRegisteredService> selectByExample(SmRegisteredServiceExample example);
}
