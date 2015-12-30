package cn.cttic.sysframe.frame.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.common.constants.FrameConstants;
import cn.cttic.sysframe.frame.dao.SmTimerEngineConfigMapper;
import cn.cttic.sysframe.frame.dao.SmTimerTaskDefMapper;
import cn.cttic.sysframe.frame.dao.SmTimerTaskRunLogMapper;
import cn.cttic.sysframe.frame.domain.SmMenu;
import cn.cttic.sysframe.frame.domain.SmTimerEngineConfig;
import cn.cttic.sysframe.frame.domain.SmTimerEngineConfigExample;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDefExample;
import cn.cttic.sysframe.frame.domain.SmTimerTaskRunLog;
import cn.cttic.sysframe.frame.model.SmTimerTaskModel;
import cn.cttic.sysframe.frame.service.SmTimerTaskService;

@Service
public class SmTimerTaskServiceImpl implements SmTimerTaskService {

	@Autowired
	private SmTimerTaskDefMapper taskDefMapper;
	
	@Autowired
	private SmTimerEngineConfigMapper engineConfigMapper;
	
	@Autowired
	private SmTimerTaskRunLogMapper logMapper;
	
	public PageList<SmTimerTaskDef> queryPage(SmTimerTaskDefExample taskCriteria, PageBounds pageBounds){
		
		return taskDefMapper.queryPage(taskCriteria, pageBounds);
	}
	public int insert(SmTimerTaskModel timerTaskModel) {
		try {
			if(timerTaskModel!=null&&!StringUtil.isBlank(timerTaskModel.getInstanceIds())){
				String[] instanceIdArray=timerTaskModel.getInstanceIds().split(",");
				SmTimerEngineConfig engineConfig = null;
				for(int i=0;i<instanceIdArray.length;i++){
					engineConfig= new SmTimerEngineConfig();
					engineConfig.setTaskId(timerTaskModel.getTaskId());
					engineConfig.setSystemId(timerTaskModel.getSystemId());
					engineConfig.setInstanceId(Long.valueOf(instanceIdArray[i]));
					engineConfigMapper.insert(engineConfig);
				}
			}
			return taskDefMapper.insert(timerTaskModel);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	@Override
	public int updateByPrimaryKeySelective(SmTimerTaskModel timerTaskModel) {
		// TODO Auto-generated method stub
		try {
			if(timerTaskModel!=null&&timerTaskModel.getTaskId()>0){
				SmTimerEngineConfigExample configCriteria = new SmTimerEngineConfigExample();
				SmTimerEngineConfigExample.Criteria criteria = configCriteria.createCriteria();
				criteria.andTaskIdEqualTo(timerTaskModel.getTaskId());
				engineConfigMapper.deleteByExample(configCriteria);
			}
			if(timerTaskModel!=null&&!StringUtil.isBlank(timerTaskModel.getInstanceIds())){
				String[] instanceIdArray=timerTaskModel.getInstanceIds().split(",");
				SmTimerEngineConfig engineConfig = null;
				for(int i=0;i<instanceIdArray.length;i++){
					engineConfig= new SmTimerEngineConfig();
					engineConfig.setTaskId(timerTaskModel.getTaskId());
					engineConfig.setSystemId(timerTaskModel.getSystemId());
					engineConfig.setInstanceId(Long.valueOf(instanceIdArray[i]));
					engineConfigMapper.insert(engineConfig);
				}
			}
			
			return taskDefMapper.updateByPrimaryKeySelective(timerTaskModel);
		} catch (Exception ex) {
			throw new SystemException(ex, StatusCodeForFrame.DB_OPERATION_ERROR);
		}
	}
	public int insertSmTimerTaskRunLog(SmTimerTaskRunLog timerTaskRunLog){
		return logMapper.insert(timerTaskRunLog);
	}
	public int updateTimerTaskDefRunDate(Date runDate,Long taskId){
		SmTimerTaskModel timerTaskModel = new SmTimerTaskModel(); 
		timerTaskModel.setPreRunTime(new java.sql.Timestamp(runDate.getTime()));
		timerTaskModel.setTaskId(taskId);
		return taskDefMapper.updateTimerTaskDefRunDate(timerTaskModel);
	}
	public SmTimerTaskDef queryTaskInfoById(Long taskId){
		return taskDefMapper.selectByPrimaryKey(taskId);
	}
	public SmTimerTaskModel queryTaskModelById(Long taskId){
		
		SmTimerTaskModel taskModel = new SmTimerTaskModel();
		SmTimerTaskDef  taskDef = taskDefMapper.selectByPrimaryKey(taskId);
		BeanUtils.copyProperties(taskDef, taskModel);
		
		SmTimerEngineConfigExample configCriteria = new SmTimerEngineConfigExample();
		SmTimerEngineConfigExample.Criteria criteria = configCriteria.createCriteria();
		List<SmTimerEngineConfig> engineConfigList = engineConfigMapper.selectByExample(configCriteria);
		String instanceIds="";
		if(engineConfigList!=null && engineConfigList.size()>0){
			for(SmTimerEngineConfig config : engineConfigList){
				if(!StringUtil.isBlank(instanceIds)){
					instanceIds=instanceIds+","+config.getInstanceId();
				}else{
					instanceIds=config.getInstanceId().toString();
				}
			}
			taskModel.setInstanceIds(instanceIds);
		}
		return taskModel;
	}
	public List<SmTimerTaskDef> getSmTimerTaskDefListByTimer(Map qryMap){
		return taskDefMapper.getSmTimerTaskDefListByTimer(qryMap);
	}
	public Map<String, Object> queryTaskLogInfoById(String logId){
		if(StringUtil.isBlank(logId)){
			throw new SystemException("定时任务日志编码不能为空！", StatusCodeForFrame.PROPERTY_IS_NULL);
		}
		Map<String, Object> model=null ;
		try{
			model= logMapper.selectByPrimaryKey2(new Long(logId));
		}catch(Exception e){
			e.printStackTrace();
			throw new SystemException(e.getMessage(), StatusCodeForFrame.INSIDE_ERROR);
		}
		return model;
	}
	public PageList<Map<String, Object>> queryTaskLogList(Map<String, Object> qryModel,PageBounds pageBounds){
		return logMapper.queryTaskLogList(qryModel,pageBounds);
	}
	
	public List<SmTimerEngineConfig> getEngineConfigList(SmTimerEngineConfigExample engineConfigCriteria){
		return engineConfigMapper.selectByExample(engineConfigCriteria);
	}
}
