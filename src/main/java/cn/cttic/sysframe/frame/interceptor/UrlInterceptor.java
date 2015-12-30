package cn.cttic.sysframe.frame.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.domain.SmMenu;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmMenuService;

public class UrlInterceptor extends HandlerInterceptorAdapter {
	private static Logger LOG = LoggerFactory.getLogger(UrlInterceptor.class);
	
	@Autowired
	private SmMenuService smMenuService;

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    try {
	    	// 获取当前请求URL
	    	
	    	String url = request.getRequestURL().append('?').append(request.getQueryString()).toString();
	    	// 获取ContextPath
	    	String contextPath = request.getContextPath();
	    	// 获取相对url
	    	String relativelyUrl = url.substring(contextPath.length() + 1);
	    	// 获取所有菜单级权限
	    	List<SmMenu> menuList = smMenuService.selectByExample();
	    	for (SmMenu bean : menuList) {
	    		// 功能菜单级权限参与判断
	    		if (FrameConstants.SmMenu.MenuType.FUNCTION.equals(bean.getMenuType()) && FrameConstants.SmMenu.State.USABLE.equals(bean.getState())) {
	    			// 如果当前请求URL在功能集内则参与判断
	    			if (!StringUtil.isBlank(bean.getMenuUrl()) && bean.getMenuUrl().contains(relativelyUrl)) {
	    				// 获取菜单Url
	    				String menuUrl = bean.getMenuUrl();
	    				// 获取当前登录操作员信息
	    				SmOperModel operInfo = SessionInfo.getOperInfo();
	    				// 获取操作员菜单权限
	    				Map<String, String> menuRigthMap = operInfo.getMenuRigthMap();
	    				LOG.debug("--------------------url=[" + url + "]--------------------");
	    				// 如果操作员菜单权限中不包含此权限
	    				if (!menuRigthMap.containsKey(menuUrl)) {
	    					// 跳转到登录页面
	    					LOG.debug("--------------------ContextPath=[" + request.getContextPath() + "]--------------------");
	    					response.sendRedirect(request.getContextPath());
	    					return false;
	    				}
	    				// 判断完成后跳出循环
	    				break;
	    			}
	    		}
	    	}
        }
        catch (SystemException e) {
	        throw e;
        }
		return super.preHandle(request, response, handler);
    }
}
