package cn.cttic.sysframe.frame.dao;

import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.common.basedao.BaseMapper;
import cn.cttic.sysframe.frame.domain.SmRole;
import cn.cttic.sysframe.frame.domain.SmRoleExample;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmRoleMapper extends BaseMapper<String, SmRole, SmRoleExample> {
	PageList<SmRole> selectByExample(SmRoleExample example, PageBounds pageBounds);
	PageList<SmRole> operRoleData(Map<String,Object> map, PageBounds pageBounds);
	List<SmRole> operRoleData(Map<String,Object> map);
	PageList<SmRole> selectBySelectiveBySignId(Map<String,Object> map, PageBounds pageBounds);
	int expireByPrimaryKey(String roleId);
}