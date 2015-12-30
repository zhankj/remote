package cn.cttic.sysframe.frame.facade.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.support.MultiLanguageSupport;
import cn.cttic.sysframe.common.support.RequestContextSupport;
import cn.cttic.sysframe.frame.facade.SystemFacadeService;
import cn.cttic.sysframe.frame.service.SystemService;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;

@Service
@ReflectionInvokable(name="system")
public class SystemFacadeServiceImpl implements SystemFacadeService {
	
	@Autowired
	SystemService systemService;

	@Override
	@ReflectionInvokable(name="current_date")
	public Date getCurrentDateFromDatabase() {
		return systemService.getSystemDate();
	}
	
	@Override
	@ReflectionInvokable(name="getId")
	public String generateId(String tableName, String columnName)
	{
		return systemService.generateId(tableName, columnName).toString();
	}
	
	@Override
	@ReflectionInvokable(name="set_locale")
	public void setLocale(String locale) {
		HttpServletRequest req = RequestContextSupport.getInstanceFromCurrentThread().getRequest();
		HttpServletResponse resp = RequestContextSupport.getInstanceFromCurrentThread().getResponse();
		MultiLanguageSupport.setLanguageType(locale, resp, req);
	}

}
