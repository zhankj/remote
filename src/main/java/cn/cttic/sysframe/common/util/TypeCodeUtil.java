package cn.cttic.sysframe.common.util;

import java.util.List;

import net.sf.json.JSONArray;
import cn.cttic.sysframe.common.model.TreeModel;

public class TypeCodeUtil {
	
	public static String getCodeDesc(String typeCode,String paramCode) {
		
		String result = null;
		/*
		if(TypeCodeDirective._ORG.equals(typeCode.toLowerCase())){
			SmOrganService smOrganService = (SmOrganService) SpringContextUtil.getBean(SmOrganService.class);
			SmOrgan org = smOrganService.selectByPrimaryKey(paramCode);
			if (org != null) {
				result = org.getOrgName();
			}
		}else if(typeCode.startsWith("_")){
			SmSysDictService smSysDictService = (SmSysDictService) SpringContextUtil.getBean(SmSysDictService.class);
			List<TreeModel> arr = smSysDictService.getTreeModel(typeCode.toLowerCase());
			TreeModel bean = loopTreeModel(arr,paramCode);
			result = bean.getText();
		}else{
			SmSysDictService smSysDictService = (SmSysDictService) SpringContextUtil.getBean(SmSysDictService.class);
			SmSysDict bean = smSysDictService.getSmSysDict(typeCode, paramCode);
			if(null != bean){
				result = bean.getParamDesc();
			}
		}*/
		return result;
	}

	private static TreeModel loopTreeModel(List<TreeModel> arr, String paramCode) {
		for(TreeModel model : arr){
			if(model.getId().equals(paramCode)){
				return model;
			}else if(model.getChildren() !=null && model.getChildren().size() > 0){
				return loopTreeModel(model.getChildren(),paramCode);
			}
		}
		return null;
	}

}
