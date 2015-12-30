package cn.cttic.sysframe.frame.service;

import cn.cttic.sysframe.frame.domain.SmLogonLog;
import cn.cttic.sysframe.frame.domain.SmLogonLogExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;



public interface SmLogonLogService {
	
	public PageList<SmLogonLog> query(SmLogonLogExample example, PageBounds pageBounds);
	
	int insertSelective(SmLogonLog record);

}
