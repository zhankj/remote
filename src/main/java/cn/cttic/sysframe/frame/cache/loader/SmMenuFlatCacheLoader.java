package cn.cttic.sysframe.frame.cache.loader;

import org.springframework.beans.factory.annotation.Autowired;

import cn.cttic.sysframe.cache.CacheLoader;
import cn.cttic.sysframe.frame.service.SmMenuService;


public class SmMenuFlatCacheLoader implements CacheLoader {
	
	@Autowired
	SmMenuService smMenuService;
	
	@Override
    public String getCacheName() {
	    return "smMenuCache";
    }

	@Override
	public Object getCacheKey() {
		return "FLAT";
	}

	@Override
	public Object getCacheTarget() {
		return smMenuService.selectByExample();
	}

}
