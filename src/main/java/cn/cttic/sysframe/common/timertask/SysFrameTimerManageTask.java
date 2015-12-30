package cn.cttic.sysframe.common.timertask;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


import org.apache.log4j.Logger;

import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;


public class SysFrameTimerManageTask extends TimerTask{

	private static Logger log = Logger.getLogger(SysFrameTimerManageTask.class);
	
	private String system_id;
	
	private String instanceId;
	
	private long num = 0; 
	
	public SysFrameTimerManageTask() {
		// TODO Auto-generated constructor stubs
	}

	@Override
	public void run() {
		if(num==0){
			num++;
			new SysFrameTimer(system_id,instanceId);
		}else{
			
			log.debug("第"+num+"次刷新定时器 处理开始***************************************************************");
			
			List<SmTimerTaskDef> taskList = new ArrayList();
			
			/***1.获取定时任务配置信息*******/
			try {
			    taskList= SysFrameTimer.getSmTimerTaskDefList(system_id,instanceId);
			}catch(Exception e){
				e.printStackTrace();
				String error="管理更新定时器刷新失败:"+e.getMessage();
				SysFrameTimerTask.writeTimeRunLog(new Long(-1),error,4,new Date(),instanceId);
			}
			
			/**2.停止定时器 **/
			String error="";
			boolean isDel=true;
			
			if(TimerUtil.sysFrameTimerMap!=null){
				synchronized(TimerUtil.sysFrameTimerMap){
					for(Iterator iterator = TimerUtil.sysFrameTimerMap.entrySet().iterator();iterator.hasNext();){  //遍历集合
						Map.Entry entry = (Map.Entry)iterator.next();
						String taskId = (String)entry.getKey();
						
						isDel=true;
						error="";
						
						if(taskList!=null&&taskList.size()>0){
							for(SmTimerTaskDef taskInfoTmp:taskList){
								if(taskId.equals(taskInfoTmp.getTaskId().toString())){
									isDel=false;
									break;
								}
							}
							if(isDel){
								iterator.remove();
								error=TimerUtil.stopTimer(taskId,false,"");
								if(!TimerUtil.isBlank(error)){
									log.debug("第"+num+"次刷新定时器 停止taskId="+taskId+"定时器失败"+error+"***************************************************************");
									error="定时器结束失败:taskId="+taskId+"停止错误****"+error;
									SysFrameTimerTask.writeTimeRunLog(new Long(-1),error,3,new Date(),instanceId);
								}else{
									log.debug("第"+num+"次刷新定时器 停止taskId="+taskId+"定时器成功***************************************************************");
								}
								
							}
						}else{
							iterator.remove();
							error=TimerUtil.stopTimer(taskId,false,"");
							if(!TimerUtil.isBlank(error)){
								
								log.debug("第"+num+"次刷新定时器 停止taskId="+taskId+"定时器失败"+error+"***************************************************************");
								error="定时器结束失败:taskId="+taskId+"停止错误****"+error;
								SysFrameTimerTask.writeTimeRunLog(new Long(-1),error,3,new Date(),instanceId);
								
							}else{
								log.debug("第"+num+"次刷新定时器 停止taskId="+taskId+"定时器成功***************************************************************");
							}
						}
						
					}
	
				}
			}
			/**3.更新定时器 **/

			if(taskList!=null&&taskList.size()>0){
				for(SmTimerTaskDef taskInfoTmp:taskList){
					
					if(taskInfoTmp.getUpdateTime()!=null){
						if(TimerUtil.sysFrameTimerMap!=null){
							synchronized(TimerUtil.sysFrameTimerMap){
								if(TimerUtil.sysFrameTimerMap.isEmpty()){
									
									if(taskInfoTmp.getPeriodType()==TimerUtil.TIMER_TASK_PERIOD_SPECIAL){
										String[] dateArray = taskInfoTmp.getSyncTimeFormat().split(",");
										String time=dateArray[4];
										
										Timer timer =new Timer();
										SysFrameTimerTask task = new SysFrameTimerTask();
										
										if(!TimerUtil.isBlank(time)){
											
											String[] timeInfo=time.split(";");
											
											for(int i=0;i<timeInfo.length;i++){
												timer = new Timer();
												task = new SysFrameTimerTask();
												task.setType(taskInfoTmp.getTaskType());
												task.setTaskInfo(taskInfoTmp);
												task.setInstanceId(instanceId);
												task.setTimerName(taskInfoTmp.getTaskId().toString());
												task.setSyncTimeFormat(timeInfo[i]);
												timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(timeInfo[i],taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),24 * 60 * 60 * 1000);
												
												Map map= new HashMap();
												map.put(TimerUtil.TIMER_OBJECT, timer);
												map.put(TimerUtil.UPDATE_TIME, taskInfoTmp.getUpdateTime());
												map.put(TimerUtil.START_TIME, new Date());
												map.put(TimerUtil.CHILD_OBJECT, null);
												map.put(TimerUtil.SYNC_TIME_FORMAT, timeInfo[i]);
												
												List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfoTmp.getTaskId().toString());
												if(mapList==null||mapList.size()==0){
													mapList = new ArrayList<Map>();
												}
												mapList.add(map);
												TimerUtil.sysFrameTimerMap.put(taskInfoTmp.getTaskId().toString(), mapList);
											}
										}
									}else{
										Timer timer =new Timer();
										SysFrameTimerTask task = new SysFrameTimerTask();
										
										task.setType(taskInfoTmp.getTaskType());
										task.setTaskInfo(taskInfoTmp);
										task.setInstanceId(instanceId);
										task.setTimerName(taskInfoTmp.getTaskId().toString());
										task.setSyncTimeFormat(taskInfoTmp.getSyncTimeFormat());
										timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(taskInfoTmp.getSyncTimeFormat(),taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),TimerUtil.getPeriod(taskInfoTmp.getSyncTimeFormat()));
										
										// SysFrameTimerTask.updateSmTimerTaskDefToNull(taskInfoTmp, null);
										log.debug("第"+num+"次刷新定时器 重新taskId="+taskInfoTmp.getTaskId()+"定时器成功  首次执行时间为"+DateUtil.format(TimerUtil.getFirstTime(taskInfoTmp.getSyncTimeFormat(),taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),"yyyy-MM-dd HH:mm:ss")+" ***************************************************************");
										
										Map taskMap= new HashMap();
										taskMap.put(TimerUtil.TIMER_OBJECT, timer);
										taskMap.put(TimerUtil.UPDATE_TIME, taskInfoTmp.getUpdateTime());
										taskMap.put(TimerUtil.START_TIME, new Date());
										taskMap.put(TimerUtil.CHILD_OBJECT, null);
										taskMap.put(TimerUtil.SYNC_TIME_FORMAT, taskInfoTmp.getSyncTimeFormat());
										
										List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfoTmp.getTaskId().toString());
										if(mapList==null||mapList.size()==0){
											mapList = new ArrayList<Map>();
										}
										mapList.add(taskMap);
										TimerUtil.sysFrameTimerMap.put(taskInfoTmp.getTaskId().toString(), mapList);
									}
								}else{
									boolean isHandle=false;
									for(Iterator iterator = TimerUtil.sysFrameTimerMap.entrySet().iterator();iterator.hasNext();){  //遍历集合
										Map.Entry entry = (Map.Entry)iterator.next();
										String taskId = (String)entry.getKey();
										error="";
										isHandle=false;
										
										if(taskId.equals(taskInfoTmp.getTaskId().toString())){
											
											List<Map> taskInfoMapList = (List<Map>)entry.getValue();
											
											Map taskInfoMap = new HashMap();
											if(taskInfoMapList!=null&&taskInfoMapList.size()>0){
												taskInfoMap=taskInfoMapList.get(0);
											}
											
											if(taskInfoMap==null||taskInfoMap.get(TimerUtil.UPDATE_TIME)==null||(((Date)taskInfoMap.get(TimerUtil.UPDATE_TIME)).getTime()<taskInfoTmp.getUpdateTime().getTime())){
												/**3.1停止原定时器 **/
												
												error=TimerUtil.stopTimer(taskId,false,"");
												if(!TimerUtil.isBlank(error)){
													log.debug("第"+num+"次刷新定时器 停止taskId="+taskId+"定时器失败"+error+"***************************************************************");
													error="定时器结束失败:taskId="+taskId+" 停止错误****"+error;
													SysFrameTimerTask.writeTimeRunLog(new Long(-1),error,3,new Date(),instanceId);
												}else{
													log.debug("第"+num+"次刷新定时器 停止taskId="+taskId+"定时器成功***************************************************************");
												
													/**3.2启动新的定时器 **/
													
													if(taskInfoTmp.getPeriodType()==TimerUtil.TIMER_TASK_PERIOD_SPECIAL){
														String[] dateArray = taskInfoTmp.getSyncTimeFormat().split(",");
														String time=dateArray[4];
														
														Timer timer =new Timer();
														SysFrameTimerTask task = new SysFrameTimerTask();
														
														if(!TimerUtil.isBlank(time)){
															
															String[] timeInfo=time.split(";");
															
															for(int i=0;i<timeInfo.length;i++){
																timer = new Timer();
																task = new SysFrameTimerTask();
																task.setType(taskInfoTmp.getTaskType());
																task.setTaskInfo(taskInfoTmp);
																task.setInstanceId(instanceId);
																task.setTimerName(taskInfoTmp.getTaskId().toString());
																task.setSyncTimeFormat(timeInfo[i]);
																timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(timeInfo[i],taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),24 * 60 * 60 * 1000);
																
																Map map= new HashMap();
																map.put(TimerUtil.TIMER_OBJECT, timer);
																map.put(TimerUtil.UPDATE_TIME, taskInfoTmp.getUpdateTime());
																map.put(TimerUtil.START_TIME, new Date());
																map.put(TimerUtil.CHILD_OBJECT, null);
																map.put(TimerUtil.SYNC_TIME_FORMAT, timeInfo[i]);
																
																List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfoTmp.getTaskId().toString());
																if(mapList==null||mapList.size()==0){
																	mapList = new ArrayList<Map>();
																}
																mapList.add(map);
																TimerUtil.sysFrameTimerMap.put(taskInfoTmp.getTaskId().toString(), mapList);
															}
														}
													}else{
														Timer timer =new Timer();
														SysFrameTimerTask task = new SysFrameTimerTask();
														
														task.setType(taskInfoTmp.getTaskType());
														task.setTaskInfo(taskInfoTmp);
														task.setInstanceId(instanceId);
														task.setTimerName(taskInfoTmp.getTaskId().toString());
														task.setSyncTimeFormat(taskInfoTmp.getSyncTimeFormat());
														timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(taskInfoTmp.getSyncTimeFormat(),taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),TimerUtil.getPeriod(taskInfoTmp.getSyncTimeFormat()));
														
														// SysFrameTimerTask.updateSmTimerTaskDefToNull(taskInfoTmp, null);
														log.debug("第"+num+"次刷新定时器 重新taskId="+taskId+"定时器成功  首次执行时间为"+DateUtil.format(TimerUtil.getFirstTime(taskInfoTmp.getSyncTimeFormat(),taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),"yyyy-MM-dd HH:mm:ss")+" ***************************************************************");
														
														Map taskMap= new HashMap();
														taskMap.put(TimerUtil.TIMER_OBJECT, timer);
														taskMap.put(TimerUtil.UPDATE_TIME, taskInfoTmp.getUpdateTime());
														taskMap.put(TimerUtil.START_TIME, new Date());
														taskMap.put(TimerUtil.CHILD_OBJECT, null);
														taskMap.put(TimerUtil.SYNC_TIME_FORMAT, taskInfoTmp.getSyncTimeFormat());
														
														List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfoTmp.getTaskId().toString());
														if(mapList==null||mapList.size()==0){
															mapList = new ArrayList<Map>();
														}
														mapList.add(taskMap);
														TimerUtil.sysFrameTimerMap.put(taskInfoTmp.getTaskId().toString(), mapList);
													}
												}
											}
											isHandle=true;
											break;
										}
									}	
									if(!isHandle){
										/**3.3 缓存中没有的定时器直接启动 **/
										if(taskInfoTmp.getPeriodType()==TimerUtil.TIMER_TASK_PERIOD_SPECIAL){
											String[] dateArray = taskInfoTmp.getSyncTimeFormat().split(",");
											String time=dateArray[4];
											
											Timer timer =new Timer();
											SysFrameTimerTask task = new SysFrameTimerTask();
											
											if(!TimerUtil.isBlank(time)){
												
												String[] timeInfo=time.split(";");
												
												for(int i=0;i<timeInfo.length;i++){
													timer = new Timer();
													task = new SysFrameTimerTask();
													task.setType(taskInfoTmp.getTaskType());
													task.setTaskInfo(taskInfoTmp);
													task.setInstanceId(instanceId);
													task.setTimerName(taskInfoTmp.getTaskId().toString());
													task.setSyncTimeFormat(timeInfo[i]);
													timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(timeInfo[i],taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),24 * 60 * 60 * 1000);
													
													Map map= new HashMap();
													map.put(TimerUtil.TIMER_OBJECT, timer);
													map.put(TimerUtil.UPDATE_TIME, taskInfoTmp.getUpdateTime());
													map.put(TimerUtil.START_TIME, new Date());
													map.put(TimerUtil.CHILD_OBJECT, null);
													map.put(TimerUtil.SYNC_TIME_FORMAT, timeInfo[i]);
													
													List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfoTmp.getTaskId().toString());
													if(mapList==null||mapList.size()==0){
														mapList = new ArrayList<Map>();
													}
													mapList.add(map);
													TimerUtil.sysFrameTimerMap.put(taskInfoTmp.getTaskId().toString(), mapList);
												}
											}
										}else{
											Timer timer =new Timer();
											SysFrameTimerTask task = new SysFrameTimerTask();
											
											task.setType(taskInfoTmp.getTaskType());
											task.setTaskInfo(taskInfoTmp);
											task.setInstanceId(instanceId);
											task.setTimerName(taskInfoTmp.getTaskId().toString());
											task.setSyncTimeFormat(taskInfoTmp.getSyncTimeFormat());
											timer.scheduleAtFixedRate(task, TimerUtil.getFirstTime(taskInfoTmp.getSyncTimeFormat(),taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),TimerUtil.getPeriod(taskInfoTmp.getSyncTimeFormat()));
											
											// SysFrameTimerTask.updateSmTimerTaskDefToNull(taskInfoTmp, null);
											log.debug("第"+num+"次刷新定时器 重新taskId="+taskInfoTmp.getTaskId()+"定时器成功  首次执行时间为"+DateUtil.format(TimerUtil.getFirstTime(taskInfoTmp.getSyncTimeFormat(),taskInfoTmp.getTaskType(),taskInfoTmp.getPeriodType()),"yyyy-MM-dd HH:mm:ss")+" ***************************************************************");
											
											Map taskMap= new HashMap();
											taskMap.put(TimerUtil.TIMER_OBJECT, timer);
											taskMap.put(TimerUtil.UPDATE_TIME, taskInfoTmp.getUpdateTime());
											taskMap.put(TimerUtil.START_TIME, new Date());
											taskMap.put(TimerUtil.CHILD_OBJECT, null);
											taskMap.put(TimerUtil.SYNC_TIME_FORMAT, taskInfoTmp.getSyncTimeFormat());
											
											List<Map> mapList= TimerUtil.sysFrameTimerMap.get(taskInfoTmp.getTaskId().toString());
											if(mapList==null||mapList.size()==0){
												mapList = new ArrayList<Map>();
											}
											mapList.add(taskMap);
											TimerUtil.sysFrameTimerMap.put(taskInfoTmp.getTaskId().toString(), mapList);
										}
									}
								}
							}
						}
					}
				}
			}
			log.debug("第"+num+"次刷新定时器 处理完成***************************************************************");
			num++;
		}
		
	}
	
	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
}

