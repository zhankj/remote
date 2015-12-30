package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmCounty;
import cn.cttic.sysframe.frame.domain.SmCountyExample;


public interface SmCountyService {

	public List<SmCounty> selectByExample(SmCountyExample example);
	
	public SmCounty selectByPrimaryKey(String countyId);
}
