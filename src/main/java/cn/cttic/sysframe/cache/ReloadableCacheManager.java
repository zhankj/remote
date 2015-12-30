package cn.cttic.sysframe.cache;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.support.SimpleCacheManager;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 可重加载的缓存管理器 <br/>
 * 扩展自 <code>SimpleCacheManager</code>，在其基础上增加了 <code>CacheLoader</code> 列表，可在应用启动时加载，或应用运行时重新加载指定的缓存
 * @author caohui@cttic.cn
 * @date May 26, 2014 2:26:47 PM
 * <br/>Copyright: Copyright (c) 2014 CTTIC
 */
public class ReloadableCacheManager extends SimpleCacheManager {
	private static final Logger LOG = LoggerFactory.getLogger(ReloadableCacheManager.class);
	
	private Map<String, Collection<CacheLoader>> cacheLoadersMap = Maps.newLinkedHashMap();
	
    /**
     * 设置自定义缓存加载器
     *
     * @param cacheLoaders 
     * @author caohui
     * @date May 23, 2014 1:32:06 PM
     */
    public void setCacheLoaders(Collection<? extends CacheLoader> cacheLoaders) {
    	for (CacheLoader loader : cacheLoaders) {
    		if (this.cacheLoadersMap.get(loader.getCacheName()) == null) {
    			Collection<CacheLoader> loaders = Lists.newArrayList();
    			this.cacheLoadersMap.put(loader.getCacheName(), loaders);
    			loaders.add(loader);
    		}
    		else {
    			Collection<CacheLoader> loaders = this.cacheLoadersMap.get(loader.getCacheName());
    			loaders.add(loader);
    		}
        }
    }
    
    /**
     * 缓存初始化方法，CacheManager实例化成功后调用
     * @author caohui
     * @date May 23, 2014 1:31:37 PM
     */
    public void initializeCaches() {
    	for (Map.Entry<String, Collection<CacheLoader>> entry : this.cacheLoadersMap.entrySet()) {
    		for (CacheLoader loader : entry.getValue()) {
    			Cache cache = this.getCache(loader.getCacheName());
    			if (cache == null) throw new SystemException(loader.getCacheName()+ "缓存没有定义",StatusCodeForFrame.CACHE_INITIALIZE_ERROR);
    			try {
    				cache.put(loader.getCacheKey(), loader.getCacheTarget());
    				LOG.debug(loader.getCacheName() + "#" + loader.getCacheKey() + "缓存加载成功");
    			}
    			catch (Exception e) {
    				throw new SystemException(e, loader.getCacheName()+ "缓存加载失败",StatusCodeForFrame.CACHE_INITIALIZE_ERROR);
    			}
    		}
        }
    	
    }
    
    /**
     * Spring实例化bean后会调用该方法
     * 
     * @author caohui
     * @date May 26, 2014 9:35:11 AM
     */
    @Override
    public void afterPropertiesSet() {
	    super.afterPropertiesSet();
	    this.initializeCaches();
    }
    
    /**
     * 获取指定缓存区的加载器列表
     *
     * @param cacheName
     * @return 
     * @author caohui
     * @date May 23, 2014 1:33:23 PM
     */
    public Collection<CacheLoader> getCacheLoaders(String cacheName) {
    	return cacheLoadersMap.get(cacheName);
    }
    
    /**
     * 指定的缓存区是否可重新加载
     *
     * @param cacheName
     * @return 
     * @author caohui
     * @date Jun 16, 2014 10:29:04 AM
     */
    public boolean isReloadability(String cacheName) {
    	return this.cacheLoadersMap.containsKey(cacheName);
    }
    
    /**
     * 获取全部缓存区列表
     *
     * @return 
     * @author caohui
     * @date May 23, 2014 1:34:37 PM
     */
    public Collection<Cache> getAllCaches() {
    	Collection<Cache> caches = Lists.newArrayList();
    	for (String name : this.getCacheNames()) {
    		caches.add(this.getCache(name));
    	}
    	return caches;
    }

	/**
     * 按名称获取缓存区，不存在则抛出异常
     *
     * @param cacheName
     * @return 
     * @author caohui
     * @date May 23, 2014 1:33:55 PM
     */
    private Cache getNonNullCache(String cacheName) {
    	Cache cache = this.getCache(cacheName);
    	if (cache == null) throw new SystemException(StatusCodeForFrame.CACHE_NOT_EXIST_ERROR, cacheName);
    	return cache;
    }
    
    /**
     * 清空指定缓存区
     *
     * @param cacheName 
     * @author caohui
     * @date May 23, 2014 1:34:22 PM
     */
    public void clearCache(String cacheName) {
    	this.getNonNullCache(cacheName).clear();
    }
    
    /**
     * 获取指定缓存区的指定key的目标缓存对象
     *
     * @param cacheName
     * @param key
     * @return 
     * @author caohui
     * @date May 23, 2014 1:36:53 PM
     */
    public Object getTargetObject(String cacheName, Object key) {
		ValueWrapper value = this.getNonNullCache(cacheName).get(key);
		
		if (value == null) {
			LOG.debug("名字为 "+cacheName+" 的缓存区域中不存在key为 "+key+" 的缓存对象");
			return null;
		}
		
		return value.get();
	}
    
    /**
     * 清除指定缓存区的指定key的目标缓存对象
     *
     * @param cacheName
     * @param key 
     * @author caohui
     * @date May 23, 2014 1:41:19 PM
     */
    public void evictTargetObject(String cacheName, Object key) {
    	this.getNonNullCache(cacheName).evict(key);
    }
    
    /**
     * 重新加载指定的缓存区
     * @param cacheName 
     * @author caohui
     * @date May 26, 2014 3:04:51 PM
     */
    public void reloadCache(String cacheName) {
    	Collection<CacheLoader>  loaders = this.getCacheLoaders(cacheName);
    	if (loaders == null) throw new SystemException(StatusCodeForFrame.CACHE_LOADER_NOT_EXIST_ERROR, cacheName);
    	for (CacheLoader loader : loaders) {
	        this.getNonNullCache(cacheName).put(loader.getCacheKey(), loader.getCacheTarget());
	        LOG.debug("缓存 " + cacheName + "#" + loader.getCacheKey() +" 已重新加载");
        }
    }
	
}

