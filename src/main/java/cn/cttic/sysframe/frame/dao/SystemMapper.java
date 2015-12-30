package cn.cttic.sysframe.frame.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SystemMapper {

	public String getSequenceName(Map<String, String> map);

	public Long generateId(Map<String, String> param);

	public Date getSystemDate();

	public List<String> getTablePkColumnName(String tableName);

}