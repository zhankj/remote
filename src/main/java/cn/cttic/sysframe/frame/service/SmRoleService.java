package cn.cttic.sysframe.frame.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.cttic.sysframe.common.model.DataGridModel;
import cn.cttic.sysframe.frame.domain.SmFuncSet;
import cn.cttic.sysframe.frame.domain.SmRole;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SmRoleService {

	public PageList<SmRole> queryRole(SmRole role, String fsId, String signId, PageBounds pageBounds);
	public SmRole loadRoleById(String roleId);
	public int addRole(SmRole role);
	public int removeRoleById(String roleId);
	public int modifyRole(SmRole role);
	PageList<SmFuncSet> queryFuncSetByRoleId(String roleId, Boolean exclude, SmFuncSet funcSet, PageBounds pageBounds);
	void assignFuncSet(String roleId, List<String> fsIds);
	void unassignFuncSet(String roleId, List<String> fsIds);
	public PageList<SmRole> selectByExample(HttpServletRequest request,SmRole smRole,DataGridModel model);

	
}
