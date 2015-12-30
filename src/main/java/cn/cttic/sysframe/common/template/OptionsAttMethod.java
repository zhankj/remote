package cn.cttic.sysframe.common.template;

import java.util.List;

import cn.cttic.sysframe.common.util.StringUtil;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * freemarker方法，判断input是否必填
 */

public class OptionsAttMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {

		String options = args.get(0).toString();

		options = options.trim();

		if (!StringUtil.isBlank(options) && options.contains("required:true")) {
			return true;
		}
		return false;
	}

}
