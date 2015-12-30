package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmTown;
import cn.cttic.sysframe.frame.domain.SmTownExample;


public interface SmTownService {

	public List<SmTown> selectByExample(SmTownExample example);
	
	public SmTown selectByPrimaryKey(String townId);
}
