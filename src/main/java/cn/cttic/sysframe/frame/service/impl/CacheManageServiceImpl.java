package cn.cttic.sysframe.frame.service.impl;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.cache.ReloadableCacheManager;
import cn.cttic.sysframe.frame.model.CacheModel;
import cn.cttic.sysframe.frame.service.CacheManageService;

import com.google.common.collect.Lists;

@Service
public class CacheManageServiceImpl implements CacheManageService {
	private static Logger LOG = LoggerFactory.getLogger(CacheManageServiceImpl.class);
	/*
	@Autowired(required=true)
	private ReloadableCacheManager cacheManager;
	
    public ReloadableCacheManager getCacheManager() {
    	return cacheManager;
    }

	@Override
	public Collection<CacheModel> getCaches() {
		Collection<CacheModel> cacheModels = Lists.newArrayList();
		for (Cache cache : this.getCacheManager().getAllCaches()) {
			CacheModel model = new CacheModel();
			model.setCacheName(cache.getName());
			model.setKeyAmount(((Map<?,?>)cache.getNativeCache()).size());
			model.setReloadability(this.getCacheManager().isReloadability(cache.getName()));
			cacheModels.add(model);
        }
		return cacheModels;
	}

	@Override
	public void clearCache(String name) {
		this.getCacheManager().clearCache(name);
		LOG.debug("缓存区" + name + "已经清除");
	}

	@Override
	public void refreshCache(String name) {
		this.getCacheManager().reloadCache(name);
		LOG.debug("缓存区" + name + "已经重新加载");
	}
	*/
}
