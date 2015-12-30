package cn.cttic.sysframe.frame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.util.SessionInfo;


public class LoginAuthInterceptor extends HandlerInterceptorAdapter {
	private static Logger LOG = LoggerFactory.getLogger(LoginAuthInterceptor.class);

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    try {
	        SessionInfo.getOperInfo();
        }
        catch (SystemException e) {
	        LOG.warn("没有找到登录信息", e);
	        throw e;
        }
		return super.preHandle(request, response, handler);
    }

}
