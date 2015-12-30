package cn.cttic.sysframe.common.constants;

import cn.cttic.sysframe.common.util.DateUtil;

public class CommonConstants {
	
	public static final String DEFAULT_DATE_FORMAT = DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS;

	public static class AjaxReturn {

		public final static String STATUS_CODE = "STATUS_CODE";

		public final static String STATUS_INFO = "STATUS_INFO";

		public final static String RETURN_CODE = "RETURN_CODE";

		public final static String RETURN_INFO = "RETURN_INFO";
	}

	public static class AjaxStatus {

		public final static String STATUS_SUCCESS = "1";

		public final static String STATUS_FAILURE = "0";
	}

	public static class Message {

		public final static String QUERY_SUCCESS = "查询成功";

		public final static String SAVE_SUCCESS = "保存成功";
		
		public final static String DELETE_SUCCESS = "删除成功";

		public final static String COMMIT_SUCCESS = "提交成功";

		public final static String CLOSE_SUCCESS = "关闭成功";
		
		public final static String LOGIN_FAIL_NAME = "您输入的用户名不存在，请重新输入！";
		
		public final static String LOGIN_FAIL_ORG_STATUS = "您的工号归属部门不存在或状态已失效，请联系系统管理员！";
		
		public final static String LOGIN_FAIL_STAFF_STATUS = "您的工号归属人员不存在或状态已失效，请联系系统管理员！";
		
		public final static String LOGIN_FAIL_OPER_STATUS = "您的工号状态不正常，请联系系统管理员！";
		
		public final static String LOGIN_OVER_TRY_NUMBER = "您今天登录失败次数超过5次，请联系管理员！";
		
		public final static String LOGIN_FAIL_PASSWORD = "您输入的密码不正确，请重新输入！";
		
		public final static String LOGIN_FAIL_NEWPASSWORD = "新密码不能为空，请您输入新密码！";
		
		public final static String LOGIN_FAIL_OLDPASSWORD = "原服务密码不正确，请重新输入！";
		
		public final static String LOGIN_FAIL_GETOPERINFO ="获取用户信息出错！";
		
	}

	public static class SmFileState {

		public final static String SM_fILE_STATE_U = "U";// 未处理

		public final static String SM_fILE_STATE_S = "S";// 成功

		public final static String SM_fILE_STATE_P = "P";// 处理过程中

		public final static String SM_fILE_STATE_F = "F"; // 失效
	}

	public static class ftpState {

		public final static String FTP_STATE_U = "U";// 正常

		public final static String FTP_STATE_E = "E";// 删除

	}
	public static class ftpParam {
		public final static String FTP_TIMEOUT_SECONDS="FTP_TIMEOUT_SECONDS"; //FTP数据超时时间
		
		public final static String FTP_CONCURRENT_CAPACITY="FTP_CONCURRENT_CAPACITY";//Ftp并发容量
		
		public final static String FTP_CONCURRENT_ACQUIRE_TIMEOUT_SECONDS="FTP_CONCURRENT_ACQUIRE_TIMEOUT_SECONDS";//Ftp并发容量acquire超时(秒)
	}
}
