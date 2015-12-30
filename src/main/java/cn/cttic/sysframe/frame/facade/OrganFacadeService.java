package cn.cttic.sysframe.frame.facade;

import cn.cttic.sysframe.common.model.TreeModel;

public interface OrganFacadeService {

	public TreeModel getTree(String orgStatus, boolean chain);

	public TreeModel getRightTree(String rootOrgId, String orgPtype, String orgType, boolean chain);

	public TreeModel getRightTree(String state, String rootOrgId);
}
