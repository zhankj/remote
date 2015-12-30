package cn.cttic.sysframe.frame.facade;

import cn.cttic.sysframe.frame.model.SmOperModel;

public interface OperatorSessionFacadeService {

	/**
	 * 初始化登录操作员相关信息
	 * @param operCode
	 * @return
	 */
	SmOperModel initOperatorSession(String operCode);
	
}
