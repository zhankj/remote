package cn.cttic.sysframe.common.timertask;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;
import cn.cttic.sysframe.frame.domain.SmTimerTaskRunLog;
import cn.cttic.sysframe.frame.model.SmTimerTaskModel;
import cn.cttic.sysframe.frame.service.ITimerTask;
import cn.cttic.sysframe.frame.service.SmTimerTaskService;
import cn.cttic.sysframe.frame.service.SystemService;

public class SysFrameTimerTask extends TimerTask{

	private static Logger log = Logger.getLogger(SysFrameTimerTask.class);

	private SmTimerTaskDef taskInfo ; 
	
	Date run_date=null;
	
	//定时器类型 0、实时  1、一次性  2、简单周期类 3、复杂周期类
	private int type = 0; 
	
	private String timerName = ""; 
	
	private String syncTimeFormat = "";
	
	public  String instanceId ="";
	
	public SysFrameTimerTask() {
		// TODO Auto-generated constructor stubs
	}

	@Override
	public void run() {
		synchronized(TimerUtil.sysFrameTimerMap){
			
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】开始执行任务***************************************************************");
			
			//更新定时任务上次执行时间
			Timestamp date=refreshTaskInfo(taskInfo.getTaskId());
			taskInfo.setPreRunTime(date);
			
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】更新配置表上次执行时间信息成功 ***************************************************************");
			
	
			//计算执行时间
			run_date=TimerUtil.runActTime(taskInfo,syncTimeFormat);
			
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】执行时间为 "+DateUtil.format(run_date,"yyyy-MM-dd HH:mm:ss")+" ***************************************************************");	
			if(run_date!=null){
				//实时处理
				if(type==SysFrameTimer.task_type_realTime){
//					if(updateTimerTaskDefine(taskInfo,run_date)){
//						handle();
//					}
				}
				//只执行一次
				else if(type==SysFrameTimer.task_type_one){
					if(TimerUtil.isTimerRun(taskInfo,TimerUtil.sysFrameTimerMap,run_date,false,syncTimeFormat)&&updateTimerTaskDef(taskInfo,run_date)){
						handle();
					}else{
						log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】 不处理业务逻辑 ***************************************************************");
					}
					//结束该定时器
					String msg=TimerUtil.stopTimer(taskInfo.getTaskId().toString(), false,syncTimeFormat);
					if(!TimerUtil.isBlank(msg)){
						msg="主定时器结束失败:"+msg;
						writeTimeRunLog(taskInfo.getTaskId(),msg,3,run_date,instanceId);
					}
					log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】是一次性任务  结束成功 ***************************************************************");
				}
				//简单周期
				else if(type==SysFrameTimer.task_type_simple){
					if(TimerUtil.isTimerRun(taskInfo,TimerUtil.sysFrameTimerMap,run_date,false,syncTimeFormat)&&updateTimerTaskDef(taskInfo,run_date)){
						handle();
					}else{
						log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】 不处理业务逻辑 ***************************************************************");
					}
				}
				//复杂周期
				else if(type==SysFrameTimer.task_type_complex){
					if(TimerUtil.isTimerRun(taskInfo,TimerUtil.sysFrameTimerMap,run_date,true,syncTimeFormat)){
						if(taskInfo.getRunTimeInterval()==null||taskInfo.getRunTimeInterval()==0||taskInfo.getRunNum()==null||taskInfo.getRunNum()==0){
							handle();
						}else{
							handleComplex();
						}
					}
				}
			}
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】执行任务完成***************************************************************");
		}
	}
	
