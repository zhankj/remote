package cn.cttic.sysframe.frame.service;

import java.util.Collection;

import cn.cttic.sysframe.frame.model.CacheModel;

/**
 * 缓存管理服务接口类 <br/>
 * @author caohui@cttic.cn
 * @date May 26, 2014 3:39:12 PM
 * <br/>Copyright: Copyright (c) 2014 CTTIC
 */
public interface CacheManageService {
	Collection<CacheModel> getCaches();
	void clearCache(String name);
	void refreshCache(String name);
}
