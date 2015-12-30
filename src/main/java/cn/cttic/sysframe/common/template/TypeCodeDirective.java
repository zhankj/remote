package cn.cttic.sysframe.common.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.cttic.sysframe.common.model.TreeModel;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.domain.SmSysDict;
import cn.cttic.sysframe.frame.service.SmOrganService;
import cn.cttic.sysframe.frame.service.SmSysDictService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class TypeCodeDirective implements TemplateDirectiveModel {
	
	public static final String _ORG = "_org";
	


	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		/*String typeCodes = params.get("typeCodes") == null? null : params.get("typeCodes").toString();
		
		if(typeCodes != null){
			String[] typeCodeArr = typeCodes.split(",");
			JSONObject rtnJson = new JSONObject();
			for (String typeCode : typeCodeArr) {
				typeCode = typeCode.toLowerCase();
				JSONArray arr = getJSONArray(typeCode);
				rtnJson.element(typeCode, arr);
			}
			StringBuilder sb = new StringBuilder();
			sb.append("<script type='text/javascript'>");
			sb.append("var $smCodes1 =");
			sb.append(rtnJson.toString());
			sb.append("</script>");
			
			env.getOut().write(sb.toString());
		}*/
	}

	private JSONArray getJSONArray(String typeCode) {
		boolean margin = typeCode.startsWith("_");
		JSONArray arr = new JSONArray();
		if(_ORG.equals(typeCode)){
			SmOrganService smOrganService = (SmOrganService) SpringContextUtil.getBean(SmOrganService.class);
			TreeModel rootNode = smOrganService.getTree("Y",true);
			List<TreeModel> list = rootNode.getChildren();
			arr = JSONArray.fromObject(list);
		}else if(margin){
			typeCode = typeCode.substring(1);
			TreeModel root = new TreeModel();
			root.setParentId("-1");
			root.setId("rootId");
			root.setText("虚拟组类型");
			TreeModel rootNode = getTree(root,typeCode);
			arr = JSONArray.fromObject(rootNode);
		}else{
			SmSysDictService smSysDictService = (SmSysDictService) SpringContextUtil.getBean(SmSysDictService.class);
			List<SmSysDict> list = smSysDictService.getSmSysDictList(typeCode);
			for (SmSysDict dict : list) {
				JSONObject json = new JSONObject();
				json.element("id", dict.getParamCode());
				json.element("text", dict.getParamDesc());
				json.element("parentId", dict.getParentParamCode());
				arr.add(json);
			}
		}
		return arr;
	}
	
	
	public TreeModel getTree(TreeModel tm,String typeCode) {
		SmSysDictService smSysDictService = (SmSysDictService) SpringContextUtil.getBean(SmSysDictService.class);
		List<SmSysDict> list = null;
		if("rootId".equals(tm.getId())){
			list = smSysDictService.getSmSysDictList(typeCode,true);
		}else{
			list= smSysDictService.getSmSysDictList(typeCode.toUpperCase(), tm.getId(), typeCode.toUpperCase());
		}
		List<TreeModel> children = new ArrayList<TreeModel>();
		for(SmSysDict dict : list){
			TreeModel c = new TreeModel();
			c.setParentId(tm.getId());
			c.setId(dict.getParamCode());
			c.setText(dict.getParamDesc());
			children.add(c);
			getTree(c,typeCode);
		}
		tm.setChildren(children);
		return tm;
	}
}
