package cn.cttic.sysframe.cache;

/**
 * 自定义缓存加载接口 <br/>
 * 如果需要在应用启动时初始化缓存，或者在应用运行时刷新缓存，请实现此接口
 * @author caohui@cttic.cn
 * @date May 26, 2014 10:06:59 AM
 * <br/>Copyright: Copyright (c) 2014 CTTIC
 */
public interface CacheLoader {
	/**
	 * 指定缓存存放的缓存区名称
	 * @return 
	 * @author caohui
	 * @date May 26, 2014 10:08:34 AM
	 */
	String getCacheName();
	/**
	 * 指定缓存的key值
	 * @return 
	 * @author caohui
	 * @date May 26, 2014 10:10:41 AM
	 */
	Object getCacheKey();
	/**
	 * 缓存的目标对象，返回的目标对象将缓存在指定缓存区的指定key上
	 * @return 
	 * @author caohui
	 * @date May 26, 2014 10:11:59 AM
	 */
	Object getCacheTarget();
}
