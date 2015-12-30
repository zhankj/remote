package cn.cttic.sysframe.frame.facade.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.facade.SysDictFacadeService;
import cn.cttic.sysframe.frame.model.SmSysDictModel;
import cn.cttic.sysframe.frame.service.SmSysDictService;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;

@Service
@ReflectionInvokable(name = "sysdict")
public class SysDictFacadeServiceImpl implements SysDictFacadeService {

	@Autowired
	private SmSysDictService smSysDictService;

	@Override
	@ReflectionInvokable(name = "loadSysDictCache")
	public SmSysDictModel loadSysDictCache() {
		HashMap<String, List<SmSysDict>> cacheMap = smSysDictService.loadSysDictCache();
		SmSysDictModel smSysDictModel = new SmSysDictModel();
		smSysDictModel.setSmSysDictMap(cacheMap);
		return smSysDictModel;
	}

	@Override
	@ReflectionInvokable(name = "getSmSysDict")
	public SmSysDict getSmSysDict(String typeCode, String paramCode) {
		return smSysDictService.getSmSysDict(typeCode, paramCode);
	}

	@Override
	@ReflectionInvokable(name = "getSmSysDictByPreName")
	public SmSysDict getSmSysDictByPreName(String typeCodePreName,
			String paramCode) {
		// 从最低级开始查找
		SmSysDict smSysDict = getSmSysDict(typeCodePreName + "_3RD", paramCode);
		if(StringUtils.isEmpty(smSysDict.getParamCode())){
			smSysDict = getSmSysDict(typeCodePreName + "_2ED", paramCode);
			if(StringUtils.isEmpty(smSysDict.getParamCode())){
				smSysDict = getSmSysDict(typeCodePreName + "_1ST", paramCode);
			}
		}
		return smSysDict;
	}
}
