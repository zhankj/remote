package cn.cttic.sysframe.frame.dao;

import java.util.Map;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmBulletin;
import cn.cttic.sysframe.frame.domain.SmBulletinExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmBulletinMapper extends BaseMapper<Integer, SmBulletin, SmBulletinExample> {
	public PageList<SmBulletin> queryPage(SmBulletinExample example, PageBounds pageBounds);
	
	public PageList<SmBulletin> queryOperPage(Map<String, Object> map, PageBounds pageBounds);
}