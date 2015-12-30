package cn.cttic.sysframe.frame.dao;

import java.util.Map;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmFuncSet;
import cn.cttic.sysframe.frame.domain.SmFuncSetExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmFuncSetMapper extends BaseMapper<String, SmFuncSet, SmFuncSetExample> {
	PageList<SmFuncSet> queryPage(SmFuncSetExample example, PageBounds pageBounds);
	
	PageList<SmFuncSet> queryPageByMap(Map qryMap, PageBounds pageBounds);
	
	PageList<SmFuncSet> selectByRoleId(Map<String,Object> map, PageBounds pageBounds);
}