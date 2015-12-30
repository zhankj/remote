package cn.cttic.sysframe.frame.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.frame.facade.OrganFacadeService;
import cn.cttic.sysframe.frame.service.SmOrganService;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;

@Service
@ReflectionInvokable(name = "organ")
public class OrganFacadeServiceImpl implements OrganFacadeService {

	@Autowired
	private SmOrganService smOrganService;

	@Override
	@ReflectionInvokable(name = "getTree")
	public TreeModel getTree(String orgStatus, boolean chain) {
		return smOrganService.getTree(orgStatus, chain);
	}

	@Override
	@ReflectionInvokable(name = "getRightTree")
	public TreeModel getRightTree(String rootOrgId, String orgPtype, String orgType, boolean chain) {
		return smOrganService.getRightTree(rootOrgId, orgPtype, orgType, chain);
	}

	@Override
	@ReflectionInvokable(name = "getRightTreeByState")
	public TreeModel getRightTree(String state, String rootOrgId) {
		return smOrganService.getRightTree(state, rootOrgId);
	}

}
