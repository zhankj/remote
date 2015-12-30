package cn.cttic.sysframe.common.template;

import java.util.Date;
import java.util.List;

import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.frame.service.SystemService;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
/**
 * 
 * 标题、简要说明. <br>
 * 类详细说明.
 * @author yangrui@cttic.cn
 * @date 2014-5-5 下午7:30:10 <br>
 * Copyright: Copyright (c) 2014 CTTIC
 */
public class SystemDateMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		
		String fmt = DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS;
		
		if(args.size()>0){
			fmt = args.get(0).toString();
		}
		
		SystemService systemService = (SystemService) SpringContextUtil.getBean(SystemService.class);
		
		Date date = systemService.getSystemDate();
		
		return DateUtil.format(date, fmt);
		
	}

}
