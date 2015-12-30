package cn.cttic.sysframe.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.cttic.sysframe.common.constants.CommonConstants;
import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.ModuleCode;
import cn.cttic.sysframe.common.exception.code.ModuleCodeForFrame;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.support.MultiLanguageSupport;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.facade.OperatorSessionFacadeService;
import cn.cttic.sysframe.frame.model.SmOperModel;

import com.google.common.collect.Maps;

public class SessionInfo {

	public final static String OPER_INFO = "OPER_INFO";

	public final static String OPER_MENU_TREE = "OPER_MENU_TREE";

	public final static String SESSION_SYSTEM_PRE = "SESSION_";

	public final static ModuleCode SESSION_SYSTEM_ID = ModuleCodeForFrame.SYSTEM_MANAGEMENT;

	public final static String SESSION_INFO = "SESSION_INFO";

	public final static String CLEAR_ALL = "CLEAR_ALL";
	
	public final static String LANGUAGE_TYPE="LANGUAGE_TYPE";

	/**
	 * 获取当前请求
	 * @return
	 */
	private static HttpServletRequest getCurrentRequest() {
		if (RequestContextHolder.getRequestAttributes() == null) throw new SystemException("当前线程未绑定请求信息，请检查是否运行在Web环境中", StatusCodeForFrame.INSIDE_ERROR);
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * 检查当前登录的操作员编码，可用于判断是否登录
	 * @throws SystemException 当前操作员编号为空时，抛出 StatusCodeForFrame.UN_LOGIN 类型的异常
	 * @return
	 */
	public static String getLoginOperCode() {
//		String operCode = getCurrentRequest().getRemoteUser();
		/*
		Object ass = getCurrentRequest().getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		if (ass == null) throw new SystemException(StatusCodeForFrame.UN_LOGIN);
		Map<String, Object> user_map = ((Assertion) ass).getPrincipal().getAttributes();
		String operCode = (String) user_map.get("name");// 获取用户名
		
		Log.debug("operCode ==>" + operCode);
		
		if (operCode == null) throw new SystemException(StatusCodeForFrame.UN_LOGIN);*/
		String operCode="";
		return operCode;
	}
	
	/**
	 * 通过operCode获取操作员相关信息（包括权限）
	 * @param operCode
	 * @return
	 */
	private static SmOperModel loadOperInfo(String operCode) {
		OperatorSessionFacadeService service = SpringContextUtil.getBean(OperatorSessionFacadeService.class);
		return service.initOperatorSession(operCode);
	}
	
	/**
	 * 将操作员相关信息加载至Session
	 * @param request
	 * @param response
	 * @param operCode
	 */
	public static void initSessionInfo() {
		
		String operCode = getLoginOperCode();

		Map<String, Map<String, Object>> sessionInfo = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();

		SmOperModel operModel = loadOperInfo(operCode);
		
		if(operModel.getOrgan()==null||StringUtil.isBlank(operModel.getOrgan().getState())||!FrameConstants.SmOrgan.State.VALID.endsWith(operModel.getOrgan().getState())){
			throw new SystemException(CommonConstants.Message.LOGIN_FAIL_ORG_STATUS, StatusCodeForFrame.UN_LOGIN);
		}
		if(operModel.getStaff()==null||StringUtil.isBlank(operModel.getStaff().getState())||!FrameConstants.SmStaff.StaffStatus.NORMAL.endsWith(operModel.getStaff().getState())){
			throw new SystemException(CommonConstants.Message.LOGIN_FAIL_STAFF_STATUS, StatusCodeForFrame.UN_LOGIN);
		}
		
		map.put(SessionInfo.OPER_INFO, operModel);
		
		sessionInfo.put(SessionInfo.SESSION_SYSTEM_PRE+SessionInfo.SESSION_SYSTEM_ID, map);
		getCurrentRequest().getSession().setAttribute(SessionInfo.SESSION_INFO, sessionInfo);
	}
	
	/**
	 * 获取操作员基本信息
	 */
	public static SmOperModel getOperInfo() {
		SmOperModel operInfo = (SmOperModel) getSessionInfo(SESSION_SYSTEM_ID, OPER_INFO);
		if (null == operInfo) {
			throw new SystemException(StatusCodeForFrame.UN_LOGIN);
		}
		return operInfo;
	}

	/**
	 * 写入session数据
	 * 
	 * @param systemId
	 *            系统标识
	 * @param sessionKey
	 *            存储session中的key值
	 * @param object
	 *            存储到session中的对象
	 * @return
	 */
	public static void setSessionInfo(ModuleCode systemId, String sessionKey, Object object) {
		Map<String,Map> sessionInfo = getSessionInfo();
		if (null == sessionInfo || sessionInfo.isEmpty()) {
			throw new SystemException(StatusCodeForFrame.UN_LOGIN);
		}
		Map map = (Map) sessionInfo.get(SESSION_SYSTEM_PRE + systemId);
		if (null == map || map.isEmpty()) {
			map = new HashMap();
		}
		map.put(sessionKey, object);
		sessionInfo.put(SESSION_SYSTEM_PRE + systemId, map);
	}

	/**
	 * 读取session数据
	 * 
	 * @param systemId
	 *            系统标识
	 * @param sessionKey
	 *            session中的key值
	 * @return object
	 */
	public static Object getSessionInfo(ModuleCode systemId, String sessionKey) {
		Map<String,Map> sessionInfo = getSessionInfo();
		if (null == sessionInfo || sessionInfo.isEmpty()) {
			throw new SystemException(StatusCodeForFrame.UN_LOGIN);
		}
		Map map = (Map) sessionInfo.get(SESSION_SYSTEM_PRE + systemId);
		if (null == map || map.isEmpty()) {
			return null;
		}
		return map.get(sessionKey);
	}

	/**
	 * 清除session指定数据（模块内）
	 * 
	 * @param systemId
	 *            系统标识
	 * @param sessionKey
	 *            session中的key值
	 */
	public static void clearSessionInfo(ModuleCode systemId, String sessionKey) {
		Map<String,Map> sessionInfo = getSessionInfo();
		if (null == sessionInfo || sessionInfo.isEmpty()) {
			throw new SystemException(StatusCodeForFrame.UN_LOGIN);
		}
		Map map = (Map) sessionInfo.get(SESSION_SYSTEM_PRE + systemId);
		if (null != map && !map.isEmpty()) {
			map.remove(sessionKey);
		}
	}

	/**
	 * 清除session数据（整个模块）
	 * 
	 * @param systemId
	 *            系统标识
	 */
	public static void clearSessionInfo(ModuleCode systemId) {
		Map<String,Map> sessionInfo = getSessionInfo();
		if (sessionInfo.isEmpty()) {
			throw new SystemException(StatusCodeForFrame.UN_LOGIN);
		}
		sessionInfo.put(SESSION_SYSTEM_PRE + systemId, new HashMap());
	}

	/**
	 * 清除session数据（整个系统）
	 */
	public static void clearSessionAll(String all) {
		if (CLEAR_ALL.equals(all)) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			Map<String, Map> sessionInfo = null;
			if (session == null) {
				throw new SystemException(StatusCodeForFrame.UN_LOGIN);
			} else {
				request.getSession().invalidate();
//				session.setAttribute(SESSION_INFO, new HashMap());
			}
		}
	}
	
