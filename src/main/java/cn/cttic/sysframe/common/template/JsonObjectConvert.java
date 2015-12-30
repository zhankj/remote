package cn.cttic.sysframe.common.template;

import java.util.List;

import net.sf.json.JSONArray;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class JsonObjectConvert implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		SimpleSequence src = (SimpleSequence) args.get(0);
		String target = JSONArray.fromObject(src.toList()).toString();
		target = "(" + target + ")";
		return target;
	}

}
