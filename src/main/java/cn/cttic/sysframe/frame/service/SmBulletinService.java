package cn.cttic.sysframe.frame.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.cttic.sysframe.frame.domain.SmBulletin;
import cn.cttic.sysframe.frame.domain.SmBulletinExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmBulletinService {

	public PageList<SmBulletin> queryPage(SmBulletinExample example, PageBounds pageBounds);
	
	public SmBulletin queryBean(Integer bulletinId);
	
	public int insert(HttpServletRequest request, SmBulletin record) throws Exception;
	
	public int updateByPrimaryKeySelective(HttpServletRequest request, SmBulletin record) throws Exception;
	
	public PageList<SmBulletin> queryOperPage(Map<String, Object> map, PageBounds pageBounds);
}
