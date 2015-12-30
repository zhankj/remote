package cn.cttic.sysframe.frame.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.model.DataGridModel;
import cn.cttic.sysframe.common.util.CamelCaseUtil;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.dao.SmFuncSetMapper;
import cn.cttic.sysframe.frame.dao.SmRole2FsMapper;
import cn.cttic.sysframe.frame.dao.SmRoleMapper;
import cn.cttic.sysframe.frame.domain.SmFuncSet;
import cn.cttic.sysframe.frame.domain.SmRole;
import cn.cttic.sysframe.frame.domain.SmRole2FsKey;
import cn.cttic.sysframe.frame.domain.SmRoleExample;
import cn.cttic.sysframe.frame.service.SmRoleService;
import cn.cttic.sysframe.frame.service.SystemService;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class SmRoleServiceImpl implements SmRoleService {
	
	@Autowired
	SmRoleMapper smRoleMapper;
	
	@Autowired
	SmFuncSetMapper smFuncSetMapper;
	
	@Autowired
	SmRole2FsMapper smRole2FsMapper;
	
	@Autowired
	private SystemService systemService;

	@Override
	public PageList<SmRole> queryRole(SmRole role, String fsId, String signId, PageBounds pageBounds) {
		if (role != null && role.getRoleName() != null && !role.getRoleName().trim().equals("")) 
			role.setRoleName(new StringBuilder("%").append(role.getRoleName()).append("%").toString());
		if (role != null && role.getRoleDesc() != null && !role.getRoleDesc().trim().equals("")) 
			role.setRoleDesc(new StringBuilder("%").append(role.getRoleDesc()).append("%").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("fsId", fsId);
		map.put("signId", signId);
		return smRoleMapper.selectBySelectiveBySignId(map, pageBounds);
	}

	@Override
	public SmRole loadRoleById(String roleId) {
		return smRoleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public int addRole(SmRole role) {
		try {
			role.setRoleId(systemService.generateId("SM_ROLE", "ROLE_ID").toString());
			//更改数据库约束
//			if (role.getRoleOrderNo() == null) role.setRoleOrderNo((short)1);
			if (role.getEffDate() == null) role.setEffDate(new Date());
			if (role.getExpDate() == null) role.setExpDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2029-12-31 23:59:59"));
			return smRoleMapper.insertSelective(role);
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}

	@Override
	public int modifyRole(SmRole role) {
		return smRoleMapper.updateByPrimaryKeySelective(role);
	}
	
	@Override
	public int removeRoleById(String roleId) {
		try {
			return smRoleMapper.expireByPrimaryKey(roleId);
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public PageList<SmFuncSet> queryFuncSetByRoleId(String roleId, Boolean exclude, SmFuncSet funcSet, PageBounds pageBounds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exclude", exclude);
		map.put("roleId", roleId);
		map.put("record", funcSet);
		if (funcSet!=null && funcSet.getFsName()!=null && !funcSet.getFsName().equals("")) 
			funcSet.setFsName(new StringBuilder("%").append(funcSet.getFsName()).append("%").toString());
		if (funcSet!=null && funcSet.getFsDesc()!=null && !funcSet.getFsDesc().equals("")) 
			funcSet.setFsDesc(new StringBuilder("%").append(funcSet.getFsDesc()).append("%").toString());
		
		try {
			return smFuncSetMapper.selectByRoleId(map, pageBounds);
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	
	@Override
	public void assignFuncSet(String roleId, List<String> fsIds) {
		SmRole2FsKey rf = new SmRole2FsKey();
		rf.setRoleId(roleId);
		for (String fsId : fsIds) {
			rf.setFsId(fsId);
			try {
				smRole2FsMapper.insert(rf);
			} catch (Exception e) {
				throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
			}
		}
	}
	
	@Override
	public void unassignFuncSet(String roleId, List<String> fsIds) {
		SmRole2FsKey rf = new SmRole2FsKey();
		rf.setRoleId(roleId);
		for (String fsId : fsIds) {
			rf.setFsId(fsId);
			try {
				smRole2FsMapper.deleteByPrimaryKey(rf);
			} catch (Exception e) {
				throw new SystemException(e, StatusCodeForFrame.DB_OPERATION_ERROR);
			}
		}
	}
	
	@Override
	public PageList<SmRole> selectByExample(HttpServletRequest request,SmRole smRole,DataGridModel model){
		// 设定分页与段排序
		PageBounds pageBounds = new PageBounds();
		pageBounds = CamelCaseUtil.configPageBounds(request, pageBounds, model,
				"cn.cttic.sysframe.frame.dao.SmRoleMapper.selectByExample",
				CamelCaseUtil.SQL_SESSION_FACTORY);
		SmRoleExample example = new SmRoleExample();
		SmRoleExample.Criteria criteria = example.or();
		if (!StringUtil.isBlank(smRole.getRoleName())){
			criteria.andRoleNameEqualTo(smRole.getRoleName());
		}
		if (!StringUtil.isBlank(smRole.getState())){
			criteria.andRoleNameEqualTo(smRole.getRoleName());
		}
		return smRoleMapper.selectByExample(example, pageBounds);
	}

	
}
