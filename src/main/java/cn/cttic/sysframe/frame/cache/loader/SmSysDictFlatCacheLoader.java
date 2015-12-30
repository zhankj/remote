package cn.cttic.sysframe.frame.cache.loader;

import org.springframework.beans.factory.annotation.Autowired;

import cn.cttic.sysframe.cache.CacheLoader;
import cn.cttic.sysframe.frame.service.SmSysDictService;

public class SmSysDictFlatCacheLoader implements CacheLoader {

	@Override
	public String getCacheName() {
		return "smSysDictCache";
	}

	@Override
	public Object getCacheKey() {
		return "FLAT";
	}

	@Override
	public Object getCacheTarget() {
		return smSysDictService.getSysDictList();
	}

	@Autowired
	private SmSysDictService smSysDictService;

}
