package cn.cttic.common;

import cn.cttic.sysframe.common.util.ConfigurationUtil;

public class RedisConfig {
	public int  maxIdle;
	public int  maxTotal;
	public int  maxWaitMillis;
	public int  retryNum;
	public int  timeout;
	public String  ip;
	public int  port;

	public static final RedisConfig INSTANCE = ConfigurationUtil.loadConfiguration("data/redis.properties", null, RedisConfig.class);
	private RedisConfig(){}

}
