package cn.cttic.sysframe.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;

/**
 * RequestContextSupport 用于从当前线程中获取 HttpServletRequest 与 HttpServletResponse <br/>
 * @author caohui@cttic.cn
 * @date Aug 12, 2014 1:58:02 PM
 * @copyright: Copyright (c) 2014 CTTIC
 */
public class RequestContextSupport {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public static ThreadLocal<RequestContextSupport> INSTANCE_IN_THREAD = new ThreadLocal<RequestContextSupport>();
	
	private RequestContextSupport(HttpServletRequest request, HttpServletResponse response) {
	    super();
	    this.request = request;
	    this.response = response;
    }

	/**
	 * 初始化实例到当前线程
	 *
	 * @param request
	 * @param response
	 * @return 
	 * @author caohui
	 * @date Aug 12, 2014 1:58:15 PM
	 */
	public static RequestContextSupport initInstanceToCurrentThread(HttpServletRequest request, HttpServletResponse response) {
		RequestContextSupport context = new RequestContextSupport(request, response);
		RequestContextSupport.INSTANCE_IN_THREAD.set(context);
		return context;
	}
	
	/**
	 * 从当前线程中获取实例
	 *
	 * @return 
	 * @author caohui
	 * @date Aug 12, 2014 2:00:47 PM
	 */
	public static RequestContextSupport getInstanceFromCurrentThread() {
		RequestContextSupport result = RequestContextSupport.INSTANCE_IN_THREAD.get();
		if (result == null) throw new SystemException(StatusCodeForFrameCore.INSIDE_ERROR, "RequestContextSupport is uninitialized");
		return result;
	}

    public HttpServletRequest getRequest() {
    	return request;
    }
	
    public HttpServletResponse getResponse() {
    	return response;
    }
    
    public static class RequestContextSupportInterceptor extends HandlerInterceptorAdapter {
    	@Override
    	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    		initInstanceToCurrentThread(request, response);
    		return super.preHandle(request, response, handler);
    	}
    }
	
}
