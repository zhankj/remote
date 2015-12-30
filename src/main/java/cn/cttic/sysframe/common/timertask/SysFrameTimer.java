package cn.cttic.sysframe.common.timertask;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.apache.log4j.Logger;

import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDefExample;
import cn.cttic.sysframe.frame.service.SmTimerTaskService;


import java.util.Date;

public class SysFrameTimer {

	private static Logger log = Logger.getLogger(SysFrameTimer.class);
	
	//定时器类型
	public static int task_type_realTime =0;
	public static int task_type_one =1;
	public static int task_type_simple =2;
	public static int task_type_complex =3;
	
	// 任务实现类型 :SQL类型、自定义方法类型
	public static int impl_type_sql =1;
	public static int impl_type_meth =2;
	
	public SysFrameTimer(String systemId,String instanceId) {
		
		List<SmTimerTaskDef> taskList = new ArrayList();
		
		/***1.获取定时任务配置信息*******/
		try {
 		    taskList= getSmTimerTaskDefList(systemId,instanceId);
		}catch(Exception e){
			e.printStackTrace(); 
		}
			
		/**2.创建定时器 **/
		Timer timer ;
		SysFrameTimerTask task;
		Map map= new HashMap();
		
		if(taskList!=null&&taskList.size()>0){
			for(SmTimerTaskDef taskInfo :taskList ){
				
				log.debug("任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】启动开始***************************************************************");
				
				/***2.1类型为空或配置时间为空 则默认即时模式*******/
				if(TimerUtil.isBlank(taskInfo.getSyncTimeFormat())||taskInfo.getTaskType()==null||taskInfo.getTaskType()==0){
				
				
				}else{
					
					if(taskInfo.getPeriodType()==TimerUtil.TIMER_TASK_PERIOD_SPECIAL){
						
						String[] dateArray = taskInfo.getSyncTimeFormat().split(",");
						String time=dateArray[4];
						
						if(!TimerUtil.isBlank(time)){
							
							String[] timeInfo=time.split(";");
							
							for(int i=0;i<timeInfo.length;i++){
								timer = new Timer();
								task = new SysFrameTimerTask();
								task.setType(taskInfo.getTaskType());
								task.setInstanceId(instanceId);
								task.setTaskInfo(taskInfo);
								task.setTimerName(taskInfo.getTaskId().toString());
								task.setSyncTimeFormat(timeInfo[i]);
								timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(timeInfo[i],taskInfo.getTaskType(),taskInfo.getPeriodType()),24 * 60 * 60 * 1000);
								
								map= new HashMap();
								map.put(TimerUtil.TIMER_OBJECT, timer);
								map.put(TimerUtil.UPDATE_TIME, taskInfo.getUpdateTime());
								map.put(TimerUtil.START_TIME, new Date());
								map.put(TimerUtil.CHILD_OBJECT, null);
								map.put(TimerUtil.SYNC_TIME_FORMAT, timeInfo[i]);
								
								List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfo.getTaskId().toString());
								if(mapList==null||mapList.size()==0){
									mapList = new ArrayList<Map>();
								}
								mapList.add(map);
								TimerUtil.sysFrameTimerMap.put(taskInfo.getTaskId().toString(), mapList);
								
								log.debug("任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】启动完成 首次执行时间为"+DateUtil.format(TimerUtil.getFirstTime(timeInfo[i],taskInfo.getTaskType(),taskInfo.getPeriodType()),"yyyy-MM-dd HH:mm:ss")+" *******"+24 * 60 * 60 * 1000+"********************************************************");
							}
						}
					}else{
						timer = new Timer();
						task = new SysFrameTimerTask();
						task.setType(taskInfo.getTaskType());
						task.setTaskInfo(taskInfo);
						task.setTimerName(taskInfo.getTaskId().toString());
						task.setInstanceId(instanceId);
						task.setSyncTimeFormat(taskInfo.getSyncTimeFormat());
						timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(taskInfo.getSyncTimeFormat(),taskInfo.getTaskType(),taskInfo.getPeriodType()),TimerUtil.getPeriod(taskInfo.getSyncTimeFormat()));
						
						map= new HashMap();
						map.put(TimerUtil.TIMER_OBJECT, timer);
						map.put(TimerUtil.UPDATE_TIME, taskInfo.getUpdateTime());
						map.put(TimerUtil.START_TIME, new Date());
						map.put(TimerUtil.CHILD_OBJECT, null);
						map.put(TimerUtil.SYNC_TIME_FORMAT, taskInfo.getSyncTimeFormat());
						
						List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfo.getTaskId().toString());
						if(mapList==null||mapList.size()==0){
							mapList = new ArrayList<Map>();
						}
						mapList.add(map);
						TimerUtil.sysFrameTimerMap.put(taskInfo.getTaskId().toString(), mapList);
						
						log.debug("任务【"+taskInfo.getTaskName()+"("+taskInfo.getTaskId()+")"+"】启动完成 首次执行时间为"+DateUtil.format(TimerUtil.getFirstTime(taskInfo.getSyncTimeFormat(),taskInfo.getTaskType(),taskInfo.getPeriodType()),"yyyy-MM-dd HH:mm:ss")+" *******"+
								TimerUtil.getPeriod(taskInfo.getSyncTimeFormat())+"********************************************************");
					}
				}
				
			}
				
		}
		//根据不同的配置信息进行定时器启动
	}
		
	/**
	 * 获取定时任务配置信息
	 */
	public static List<SmTimerTaskDef> getSmTimerTaskDefList(String systemId,String instanceId) throws Exception {
        
		
			SmTimerTaskService taskDefservice=SpringContextUtil.getBean(SmTimerTaskService.class);
    		Connection con = null;
            List<SmTimerTaskDef> result = new ArrayList<SmTimerTaskDef>();
            try{
	            Map qryMap= new HashMap();
	            qryMap.put("taskStatus", FrameConstants.TimerTask.TASK_STATUS_Y);
	            qryMap.put("systemId", systemId);
				if(!TimerUtil.isBlank(instanceId)){
					 qryMap.put("instanceId", instanceId);
				}
				result=taskDefservice.getSmTimerTaskDefListByTimer(qryMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (con != null) {
                    con.close();
                }
            }
        return result;
	}
}
