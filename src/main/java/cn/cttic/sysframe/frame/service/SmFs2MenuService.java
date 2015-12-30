package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.frame.domain.SmFs2MenuExample;
import cn.cttic.sysframe.frame.domain.SmFs2MenuKey;

public interface SmFs2MenuService {

	public List<SmFs2MenuKey> selectByExample(SmFs2MenuExample example);

	public int insertBeans(SmFs2MenuExample example, List<SmFs2MenuKey> records);

	public int delete(SmFs2MenuExample example);
	
	public int checkMenusByFsId(String fs_id);
}
