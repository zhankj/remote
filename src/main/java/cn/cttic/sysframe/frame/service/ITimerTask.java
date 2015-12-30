package cn.cttic.sysframe.frame.service;

import java.util.HashMap;

import cn.cttic.sysframe.frame.domain.SmTimerTaskDef;

public interface ITimerTask {
	public final static String CODE = "code";  //1.执行成功;2.执行失败;3.定时任务停止失败
	public final static String RESULT = "result";
	
	public final static String SUCCESS_CODE = "1";    //执行成功
	public final static String FAIL_CODE = "2";       //执行失败
	public final static String EXCEPTION_CODE = "3";  //定时任务停止失败
	
	/**
	 * 
	 * @param timerTaskInfo
	 * @param operId
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String> execute(SmTimerTaskDef timerTaskInfo,String operId);
}
