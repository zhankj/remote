package cn.cttic.sysframe.frame.dao;

import java.util.Map;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmBulletinRange;
import cn.cttic.sysframe.frame.domain.SmBulletinRangeExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmBulletinRangeMapper extends BaseMapper<Integer, SmBulletinRange, SmBulletinRangeExample> {

	public PageList<Map<String,Object>> querySelector(Map<String,Object> map, PageBounds pageBounds);
}