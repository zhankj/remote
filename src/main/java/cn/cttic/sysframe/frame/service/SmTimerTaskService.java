package cn.cttic.sysframe.frame.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.frame.domain.SmTimerEngineConfig;
import cn.cttic.sysframe.frame.domain.SmTimerEngineConfigExample;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDefExample;
import cn.cttic.sysframe.frame.domain.SmTimerTaskRunLog;
import cn.cttic.sysframe.frame.model.SmTimerTaskModel;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


public interface SmTimerTaskService {
	
	public PageList<SmTimerTaskDef> queryPage(SmTimerTaskDefExample taskCriteria, PageBounds pageBounds);
	
	public int insert(SmTimerTaskModel timerTaskModel);
	
	public int updateByPrimaryKeySelective(SmTimerTaskModel timerTaskModel);
	
	public int insertSmTimerTaskRunLog(SmTimerTaskRunLog timerTaskRunLog);
	
	public int updateTimerTaskDefRunDate(Date runDate,Long taskId);
	
	public SmTimerTaskDef queryTaskInfoById(Long taskId);
	
	public List<SmTimerTaskDef> getSmTimerTaskDefListByTimer(Map qryMap);
	
	public Map<String, Object> queryTaskLogInfoById(String logId);
	
	public PageList<Map<String, Object>> queryTaskLogList(Map<String, Object> qryMap,PageBounds pageBounds);
	
	public List<SmTimerEngineConfig> getEngineConfigList(SmTimerEngineConfigExample engineConfigCriteria);
	
	public SmTimerTaskModel queryTaskModelById(Long taskId);
}
