package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmArea;
import cn.cttic.sysframe.frame.domain.SmAreaExample;


public interface SmAreaService {

	public List<SmArea> selectByExample(SmAreaExample example);
	
	public SmArea selectByPrimaryKey(String areaId);
}
