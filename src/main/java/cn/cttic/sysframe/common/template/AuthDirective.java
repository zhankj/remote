package cn.cttic.sysframe.common.template;

import java.io.IOException;
import java.util.Map;

import cn.cttic.sysframe.common.util.SessionInfo;
import cn.cttic.sysframe.common.util.StringUtil;
import cn.cttic.sysframe.frame.model.SmOperModel;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class AuthDirective implements TemplateDirectiveModel {


	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String ifAllGranted = params.get("ifAllGranted") == null? null : params.get("ifAllGranted").toString();
		
		String ifNotGranted = params.get("ifNotGranted") == null? null : params.get("ifNotGranted").toString();
		
		String ifAnyGranted = params.get("ifAnyGranted") == null? null : params.get("ifAnyGranted").toString();
		
		int paramLen = 0;
		if(!StringUtil.isBlank(ifAllGranted)){
			paramLen++;
		}
		if(!StringUtil.isBlank(ifNotGranted)){
			paramLen++;
		}
		if(!StringUtil.isBlank(ifAnyGranted)){
			paramLen++;
		}
		if(paramLen == 1){
			SmOperModel sm = SessionInfo.getOperInfo();
			
			Map<String, String> menus = sm.getMenuRigthMap();
			
			if(!StringUtil.isBlank(ifNotGranted)){
				String[] arrs = ifNotGranted.split(",");
				int len = 0;
				for(String str : arrs){
					if(!menus.containsKey(str)){
						len++;
					}
				}
				if(len == arrs.length){
					body.render(env.getOut());
				}
			}else if(!StringUtil.isBlank(ifAllGranted)){
				String[] arrs = ifAllGranted.split(",");
				int len = 0;
				for(String str : arrs){
					if(menus.containsKey(str)){
						len++;
					}
				}
				if(len == arrs.length){
					body.render(env.getOut());
				}
			}else if(!StringUtil.isBlank(ifAnyGranted)){
				String[] arrs = ifAnyGranted.split(",");
				for(String str : arrs){
					if(menus.containsKey(str)){
						body.render(env.getOut());
						break;
					}
				}
			}
		}
		
	}

}
