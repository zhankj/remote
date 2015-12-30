package cn.cttic.sysframe.frame.service;

import java.util.List;

import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.frame.domain.SmOrgan;
import cn.cttic.sysframe.frame.domain.SmOrganExample;

public interface SmOrganService {

	public List<SmOrgan> selectByExample();
	
	public List<SmOrgan> getBeans(SmOrganExample smOrganCriteria);
	
	public TreeModel getTree(String state, boolean chain);
	
	public TreeModel getRightTree(String state, String rootOrgId);
	
	public TreeModel getRightTree(String rootOrgId, String orgPtype, String orgType, boolean chain);
	
	public SmOrgan selectByPrimaryKey(String orgId);
	
	public int insert(SmOrgan record);
	
	public int updateByPrimaryKeySelective(SmOrgan record);
	
	public List<SmOrgan> queryTree(String orgId);
}
