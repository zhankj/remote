package cn.cttic.sysframe.frame.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.domain.SmOper;
import cn.cttic.sysframe.frame.facade.AuthFacadeService;
import cn.cttic.sysframe.frame.facade.OperatorSessionFacadeService;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmOperService;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;

@Service
@ReflectionInvokable(name="auth")
public class AuthFacadeServiceImpl implements AuthFacadeService {
	
	@Autowired
	SmOperService smOperService;
	
	@Autowired
	OperatorSessionFacadeService operatorSessionFacadeService;
	
	@Override
	@ReflectionInvokable(name="login")
	public SmOperModel login(String operCode, String loginPwd) {
		//验证工号信息是否正确
		SmOper smOper = smOperService.getByAuth(operCode, loginPwd);
		smOper.setLoginPwd(null);
		//获取登录初始化信息
		return operatorSessionFacadeService.initOperatorSession(operCode);
	}
}
