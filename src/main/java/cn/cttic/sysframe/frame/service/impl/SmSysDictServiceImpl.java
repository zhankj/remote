package cn.cttic.sysframe.frame.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.common.support.MultiLanguageSupport;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.dao.SmParamDefineMapper;
import cn.cttic.sysframe.frame.dao.SmParamDetailMapper;
import cn.cttic.sysframe.frame.dao.SmSysDictMapper;
import cn.cttic.sysframe.frame.domain.SmParamDetail;
import cn.cttic.sysframe.frame.domain.SmParamDetailExample;
import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.domain.SmSysDictCriteria;
import cn.cttic.sysframe.frame.service.SmSysDictService;

@Service
public class SmSysDictServiceImpl implements SmSysDictService {

	private transient static Log log = LogFactory.getLog(SmSysDictServiceImpl.class);

	private static Map<String, List<TreeModel>> comboTreeModelData = new ConcurrentHashMap<String, List<TreeModel>>();

	private static Map<String, JSONArray> comboTreeJsonData = new ConcurrentHashMap<String, JSONArray>();

	@Autowired
	private SmSysDictMapper mapper;
	
	@Autowired
	private SmParamDetailMapper smParamDetailMapper;
	
	@Autowired
	private SmParamDefineMapper smParamDefineMapper;

	@Override
	@Cacheable(value = "smSysDictCache", key = "'FLAT'")
	public List<SmSysDict> getSysDictList() {
		return mapper.selectByExample(new SmSysDictCriteria());
	}

	@Override
	@Cacheable(value = "smSysDictCache", key = "'MAP'")
	public HashMap<String, List<SmSysDict>> loadSysDictCache() {
		log.debug("加载[系统配置参数]缓存数据......start......");
		HashMap<String, List<SmSysDict>> cacheMap = new HashMap<String, List<SmSysDict>>();
		List<SmSysDict> smSysDictList = SpringContextUtil.getBean(getClass()).getSysDictList();
		if (cacheMap != null) {
			String key = null;
			for (int index = 0; index < smSysDictList.size(); index++) {
				SmSysDict bean = smSysDictList.get(index);
				key = (bean.getTypeCode()).toLowerCase();
				key = key + "_" + bean.getLanguageType().toLowerCase();
				if (!cacheMap.containsKey(key)) {
					cacheMap.put(key, new ArrayList<SmSysDict>());
				}
				cacheMap.get(key).add(bean);
			}
		}
		log.debug("加载[系统配置参数]缓存数据......end......");
		return cacheMap;
	}

	@Override
	public List<SmSysDict> getSmSysDictList(String typeCode) {
		List<SmSysDict> smSysDictList = new ArrayList<SmSysDict>();
		String key = typeCode.toLowerCase();

		String languageType = MultiLanguageSupport.getLanguageType();

		String chineseKey = key + "_zh_cn";
		key = key + "_" + languageType.toLowerCase();

		// 使用 SpringContextUtil.getBean(getClass())
		// 调用避免通过this内部调用导致基于动态代理的cache不生效
		HashMap<String, List<SmSysDict>> smSysDictMap = SpringContextUtil.getBean(getClass()).loadSysDictCache();
		if (smSysDictMap.containsKey(key)) {
			return (List<SmSysDict>) smSysDictMap.get(key);
		} else if (smSysDictMap.containsKey(chineseKey)) {
			return (List<SmSysDict>) smSysDictMap.get(chineseKey);
		}
		return smSysDictList;
	}

	@Override
	public SmSysDict getSmSysDict(String typeCode, String paramCode) {
		List<SmSysDict> smSysDictList = getSmSysDictList(typeCode);
		for (SmSysDict bean : smSysDictList) {
			if (null != paramCode && paramCode.equals(bean.getParamCode())) {
				return bean;
			}
		}
		return new SmSysDict();
	}

	@Override
	public List<SmSysDict> getSmSysDictList(String parentTypeCode, String parentParamCode, String typeCode) {
		List<SmSysDict> smSysDictList = new ArrayList<SmSysDict>();
		String key = typeCode.toLowerCase();
		List<SmSysDict> _smSysDictList = getSmSysDictList(key);
		for (SmSysDict bean : _smSysDictList) {
			if (parentTypeCode.equals(bean.getParentTypeCode()) && parentParamCode.equals(bean.getParentParamCode())) {
				smSysDictList.add(bean);
			}
		}
		return smSysDictList;
	}

	@Override
	public List<SmSysDict> getSmSysDictList(String parentTypeCode, String parentParamCode) {
		List<SmSysDict> smSysDictList = new ArrayList<SmSysDict>();
		List<SmSysDict> _smSysDictList = SpringContextUtil.getBean(getClass()).getSysDictList();
		for (SmSysDict bean : _smSysDictList) {
			if (parentTypeCode.equals(bean.getParentTypeCode()) && parentParamCode.equals(bean.getParentParamCode())) {
				smSysDictList.add(bean);
			}
		}
		return smSysDictList;
	}

	@Override
	public List<SmSysDict> getSmSysDictList(String typeCode, boolean notParent) {
		List<SmSysDict> list = getSmSysDictList(typeCode);
		List<SmSysDict> result = new ArrayList<SmSysDict>();
		if (notParent) {
			for (SmSysDict sys : list) {
				if (StringUtil.isBlank(sys.getParentTypeCode())) {
					result.add(sys);
				}
			}
		} else {
			for (SmSysDict sys : list) {
				if (!StringUtil.isBlank(sys.getParentTypeCode())) {
					result.add(sys);
				}
			}
		}
		return result;
	}

	@Override
	public String getSmSysDictValue(String typeCode, String paramCode) {
		return getSmSysDictValue(typeCode, paramCode, "");
	}

