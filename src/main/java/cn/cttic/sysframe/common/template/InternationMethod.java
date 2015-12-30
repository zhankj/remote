package cn.cttic.sysframe.common.template;

import java.util.List;

import cn.cttic.sysframe.common.support.MultiLanguageSupport;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 */

public class InternationMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		
		String languageType = MultiLanguageSupport.getLanguageType();
		
		String lang = "zh-cn";
		
		if("en".equals(languageType.toLowerCase())){
			lang = "en";
		}
		
		String onClick = args.get(0).toString();
		
		String temp = onClick.replace(" ", "");
		
		if(temp.length() == 15 || temp.length() == 13){
			return "WdatePicker({lang:'"+lang+"'})";
		}else if(onClick.contains("}")){
			String[] arr = onClick.split("}");
			int length = arr.length;
			String first = "";
			for(int i=0;i<length-1;i++){
				first = first + arr[i] +"}";
			}
			first = first.substring(0,first.length()-1);
			String last = arr[length-1];
			String middle = ",lang:'"+lang+"'}";
			String result = first + middle + last;
			return result;
		}
		return onClick;
	}

}
