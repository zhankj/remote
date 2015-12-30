package cn.cttic.sysframe.frame.model;

import java.util.List;
import java.util.Map;

import cn.cttic.sysframe.frame.domain.SmSysDict;

/**
 * 
 * 临时接收静态参数集合 . <br>
 * 后期曹晖会做封装.
 * 
 * @author houbaolin@cttic.cn
 * @date 2014年6月19日 下午3:47:31 <br>
 * Copyright: Copyright (c) 2014 CTTIC
 */
public class SmSysDictModel {

	private Map<String, List<SmSysDict>> smSysDictMap;

	public Map<String, List<SmSysDict>> getSmSysDictMap() {
		return smSysDictMap;
	}

	public void setSmSysDictMap(Map<String, List<SmSysDict>> smSysDictMap) {
		this.smSysDictMap = smSysDictMap;
	}

}
