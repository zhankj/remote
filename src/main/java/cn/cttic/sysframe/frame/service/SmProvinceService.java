package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmProvince;
import cn.cttic.sysframe.frame.domain.SmProvinceExample;


public interface SmProvinceService {

	public List<SmProvince> selectByExample(SmProvinceExample example);
	
	public SmProvince selectByPrimaryKey(String provinceId);
}