	@Override
	public String getSmSysDictValue(String typeCode, String paramCode, String defaultValue) {
		List<SmSysDict> smSysDictList = getSmSysDictList(typeCode);
		for (SmSysDict bean : smSysDictList) {
			if (StringUtils.equals(paramCode, bean.getParamCode())) {
				return bean.getParamDesc();
			}
		}
		return defaultValue;
	}

	@Override
	public String getSmSysDictValueByRoot(String rootTypeCode, String paramCode, String defaultValue) {
		List<SmSysDict> smSysDictList = selectByRootTypeCode(rootTypeCode);
		for (SmSysDict bean : smSysDictList) {
			if (StringUtils.equals(paramCode, bean.getParamCode())) {
				return bean.getParamDesc();
			}
		}
		return defaultValue;
	}

	@Override
	public List<SmSysDict> selectByRootTypeCode(String typeCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("typeCode", typeCode);
		return mapper.selectByRootTypeCode(map);
	}

	@Override
	public List<TreeModel> getTreeModel(String typeCode) {
		String key = typeCode;
		List<TreeModel> result = comboTreeModelData.get(typeCode);

		if (result != null) {
			return result;
		}
		typeCode = typeCode.substring(1);

		// 获取code
		String[] arrs = typeCode.split("\\$");
		String code = null;
		if (arrs.length > 1) {
			code = arrs[1].split("=")[1];
			typeCode = arrs[0];
		}

		List<TreeModel> resultList = new ArrayList<TreeModel>();

		List<SmSysDict> rootList = getSmSysDictList(typeCode);
		// code不为null的话，用code过滤
		if (null != code) {
			List<SmSysDict> temp = new ArrayList<SmSysDict>();
			for (SmSysDict sd : rootList) {
				if (code.equals(sd.getParamCode())) {
					temp.add(sd);
				}
			}
			rootList = temp;
		}

		for (SmSysDict dict : rootList) {
			TreeModel tm = new TreeModel();
			tm.setId(dict.getParamCode());
			tm.setText(dict.getParamDesc());
			// tm.setTypeCode(typeCode);
			JSONObject attr = new JSONObject();
			attr.element("typeCode", dict.getTypeCode());
			tm.setAttributes(attr);
			tm.setState("closed");
			resultList.add(tm);
			setChildren(tm, dict.getTypeCode(), dict.getParamCode());
		}
		// result = JSONArray.fromObject(resultList);
		comboTreeModelData.put(key, resultList);
		return resultList;
	}

	private void setChildren(TreeModel model, String parentTypeCode, String parentParamCode) {
		List<TreeModel> resultList = new ArrayList<TreeModel>();
		List<SmSysDict> rootList = getSmSysDictList(parentTypeCode, parentParamCode);
		if (null != rootList && rootList.size() > 0) {
			for (SmSysDict dict : rootList) {
				TreeModel tm = new TreeModel();
				tm.setId(dict.getParamCode());
				tm.setText(dict.getParamDesc());
				// tm.setTypeCode(typeCode);
				JSONObject attr = new JSONObject();
				attr.element("typeCode", dict.getTypeCode());
				tm.setAttributes(attr);
				tm.setState("closed");
				resultList.add(tm);
				if (null != dict.getTypeCode()) {
					setChildren(tm, dict.getTypeCode(), dict.getParamCode());
				}
			}
			model.setChildren(resultList);
		} else {
			// 如果model是叶子节点，把state设置成null
			model.setState(null);
		}
	}

	@Override
	public JSONArray getTreeJSONArray(String typeCode) {
		JSONArray result = comboTreeJsonData.get(typeCode);
		if (result != null && result.size() > 0) {
			return result;
		}
		List<TreeModel> tree = getTreeModel(typeCode);
		result = JSONArray.fromObject(tree);
		comboTreeJsonData.put(typeCode, result);
		return result;
	}

	@Override
	public SmSysDict getSmSysDict(List<String> typeCode, String paramCode) {
		SmSysDictCriteria example = new SmSysDictCriteria();
		SmSysDictCriteria.Criteria criteria = example.or();
		if (StringUtils.isNotBlank(paramCode)) {
			criteria.andParamCodeEqualTo(paramCode);
		}
		if (typeCode != null) {
			criteria.andTypeCodeIn(typeCode);
		}
		List<SmSysDict> smSysDictList = mapper.selectByExample(example);
		if (smSysDictList != null && smSysDictList.size() > 0) {
			return smSysDictList.get(0);
		}
		return null;
	}

	@Override
	public List<SmSysDict> getSmSysDictListByPTypeCode(String parentTypeCode) {
		List<SmSysDict> smSysDictList = new ArrayList<SmSysDict>();
		List<SmSysDict> _smSysDictList = SpringContextUtil.getBean(getClass()).getSysDictList();
		for (SmSysDict bean : _smSysDictList) {

			if (null != bean.getParentTypeCode() && parentTypeCode.toLowerCase().equals(bean.getParentTypeCode().toLowerCase())) {
				smSysDictList.add(bean);
			}
		}
		return smSysDictList;
	}

	@Override
	public List<SmParamDetail> getSmParamDetail(String rootTypeCode,String paramCode) {
		SmParamDetailExample example = new SmParamDetailExample();
		SmParamDetailExample.Criteria criteria = example.createCriteria();
		criteria.andTypeParamCodeEqualTo(paramCode);
		List<String> typeCodes = smParamDefineMapper.selectByParentTypeCode(rootTypeCode);
		criteria.andTypeCodeIn(typeCodes);
		List<SmParamDetail> smParamDetailList = smParamDetailMapper.selectByExample(example);
		return smParamDetailList;
	}

}
