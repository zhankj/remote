package cn.cttic.sysframe.frame.listener;

import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.domain.SmLogon;
import cn.cttic.sysframe.frame.domain.SmLogonLog;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmLogonService;
import cn.cttic.sysframe.frame.service.SystemService;


public class SessionListener implements HttpSessionListener{
	
	@Override
    public void sessionCreated(HttpSessionEvent se) {
	    
    }
	/**
	 * session失效时调用
	 *
	 * @param event 
	 * @author yangrui
	 * @date 2014-6-18 下午4:16:43
	 */
	@Override
    public void sessionDestroyed(HttpSessionEvent event) {
		Map<String,Map> sessionInfo =  (Map<String, Map>) event.getSession().getAttribute(SessionInfo.SESSION_INFO);
		if (sessionInfo == null) return;
		Map map = (Map) sessionInfo.get(SessionInfo.SESSION_SYSTEM_PRE + SessionInfo.SESSION_SYSTEM_ID);
		SmOperModel smOper = (SmOperModel) map.get(SessionInfo.OPER_INFO);
		
		SystemService systemService = (SystemService)SpringContextUtil.getBean(SystemService.class);
		SmLogonService smLogonService = (SmLogonService)SpringContextUtil.getBean(SmLogonService.class);
		
		
		SmLogon logon = smLogonService.selectByPrimaryKey(smOper.getOperId());
		if(logon!=null){
			logon.setOperCode(smOper.getOperCode());
			logon.setOperId(smOper.getOperId());
			logon.setStaffId(smOper.getStaffId());
			logon.setLogonDate(systemService.getSystemDate());
			logon.setState(FrameConstants.SmLogon.LogonStatus.OUTLINE);
			
			//登录日志表
			SmLogonLog logonLog = new SmLogonLog();
			//日志表id
			Long logId = systemService.generateId("SM_LOGON_LOG", "LOG_ID");
			logonLog.setLogId(logId);
			logonLog.setOperId(smOper.getOperId());
			logonLog.setOperCode(smOper.getOperCode());
			logonLog.setStaffId(smOper.getStaffId());
			//登录成功
			logonLog.setResultFlag(FrameConstants.SmLogonLog.ResultFlag.SUCCESS);
			logonLog.setLogonType(FrameConstants.SmLogonLog.LogonType.SESSION_OUT);
			logonLog.setLogonDate(systemService.getSystemDate());
			logonLog.setClientIp(logon.getClientIp());
			
			
			smLogonService.saveLog(logon, logonLog, true);
		}
		
    }

}
