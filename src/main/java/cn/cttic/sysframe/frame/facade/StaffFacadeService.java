package cn.cttic.sysframe.frame.facade;

import cn.cttic.sysframe.common.model.DataGridModel;
import cn.cttic.sysframe.frame.domain.SmStaff;

import com.github.miemiedev.mybatis.paginator.domain.PageList;


public interface StaffFacadeService {
	
	public PageList<SmStaff> getClientManager(DataGridModel model,SmStaff staff,String orgId) throws Exception;
}
