package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmFs2Data;
import cn.cttic.sysframe.frame.domain.SmFs2DataExample;
import cn.cttic.sysframe.frame.domain.SmFs2DataKey;

public interface SmFs2DataService {

	public List<SmFs2Data> selectByExample(SmFs2DataExample example);

	public int insertBeans(List<SmFs2Data> records);

	public int deleteBeans(List<SmFs2DataKey> records);
	
	public int checkDataRightsByFsId(String fs_id);
}
