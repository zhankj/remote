/**
 * 
 */
package cn.cttic.sysframe.frame.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.frame.domain.SmParamDetail;
import cn.cttic.sysframe.frame.domain.SmSysDict;

/**
 * 静态参数
 * 
 * @author
 */
public interface SmSysDictService {

	/**
	 * 
	 * @return
	 */
	List<SmSysDict> getSysDictList();

	public HashMap<String, List<SmSysDict>> loadSysDictCache();

	public List<SmSysDict> getSmSysDictList(String typeCode);

	public List<SmSysDict> getSmSysDictList(String typeCode, boolean notParent);

	public SmSysDict getSmSysDict(String typeCode, String paramCode);

	public List<SmSysDict> getSmSysDictList(String parentTypeCode, String parentParamCode, String typeCode);

	public List<SmSysDict> getSmSysDictList(String parentTypeCode, String parentParamCode);

	public String getSmSysDictValue(String typeCode, String paramCode);

	public String getSmSysDictValue(String typeCode, String paramCode, String defaultValue);

	public String getSmSysDictValueByRoot(String rootTypeCode, String paramCode, String defaultValue);

	public List<SmSysDict> selectByRootTypeCode(String typeCode);

	public List<TreeModel> getTreeModel(String typeCode);

	public JSONArray getTreeJSONArray(String typeCode);

	public SmSysDict getSmSysDict(List<String> typeCode, String paramCode);

	List<SmSysDict> getSmSysDictListByPTypeCode(String parentTypeCode);
	
	public List<SmParamDetail> getSmParamDetail(String rootTypeCode, String paramCode);

}