	/**
	 * 普通timer处理方法
	 */
	private void handle(){
		
		if(taskInfo!=null){
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】 开始执行业务逻辑 ***************************************************************");
		}
		//SQL类型 
		if(taskInfo!=null&&taskInfo.getImplType()==SysFrameTimer.impl_type_sql){
			ITimerTask handleImpl = null;
			try {
//				handleImpl = (ITimerTask)TimerUtil.getImplObject("c.SqlTimerTaskImpl");
				
			} catch (Exception e) {
	        	writeTimeRunLog(taskInfo.getTaskId(),"获取实现类出错:"+e.getMessage(),Integer.parseInt("2"),run_date,instanceId);
	        } 
			HashMap map = handleImpl.execute(taskInfo,taskInfo.getTaskCreator());
			String result=(String)map.get(ITimerTask.RESULT);
			String code=(String)map.get(ITimerTask.CODE);
//			String result="成功";
//			String code="1";
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】执行SQL任务完成***************************************************************");
	        writeTimeRunLog(taskInfo.getTaskId(),result,Integer.parseInt(code),run_date,instanceId);
		}
		//自定义方法
		else if(taskInfo!=null&&taskInfo.getImplType()==SysFrameTimer.impl_type_meth){
			//调用实现方法
			ITimerTask handleImpl=null; 
			try {
				 handleImpl = (ITimerTask)TimerUtil.getImplObjectForSpring(taskInfo.getImplMeth().trim());
	        } catch (Exception e) {
	        	writeTimeRunLog(taskInfo.getTaskId(),"获取实现类出错:"+e.getMessage(),Integer.parseInt("2"),run_date,instanceId);
	        } 
	        HashMap map = null;
	        try {
	        	map = handleImpl.execute(taskInfo,taskInfo.getTaskCreator());
	        } catch (Exception e) {
	        	writeTimeRunLog(taskInfo.getTaskId(),"实现类执行出错:"+e.getMessage(),Integer.parseInt("2"),run_date,instanceId);
	        } 
			if(map!=null){
				String result=(String)map.get(ITimerTask.RESULT);
				String code=(String)map.get(ITimerTask.CODE);
				writeTimeRunLog(taskInfo.getTaskId(),result,Integer.parseInt(code),run_date,instanceId);
			}else{
				writeTimeRunLog(taskInfo.getTaskId(),"返回结果为空,无法确认是否执行成功",Integer.parseInt("2"),run_date,instanceId);
			}
		}
		if(taskInfo!=null){
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】 执行业务逻辑结束 ***************************************************************");
		}
	}
	/**
	 * 复杂timer处理方法
	 */
	private void handleComplex(){
		List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfo.getTaskId().toString());
		if(mapList!=null&&mapList.size()>0){
			for (Map taskInfoMap : mapList){
				if(taskInfoMap!=null){
					String syncTimeFormatTemp=(String)taskInfoMap.get(TimerUtil.SYNC_TIME_FORMAT);
					if(!TimerUtil.isBlank(syncTimeFormat)&&syncTimeFormat.equals(syncTimeFormatTemp)){
						if(taskInfoMap!=null){
							
							//1.停止该定时器的子定时器
							String msg=TimerUtil.stopTimer(taskInfo.getTaskId().toString(), true,syncTimeFormat);
							if(!TimerUtil.isBlank(msg)){
								msg="子定时器结束失败:"+msg;
								writeTimeRunLog(taskInfo.getTaskId(),"syncTimeFormat:"+syncTimeFormat+" "+msg,3,run_date,instanceId);
							}else{
							
								//2.重新启动子定时器
								Timer timer ;
								SysFrameTimerChildrenTask task;
								timer = new Timer();
								task = new SysFrameTimerChildrenTask();
								task.setInstanceId(instanceId);
								task.setTaskInfo(taskInfo);
								task.setTimerName(taskInfo.getTaskId().toString());
								task.setSyncTimeFormat(syncTimeFormat);
								task.setNum(0);
								task.setMaxnum(taskInfo.getRunNum());
								task.setRun_date(run_date);
								task.setPeriod_time(taskInfo.getRunTimeInterval() * 60 *1000 );
								task.setFirst_date(run_date);
								timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(syncTimeFormat,taskInfo.getTaskType(),taskInfo.getPeriodType()),taskInfo.getRunTimeInterval() * 60 *1000  );
								
								taskInfoMap.put(TimerUtil.CHILD_OBJECT, timer);
							}
						}
					}
	//				Map map = TimerUtil.sysFrameTimerMap.get(taskInfo.getTaskId().toString());
				}
			}
		}
	}
	/**
	 * 写定时任务执行日志
	 * @return
	 */
	public static void writeTimeRunLog(Long taskId,String result ,int code,Date run_date,String instanceIdStr) {

		SmTimerTaskService taskDefservice = SpringContextUtil.getBean(SmTimerTaskService.class);
		SystemService systemService = SpringContextUtil.getBean(SystemService.class);
		if (result != null && result.getBytes().length > 1000) {
			String result_display = "";
			int counByte = 0;
			for (int i = 0; i < result.length(); i++) {
				String pre_display_title_userinfo = result.substring(i, i + 1);
				java.util.regex.Pattern p = java.util.regex.Pattern
						.compile("[\\u4e00-\\u9fa5]");
				java.util.regex.Matcher m = p
						.matcher(pre_display_title_userinfo);
				if (counByte < 990) {
					if (m.find()) {
						if (counByte < 988) {
							result_display = result_display
									+ pre_display_title_userinfo;
							counByte++;
							counByte++;
						}
					} else {
						if (counByte < 989) {
							result_display = result_display
									+ pre_display_title_userinfo;
							counByte++;
						}
					}
				} else {
					break;
				}
			}
			result = result_display + "..";
		}
		   
		SmTimerTaskRunLog logInfo = new SmTimerTaskRunLog();
		logInfo.setLogId(systemService.generateId("SM_TIMER_TASK_RUN_LOG", "LOG_ID"));
		logInfo.setTaskId(taskId);
		logInfo.setRunTime(run_date);
		logInfo.setRunResult(result);
		logInfo.setResultCode(code);
		if (!TimerUtil.isBlank(instanceIdStr)) {
			logInfo.setInstanceId(Long.valueOf(instanceIdStr));
		}
		logInfo.setEndTime(systemService.getSystemDate());

		taskDefservice.insertSmTimerTaskRunLog(logInfo);
	}
	/**
	 * 更新定时任务配置表执行时间
	 * @return
	 */
	public static boolean updateTimerTaskDef(SmTimerTaskDef taskInfo,Date run_date) {

		boolean flag = true;
		SmTimerTaskService taskDefservice = SpringContextUtil
				.getBean(SmTimerTaskService.class);
		try {
			Integer count = taskDefservice.updateTimerTaskDefRunDate(run_date,
					taskInfo.getTaskId());
			if (count == 0) {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	/**
	 * 清空执行时间
	 * @return
	 */
	public static boolean updateTimerTaskDefineToNull( SmTimerTaskDef taskInfo, java.sql.Timestamp date) {
		
		   boolean flag= true;
		   SmTimerTaskModel timerTaskModel = new SmTimerTaskModel();
		   timerTaskModel.setPreRunTime(date);
		   timerTaskModel.setTaskId(taskInfo.getTaskId());
		   SmTimerTaskService taskDefservice=SpringContextUtil.getBean(SmTimerTaskService.class);
		   Integer count= taskDefservice.updateByPrimaryKeySelective(new SmTimerTaskModel());
			if(count==0){
				flag=false;
			}
	        return flag;
	}
	/**
	 * 更新定时任务配置表配置
	 * @return
	 */
	public static Timestamp refreshTaskInfo(Long taskId) {
	   SmTimerTaskService taskDefservice=SpringContextUtil.getBean(SmTimerTaskService.class);
	   SmTimerTaskDef taskInfoTmp=null;
	
	    try {
	    	taskInfoTmp = taskDefservice.queryTaskInfoById(taskId);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		if(taskInfoTmp!=null&&FrameConstants.TimerTask.TASK_STATUS_Y.endsWith(taskInfoTmp.getTaskStatus())){
			 return taskInfoTmp.getPreRunTime();
		}
	   return null;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public SmTimerTaskDef getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(SmTimerTaskDef taskInfo) {
		this.taskInfo = taskInfo;
	}

	public String getTimerName() {
		return timerName;
	}

	public void setTimerName(String timerName) {
		this.timerName = timerName;
	}

	public Date getRun_date() {
		return run_date;
	}

	public void setRun_date(Date run_date) {
		this.run_date = run_date;
	}

	public String getSyncTimeFormat() {
		return syncTimeFormat;
	}

	public void setSyncTimeFormat(String syncTimeFormat) {
		this.syncTimeFormat = syncTimeFormat;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
}

