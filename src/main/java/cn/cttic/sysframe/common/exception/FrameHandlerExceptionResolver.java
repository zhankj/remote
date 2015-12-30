package cn.cttic.sysframe.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.cttic.sysframe.common.configuration.SysConfiguration;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.model.ExceptionModel;
import cn.cttic.sysframe.common.support.MultiLanguageSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller 统一异常处理类
 * @author caohui
 *
 */
public class FrameHandlerExceptionResolver implements HandlerExceptionResolver {
	final Logger LOG = LoggerFactory.getLogger(FrameHandlerExceptionResolver.class);

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		LOG.error("截获异常", ex);
		
//		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		ExceptionModel exceptionModel = MultiLanguageSupport.generateExceptionModel(SystemException.wrapException(ex));
		
		if(isJsonRequest(request)) {
			LOG.debug("json请求，返回异常的json形式");
			
			//设置response的header，如果不设置contentType，则默认是text/html，则json串在前端被解析为string，而不是object
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			PrintWriter writer = null;
			try {  
                writer = response.getWriter();
                new ObjectMapper().writeValue(writer, exceptionModel);
                writer.flush();
            } catch (IOException e) {
            	LOG.error("写入response失败", e);
				e.printStackTrace();
			} finally {
            	if (writer != null) writer.close();
            }
            return null;
            
		} else {
			String viewName = "home/error";
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("exception", exceptionModel);
			
			if(SysConfiguration.INSTANCE.SSO_ENABLED)  {
//				model.put("redirect", SysConfiguration.INSTANCE.SSO_LOGOUT_URL);
				//跳转到CAS登录页面
				StringBuffer urlBuffer = request.getRequestURL();
				urlBuffer.delete(urlBuffer.length() - request.getRequestURI().length(), urlBuffer.length()).append(request.getContextPath()).append("/login/logout.htm").toString();
				LOG.debug("urlBuffer==>" + urlBuffer.toString());
				model.put("redirect", urlBuffer.toString());
			}
			
			if (SystemException.wrapException(ex).getStatusCode().equals(StatusCodeForFrame.UN_LOGIN)) {
				LOG.debug("尝试跳转到登录页面");

				if(SysConfiguration.INSTANCE.SSO_ENABLED) {//跳转到CAS登录页面
//					StringBuffer urlBuffer = request.getRequestURL();
//					String urlString = urlBuffer.delete(urlBuffer.length() - request.getRequestURI().length(), urlBuffer.length()).append(request.getContextPath()).toString();
//					LOG.debug("service URL => " + urlString);
//					return new ModelAndView(new StringBuilder("redirect:").append(SysConfiguration.INSTANCE.SSO_LOGIN_URL).append("?service=").append(urlString).toString());
					
					
					
					viewName = "login/sso_redirect";
				}
				else {
					viewName = "login/login";
				}
			}
			LOG.debug("非JSON请求，直接设置 exception model");
			
			ModelAndView mav = new ModelAndView(viewName, model);
			if ("".equals(viewName)) mav.setView(null);
			return mav;
			
		}
	}
	
	
	/**
	 * 判断是否为JSON请求
	 * @param request
	 * @return
	 */
	private boolean isJsonRequest(HttpServletRequest request) {
		return request.getHeader("accept").indexOf("application/json") != -1 ||
				request.getRequestURI().toLowerCase().endsWith(".json");
//		return request.getHeader("accept").indexOf("application/json") != -1 || 
//				(request.getHeader("X-Requested-With")!= null 
//				&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") != -1);
	}

}
