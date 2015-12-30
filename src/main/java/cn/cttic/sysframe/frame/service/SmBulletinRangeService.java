package cn.cttic.sysframe.frame.service;

import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.frame.domain.SmBulletinRange;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmBulletinRangeService {

	public List<SmBulletinRange> queryBeans(Integer bulletinId);
	
	public PageList<Map<String,Object>> querySelector(Map<String,Object> map, PageBounds pageBounds);
	
	public void save(Integer bulletinId, String releaseRange, String operType, List<String> list);
}
