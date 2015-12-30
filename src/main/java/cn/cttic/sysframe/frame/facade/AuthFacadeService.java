package cn.cttic.sysframe.frame.facade;

import cn.cttic.sysframe.frame.model.SmOperModel;


public interface AuthFacadeService {
	SmOperModel login(String operCode, String loginPwd);
}
