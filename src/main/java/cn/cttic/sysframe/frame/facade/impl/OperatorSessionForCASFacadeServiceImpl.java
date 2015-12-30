package cn.cttic.sysframe.frame.facade.impl;

import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.facade.OperatorSessionFacadeService;
import cn.cttic.sysframe.frame.model.SmOperModel;

//@Service
public class OperatorSessionForCASFacadeServiceImpl implements
		OperatorSessionFacadeService {

	@Override
	public SmOperModel initOperatorSession(String operCode) {
		SmOperModel model = new SmOperModel();
		model.setStaffName("测试专用");
		return null;
	}

}
