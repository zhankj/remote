package cn.cttic.sysframe.frame.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.model.DataGridModel;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.domain.SmOrgan;
import cn.cttic.sysframe.frame.domain.SmStaff;
import cn.cttic.sysframe.frame.domain.SmStaffExample;
import cn.cttic.sysframe.frame.domain.SmStaffExample.Criteria;
import cn.cttic.sysframe.frame.facade.StaffFacadeService;
import cn.cttic.sysframe.frame.service.SmOrganService;
import cn.cttic.sysframe.frame.service.SmStaffService;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
@ReflectionInvokable(name = "auth")
public class StaffFacadeServiceImpl implements StaffFacadeService {

	@Autowired
	SmStaffService smStaffService;

	@Autowired
	SmOrganService smOrganService;

	@Override
	@ReflectionInvokable(name = "getClientManager")
	public PageList<SmStaff> getClientManager(DataGridModel model, SmStaff staff, String orgId) throws Exception {
		int pageNumber = model.getPage();
		int pageSize = model.getRows();
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(pageSize);
		pageBounds.setPage(pageNumber);

		SmStaffExample sc = new SmStaffExample();
		Criteria criteria = sc.or();
		if (!StringUtil.isBlank(staff.getStaffCode())) {
			criteria.andStaffCodeEqualTo(staff.getStaffCode());
		}
		if (!StringUtil.isBlank(staff.getStaffName())) {
			criteria.andStaffNameLike("%" + staff.getStaffName() + "%");
		}
		if (!StringUtil.isBlank(staff.getStaffPost())) {
			criteria.andStaffPostEqualTo(staff.getStaffPost());
		}
		if (!StringUtil.isBlank(staff.getOrgId())) {
			criteria.andOrgIdEqualTo(staff.getOrgId());
		} else if (!StringUtil.isBlank(orgId)) {
			List<SmOrgan> organBeanList = smOrganService.queryTree(orgId);
			List<String> orgIdList = new ArrayList<String>();
			for (SmOrgan bean : organBeanList) {
				if (FrameConstants.SmOrgan.State.VALID.equals(bean.getState())) {
					orgIdList.add(bean.getOrgId());
				}
			}
			criteria.andOrgIdIn(orgIdList);
		}
		criteria.andStaffTypeEqualTo("02");

		criteria.andStateEqualTo("Y");
		PageList<SmStaff> SmStaffPageList = smStaffService.query(sc, pageBounds);
		return SmStaffPageList;
	}
}