	/**
	 * 获取session数据，如果不存在，则抛出 <code> StatusCodeForFrame.UN_LOGIN </code> 未登录异常
	 * @return 
	 * @author yanshaodong
	 * @author caohui
	 * @date Jun 12, 2014 10:40:30 AM
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Map> getSessionInfo() {
		Map<String, Map> sessionInfoMap = loadSessionInfo();
		if (sessionInfoMap == null || sessionInfoMap.isEmpty()) {
			initSessionInfo();
		}

		return loadSessionInfo();
		
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//		Map<String,Map> sessionInfo=null;
//		if(session==null){
//			throw new SystemException(StatusCodeForFrame.UN_LOGIN);
//		}else{
//			sessionInfo=(Map)session.getAttribute(SESSION_INFO);
//		}
//		return sessionInfo;
	}
	
	/**
	 * 获取session数据，如果不存在，返回 <code> null </code>，不抛出异常
	 * @return 
	 * @author caohui
	 * @date Jun 12, 2014 10:39:13 AM
	 */
	@SuppressWarnings("rawtypes")
    public static Map<String, Map> loadSessionInfo() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		} else {
			@SuppressWarnings({ "unchecked", "rawtypes" })
            Map<String, Map> sessionInfo = (Map<String, Map>) session.getAttribute(SESSION_INFO);
			return sessionInfo;
		}
	}


	public static String getLanguageType() {
		return MultiLanguageSupport.getLanguageType();
    }
}
