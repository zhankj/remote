package cn.cttic.sysframe.common.timertask;

import java.util.Timer;

import org.apache.log4j.Logger;

import java.util.Date;

public class SysFrameManageTimer {

	private static Logger log = Logger.getLogger(SysFrameManageTimer.class);
	
	public SysFrameManageTimer(String systemId,long refresh_period,String instanceId) {
		
		log.debug(" 管理启动开始***************************************************************");		
    	log.debug(" instanceId ="+instanceId+"***************************************************************");
    	
		Timer timer ;
		SysFrameTimerManageTask task;
		timer = new Timer();
		task = new SysFrameTimerManageTask();
		task.setNum(0);
		task.setSystem_id(systemId);
		task.setInstanceId(instanceId);
		timer.schedule(task, new Date(),refresh_period * 60 * 1000);
		
		log.debug(" 管理启动完成***************************************************************");		
		
	}
}
