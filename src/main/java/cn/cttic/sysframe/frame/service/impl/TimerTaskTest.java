package cn.cttic.sysframe.frame.service.impl;

import java.util.HashMap;
import java.util.Map;

import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.dao.SmDataRightMapper;
import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;
import cn.cttic.sysframe.frame.service.ITimerTask;

public class TimerTaskTest implements ITimerTask{
	
	private SmDataRightMapper dataRightfMapper=SpringContextUtil.getBean(SmDataRightMapper.class);
	
	@Override
	public HashMap<String, String> execute(SmTimerTaskDef timerTaskInfo,
			String operId) {
		
		//定义返回结果集合
		 HashMap<String, String> result = new HashMap<String, String>();
		
		try{
			/* 
			 * 业务逻辑开始
			 *  
			 *  业务处理逻辑。。。
			 *  业务日志记录 （不保存在定时任务日志中，需要具体业务自己记录自己的业务日志。
			 *  
			 * 业务逻辑结束
			 */
			  Map paramMap = new HashMap();
			  String sql ="insert into sm_test_20140625(task_id,up_date,instance_id) values("+timerTaskInfo.getTaskId()+",sysdate,111111)";
			  paramMap.put("sysExecSql", sql);
			  dataRightfMapper.commQuery(paramMap);
		
		      result.put(ITimerTask.CODE, ITimerTask.SUCCESS_CODE);
		      result.put(ITimerTask.RESULT, "XXXX定时任务执行成功，业务全部执行成功。。");
			
		}catch(Exception e){
			  // 只要定时任务按时执行了，就是执行成功！进入该方法了，就是执行成功了。。
			  e.printStackTrace();
		      result.put(ITimerTask.CODE, ITimerTask.SUCCESS_CODE);
		      result.put(ITimerTask.RESULT, "XXXX定时任务执行成功，执行业务逻辑时出错。。"+e.getMessage());
		}
		return result;
	}

}
