package cn.cttic.sysframe.frame.facade;

import java.util.Date;


public interface SystemFacadeService {
	Date getCurrentDateFromDatabase();
	String generateId(String tableName, String columnName);
	void setLocale(String locale);
}
