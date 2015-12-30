package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmFile;
import cn.cttic.sysframe.frame.domain.SmFileExample;

public interface SmFileService {

	public  List<SmFile> selectByExample(SmFileExample example);
	
	public   int insert(SmFile record);
	
	public  int updateByPrimaryKeySelective(SmFile record);
}
