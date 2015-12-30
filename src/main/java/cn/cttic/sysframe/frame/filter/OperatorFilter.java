package cn.cttic.sysframe.frame.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.frame.model.SmOperModel;

/**
 * 记录当前使用者的信息到log4j.MDC
 * 
 * @author 付加朕
 * @date 2014年9月4日 下午10:02:30
 */
public class OperatorFilter implements Filter {

	private final static String DEFAULT_OPER_CODE = "000000";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req=(HttpServletRequest)request;
		    HttpSession session= req.getSession();
			Map<String,Map> sessionInfo =  (Map<String, Map>) session.getAttribute(SessionInfo.SESSION_INFO);
			Map map = (Map) sessionInfo.get(SessionInfo.SESSION_SYSTEM_PRE + SessionInfo.SESSION_SYSTEM_ID);
			SmOperModel smOper = (SmOperModel) map.get(SessionInfo.OPER_INFO);
			if (smOper == null) {
				MDC.put("operCode", DEFAULT_OPER_CODE);
			} else {
				MDC.put("operCode", smOper.getOperCode());
			}
		} catch (Exception e) {
			MDC.put("operCode", DEFAULT_OPER_CODE);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {

	}

	@Override
	public void destroy() {
		MDC.remove("operCode");
	}

}