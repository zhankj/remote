package cn.cttic.sysframe.common.timertask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import cn.cttic.sysframe.common.configuration.SysConfiguration;


public class SysFrameTimerServlet extends HttpServlet {

	private static final Logger logger = Logger
			.getLogger(SysFrameTimerServlet.class);

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		try {
			
			// 读取配置文件
			String systemId =SysConfiguration.INSTANCE.SYSTEM_ID;
			String refresh_period = SysConfiguration.INSTANCE.TIMER_REFRESH_PERIOD;
	    	String instanceId=SysConfiguration.INSTANCE.INSTANCE_ID;
	    	
			long refresh_period_long=0;
			try{
			   refresh_period_long=new Long(refresh_period);
			}catch(Exception e){
				refresh_period_long=30;
			}
			TimerUtil.sysFrameTimerMap = new HashMap<String,List<Map>>();
			logger.debug("systemId="+systemId+"****refresh_period_long="+refresh_period_long+"***********************************************************");
			if(!TimerUtil.isBlank(systemId)){
				logger.debug("定时器启动开始***************************************************************");
				   new SysFrameManageTimer(systemId,refresh_period_long,instanceId);
				logger.debug("定时器启动完成***************************************************************");
			}else{
				logger.debug("systemId为空定时器不启动***************************************************************");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
