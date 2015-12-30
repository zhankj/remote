package cn.cttic.sysframe.common.configuration;

import cn.cttic.sysframe.common.util.ConfigurationUtil;


public class SysConfiguration {
	
	//系统实例标识
	public String INSTANCE_ID;
	//系统标识
	public String SYSTEM_ID;
	//定时任务刷新时间（分）
	public String TIMER_REFRESH_PERIOD;
	
	public boolean SSO_ENABLED;
	public String SSO_LOGOUT_URL;
	public String SSO_LOGIN_URL;
	
	public static final SysConfiguration INSTANCE = ConfigurationUtil.loadConfiguration("sysconfig.properties", null, SysConfiguration.class);
	private SysConfiguration(){}
	
}
