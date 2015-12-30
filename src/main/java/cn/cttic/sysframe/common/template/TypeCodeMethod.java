package cn.cttic.sysframe.common.template;

import java.util.List;

import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.common.util.TypeCodeUtil;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
/**
 * 
 * freemarker方法，用来在.ftl使用获取对应类型码表、对应参数的中文
 * 
 * 如：${getCodeDesc("sm_sex",entity.sex)} 
 */

public class TypeCodeMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		
		if (args == null || args.size() < 2 || StringUtil.isEmpty(args.get(0)) ||StringUtil.isEmpty(args.get(1)) ) {
			return null;
		}
		
		String typeCode = args.get(0).toString();
		
		typeCode = typeCode.toLowerCase();
		
		String paramCode =  args.get(1).toString();
		
		String result = TypeCodeUtil.getCodeDesc(typeCode, paramCode);
		
		if(null == result){
			result = paramCode;
			//如果静态参数表没有找到指定的参数，则尝试返回默认值
			if (args.size() >= 3 && args.get(2) != null) {
				return args.get(2).toString();
			}
		}
		
		return result;
	}

}
