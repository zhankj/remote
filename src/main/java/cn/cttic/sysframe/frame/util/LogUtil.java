package cn.cttic.sysframe.frame.util;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.domain.SmOperateLogWithBLOBs;
import cn.cttic.sysframe.frame.model.SmOperModel;
import cn.cttic.sysframe.frame.service.SmOperateLogService;
import cn.cttic.sysframe.frame.service.SystemService;

public class LogUtil {
	
	public final static String LOG_NAME = "LOG_NAME";

	public final static String TARGET_STAFF = "01";

	public final static String TARGET_GROUP = "02";

	private final static String OPERATE_TYPE_CREATE = "01";

	private final static String OPERATE_TYPE_MODIFY = "02";

	private final static String OPERATE_TYPE_DELETE = "03";

	
	/**
	 * 新建对象日志
	 *
	 * @param target
	 * @param newObject 
	 * @author yangrui
	 * @date 2014-6-24 下午1:54:52
	 */

	public static void createLog(String target, Object newObject) {

		SmOperateLogService smOperateLogService = (SmOperateLogService) SpringContextUtil.getBean(SmOperateLogService.class);

		SystemService systemService = (SystemService) SpringContextUtil.getBean(SystemService.class);

		SmOperateLogWithBLOBs entity = new SmOperateLogWithBLOBs();

		SmOperModel model = SessionInfo.getOperInfo();

		long id = systemService.generateId("SM_OPERATE_LOG", "LOG_ID");

		Date date = systemService.getSystemDate();
		
		String[] result = fromObject2Text(newObject);
		
		entity.setLogId(id);
		entity.setOperId(model.getOperId());
		entity.setOperCode(model.getOperCode());
		entity.setStaffId(model.getStaffId());
		entity.setOperateObject(target);
		entity.setOperateType(OPERATE_TYPE_CREATE);
		entity.setNewObject(result[0]);
		entity.setLogName(result[1]);
		entity.setOperateDate(date);

		smOperateLogService.insertSelective(entity);

	}
	
	/**
	 * 删除对象日志
	 *
	 * @param target
	 * @param newObject 
	 * @author yangrui
	 * @date 2014-6-24 下午1:55:05
	 */
	public static void deleteLog(String target, Object newObject) {
		
		SmOperateLogService smOperateLogService = (SmOperateLogService) SpringContextUtil.getBean(SmOperateLogService.class);

		SystemService systemService = (SystemService) SpringContextUtil.getBean(SystemService.class);

		SmOperateLogWithBLOBs entity = new SmOperateLogWithBLOBs();

		SmOperModel model = SessionInfo.getOperInfo();

		long id = systemService.generateId("SM_OPERATE_LOG", "LOG_ID");

		Date date = systemService.getSystemDate();
		
		String result[] = fromObject2Text(newObject);
		
		entity.setLogId(id);
		entity.setOperId(model.getOperId());
		entity.setOperCode(model.getOperCode());
		entity.setStaffId(model.getStaffId());
		entity.setOperateObject(target);
		entity.setOperateType(OPERATE_TYPE_DELETE);
		entity.setOldObject(result[0]);
		entity.setLogName(result[1]);
		entity.setOperateDate(date);

		smOperateLogService.insertSelective(entity);
	}
	/**
	 * 
	 *
	 * @param newObject
	 * @return [对象文本，日志名称]
	 * @author yangrui
	 * @date 2014-6-24 下午4:40:42
	 */
	private static String[] fromObject2Text(Object newObject) {
		String new_str = newObject.toString();
		JSONArray new_arr = JSONArray.fromObject("["+new_str+"]");
		JSONObject new_json = (JSONObject)new_arr.get(0);
		StringBuilder new_builder = new StringBuilder();
		String logName = null;
		
		Iterator<?> it = new_json.keys();
		while (it.hasNext()) {  
            String key = (String) it.next();  
            String new_value = new_json.getString(key)==null?"":new_json.getString(key);
            if(key.equals("LOG_NAME")){
            	logName = new_value;
            	continue;
            }
            new_builder.append(key+"："+new_value).append("，");
       } 
		String new_result = new_builder.toString();
		String result = new_result.substring(0, new_result.length()-1);
	    return new String[]{result,logName};
    }

	/**
	 * 修改对象日志
	 *
	 * @param target
	 * @param oldObject
	 * @param newObject 
	 * @author yangrui
	 * @date 2014-6-24 下午1:55:16
	 */
	public static void modifyLog(String target, Object oldObject, Object newObject) {
		
		SmOperateLogService smOperateLogService = (SmOperateLogService) SpringContextUtil.getBean(SmOperateLogService.class);

		SystemService systemService = (SystemService) SpringContextUtil.getBean(SystemService.class);

		SmOperateLogWithBLOBs entity = new SmOperateLogWithBLOBs();

		SmOperModel model = SessionInfo.getOperInfo();

		long id = systemService.generateId("SM_OPERATE_LOG", "LOG_ID");

		Date date = systemService.getSystemDate();
		
		String old_str = oldObject.toString();
		String new_str = newObject.toString();
		
		JSONArray old_arr = JSONArray.fromObject("["+old_str+"]");
		JSONArray new_arr = JSONArray.fromObject("["+new_str+"]");
		
		JSONObject old_json = (JSONObject)old_arr.get(0);
		JSONObject new_json = (JSONObject)new_arr.get(0);
		
		LinkedList<String> old_queue = new LinkedList<String>();
		LinkedList<String> new_queue = new LinkedList<String>();
		
		String logName = null;
		
		Iterator<?> it = old_json.keys();
		while (it.hasNext()) {  
             String key = (String) it.next();  
             String old_value = old_json.getString(key)==null?"":old_json.getString(key);
             String new_value = new_json.getString(key)==null?"":new_json.getString(key);
             if(key.equals("LOG_NAME")){
            	 logName = new_value;
            	 continue;
             }
             if(old_value.equals(new_value)){
            	 old_queue.add(key+"："+old_value);
            	 new_queue.add(key+"："+new_value);
             }else{
            	 old_queue.push(key+"：<font color=blue>"+old_value+"</font>");
            	 new_queue.push(key+"：<font color=red>"+new_value+"</font>");
             }
        }  
		
		StringBuilder old_builder = new StringBuilder();
		StringBuilder new_builder = new StringBuilder();
		for(String str : old_queue){
			old_builder.append(str).append("，");
		}
		for(String str : new_queue){
			new_builder.append(str).append("，");
		}
		
		String old_result = old_builder.toString();
		String new_result = new_builder.toString();
		
		entity.setLogId(id);
		entity.setOperId(model.getOperId());
		entity.setOperCode(model.getOperCode());
		entity.setStaffId(model.getStaffId());
		entity.setOperateObject(target);
		entity.setLogName(logName);
		entity.setOperateType(OPERATE_TYPE_MODIFY);
		entity.setOldObject(old_result.substring(0, old_result.length()-1));
		entity.setNewObject(new_result.substring(0, new_result.length()-1));
		entity.setOperateDate(date);

		smOperateLogService.insertSelective(entity);

	}
}
