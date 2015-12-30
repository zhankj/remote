package cn.cttic.sysframe.frame.service;

import java.util.Map;

import cn.cttic.sysframe.frame.domain.SmFuncSet;
import cn.cttic.sysframe.frame.domain.SmFuncSetExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmFuncSetService {

	public PageList<SmFuncSet> queryPage(SmFuncSetExample example, PageBounds pageBounds);

	public SmFuncSet selectByPrimaryKey(String fsId);

	public int insert(SmFuncSet record);

	public int updateByPrimaryKeySelective(SmFuncSet record);

	public int insertRel(SmFuncSet record, String[] menuNodeIds);

	public int updateRel(SmFuncSet record, String[] menuNodeIds);
	
	public PageList<SmFuncSet> queryPageByMap(Map qryMap, PageBounds pageBounds);
	
	public String validateBusi(String fsId);
}
