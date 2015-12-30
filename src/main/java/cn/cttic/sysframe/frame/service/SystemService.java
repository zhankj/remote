package cn.cttic.sysframe.frame.service;

import java.util.Date;
import java.util.List;

public interface SystemService {

	public Long generateId(String tableName, String columnName);

	public Date getSystemDate();

	public List<String> getTablePkColumnName(String tableName);

	String getCurrentSystemCode();

	String getCurrentSystemInstanceId();
}
