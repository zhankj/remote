package cn.cttic.sysframe.frame.facade;

import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.model.SmSysDictModel;

public interface SysDictFacadeService {

	public SmSysDictModel loadSysDictCache();
	
	public SmSysDict getSmSysDict(String typeCode, String paramCode);
	
	/**
	 * 查询树形字典数据，只需要传入前缀，无需增加 _1ST、_2ND、_3RD
	 *
	 * @param typeCodePreName
	 * @param paramCode
	 * @return 
	 * @author zhangzechen
	 * @date 2014年12月30日 下午5:02:11
	 */
	public SmSysDict getSmSysDictByPreName(String typeCodePreName, String paramCode);
}
