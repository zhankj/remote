package cn.cttic.sysframe.common.timertask;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;


import org.apache.log4j.Logger;

import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;
import cn.cttic.sysframe.frame.service.ITimerTask;


public class SysFrameTimerChildrenTask extends TimerTask{

	private static Logger log = Logger.getLogger(SysFrameTimerChildrenTask.class);

	private SmTimerTaskDef taskInfo ; 
	
	private String timerName = ""; 
	
	private Date run_date=null;
	//记录次数
	private int num=0; 
	
	//最大次数
	private int Maxnum=0; 
	
	private String syncTimeFormat = "";
	
	private  String instanceId ="";
	
	private Date first_date=null;
	private long period_time=0;  //毫秒
	
	public SysFrameTimerChildrenTask() {
		// TODO Auto-generated constructor stubs
	}

	@Override
	public void run() {
		synchronized(TimerUtil.sysFrameTimerMap){
			int num2=num+1;
			
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】开始执行任务***************************************************************");
			if(num==0){
				
				if(TimerUtil.isTimerRunActComplex(taskInfo,TimerUtil.sysFrameTimerMap,run_date,syncTimeFormat)&&SysFrameTimerTask.updateTimerTaskDef(taskInfo,run_date)){
					log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】执行时间为 "+DateUtil.format(run_date,"yyyy-MM-dd HH:mm:ss")+" 开始执行业务逻辑***************************************************************");
					handle();
					log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】执行时间为 "+DateUtil.format(run_date,"yyyy-MM-dd HH:mm:ss")+" 执行业务逻辑完毕***************************************************************");
				}else{
					log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】执行时间为 "+DateUtil.format(run_date,"yyyy-MM-dd HH:mm:ss")+"  不处理业务逻辑***************************************************************");
				}
				addChildTimerMap();
			}else if(Maxnum>num){
				//更新定时任务上次执行时间
				Timestamp date=SysFrameTimerTask.refreshTaskInfo(taskInfo.getTaskId());
				
				
				if(date==null){
					List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfo.getTaskId().toString());
					for (Map map : mapList){
						String syncTimeFormatTemp=(String)map.get(TimerUtil.SYNC_TIME_FORMAT);
						if(!TimerUtil.isBlank(syncTimeFormat)&&syncTimeFormat.equals(syncTimeFormatTemp)){
							date=new java.sql.Timestamp(((Date)map.get(TimerUtil.PRE_RUN_TIME)).getTime());
						}
					}
				}
				
				taskInfo.setPreRunTime(date);
				
				// run_date=TimerUtil.runActComplexTime(taskInfo);
				run_date = new Date();
				run_date.setTime(first_date.getTime()+ (period_time * num) );
				log.debug("period_time "+period_time+"-first_date--"+first_date.getTime()+" num2 " +num2);
				
				log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】执行时间为 "+DateUtil.format(run_date,"yyyy-MM-dd HH:mm:ss")+" ***************************************************************");
				if(TimerUtil.isTimerRunActComplex(taskInfo,TimerUtil.sysFrameTimerMap,run_date,syncTimeFormat)&&SysFrameTimerTask.updateTimerTaskDef(taskInfo,run_date)){
					log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】执行时间为 "+DateUtil.format(run_date,"yyyy-MM-dd HH:mm:ss")+" 开始执行业务逻辑***************************************************************");
					handle();
					log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】执行时间为 "+DateUtil.format(run_date,"yyyy-MM-dd HH:mm:ss")+" 执行业务逻辑完毕***************************************************************");
				}else{
					log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】执行时间为 "+DateUtil.format(run_date,"yyyy-MM-dd HH:mm:ss")+"  不处理业务逻辑***************************************************************");
				}
				addChildTimerMap();
			}
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】次执行任务完毕***************************************************************");
			num++;
			if(Maxnum==num){
				//停止定时器
				String msg=TimerUtil.stopTimer(taskInfo.getTaskId().toString(), true,syncTimeFormat);
				if(!TimerUtil.isBlank(msg)){
					msg="子定时器结束失败:"+msg;
					SysFrameTimerTask.writeTimeRunLog(taskInfo.getTaskId(),"syncTimeFormat:"+syncTimeFormat+" "+msg,3,run_date,instanceId);
				}
				log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num+"】执行任务完毕 该定时器结束***************************************************************");
			}
		}
	}
	
	/**
	 * timer处理方法
	 */
	private void handle(){
		
//		synchronized(TimerUtil.sysFrameTimerMap){
//			Map map = TimerUtil.sysFrameTimerMap.get(taskInfo.getTaskId().toString());
//			if(map!=null){
//				map.put(TimerUtil.PRE_RUN_TIME, run_date);
//			}
//		}
		
		int num2=num+1;
		if(taskInfo!=null){
			log.debug("定时任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】次开始处理 ***************************************************************");
		}
		//SQL类型 
		if(taskInfo!=null&&taskInfo.getImplType().intValue()==SysFrameTimer.impl_type_sql){
				
			ITimerTask handleImpl=null;
			try{
//				handleImpl = (ITimerTask)TimerUtil.getImplObject("com.ailk.crm.system.service.imp.SqlTimerTaskImpl");
				handleImpl = (ITimerTask)TimerUtil.getImplObjectForSpring("com.ailk.crm.system.timer.interfaces.imp.FileAutoGenTaskImpl");
			} catch (Exception e) {
	        	SysFrameTimerTask.writeTimeRunLog(taskInfo.getTaskId(),"获取实现类出错!",Integer.parseInt("2"),run_date,instanceId);
	        } 
	        HashMap map = null;
	        try{
	        	map = handleImpl.execute(taskInfo,taskInfo.getTaskCreator());
	        } catch (Exception e) {
	        	SysFrameTimerTask.writeTimeRunLog(taskInfo.getTaskId(),"实现类执行出错!",Integer.parseInt("2"),run_date,instanceId);
	        } 
	        String result=(String)map.get(ITimerTask.RESULT);
			String code=(String)map.get(ITimerTask.CODE);
			
			SysFrameTimerTask.writeTimeRunLog(taskInfo.getTaskId(),result,Integer.parseInt(code),run_date,instanceId);
			log.debug("定时子任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】子任务第【"+num2+"】次处理完毕***************************************************************");
		}
		//自定义方法
		else if(taskInfo!=null&&taskInfo.getImplType()==SysFrameTimer.impl_type_meth){
			//调用实现方法	
			ITimerTask handleImpl=null;
			try{
				handleImpl = (ITimerTask)TimerUtil.getImplObjectForSpring(taskInfo.getImplMeth());
	        } catch (Exception e) {
	        	SysFrameTimerTask.writeTimeRunLog(taskInfo.getTaskId(),"获取实现类出错!",Integer.parseInt("2"),run_date,instanceId);
	        } 
	        HashMap map = null;
	        try{
	        	map = handleImpl.execute(taskInfo,taskInfo.getTaskCreator());
	        } catch (Exception e) {
	        	SysFrameTimerTask.writeTimeRunLog(taskInfo.getTaskId(),"实现类执行出错!",Integer.parseInt("2"),run_date,instanceId);
	        } 
			if(map!=null){
				String result=(String)map.get("result");
				String code=(String)map.get("code");
				SysFrameTimerTask.writeTimeRunLog(taskInfo.getTaskId(),result,Integer.parseInt(code),run_date,instanceId);
			}else{
				SysFrameTimerTask.writeTimeRunLog(taskInfo.getTaskId(),"返回结果为空,无法确认是否执行成功",Integer.parseInt("2"),run_date,instanceId);
			}
		}
	}
	/**
	 * 向缓存中写复杂周期第二周期实例
	 */
	private void addChildTimerMap(){
		List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfo.getTaskId().toString());
		for (Map map : mapList){
			String syncTimeFormatTemp=(String)map.get(TimerUtil.SYNC_TIME_FORMAT);
			if(!TimerUtil.isBlank(syncTimeFormat)&&syncTimeFormat.equals(syncTimeFormatTemp)){
				map.put(TimerUtil.PRE_RUN_TIME, run_date);
			}
		}
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getMaxnum() {
		return Maxnum;
	}

	public void setMaxnum(int maxnum) {
		Maxnum = maxnum;
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

	public Date getFirst_date() {
		return first_date;
	}

	public void setFirst_date(Date first_date) {
		this.first_date = first_date;
	}

	public long getPeriod_time() {
		return period_time;
	}

	public void setPeriod_time(long period_time) {
		this.period_time = period_time;
	}

}

