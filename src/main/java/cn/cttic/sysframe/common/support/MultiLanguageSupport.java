package cn.cttic.sysframe.common.support;

import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCode;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.model.ExceptionModel;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.common.util.StringUtil;


public class MultiLanguageSupport {
	private static Logger LOG = LoggerFactory.getLogger(MultiLanguageSupport.class);
	
	private static final String TYPE_PARAM_CODE = "VIEWS.EXCEPTION";
	
	
	public static ExceptionModel generateExceptionModel(StatusCode statusCode, Object... patternArguments) {
		//8位异常编码
		String code = StatusCodeSupport.getFullStatusCode(statusCode);
		
		//异常描述
		//String descTemplate = SpringContextUtil.getBean(SmSysDictServiceImpl.class).getSmSysDict(TYPE_PARAM_CODE, code).getParamDesc();
		String descTemplate  = "";
		descTemplate = descTemplate != null ? descTemplate : statusCode.getDescription();
		String desc = patternArguments != null ? MessageFormat.format(descTemplate, patternArguments) : descTemplate;
		
		ExceptionModel exceptionModel  = new ExceptionModel();
		exceptionModel.setCode(code);
		exceptionModel.setDesc(desc.toString());
		exceptionModel.setType(statusCode.getType());
		
		return exceptionModel;
	}
	
	public static ExceptionModel generateExceptionModel(SystemException ex) {
		ExceptionModel exceptionModel  = generateExceptionModel(ex.getStatusCode(), ex.getStatusCodeArgs());

		if (!StringUtil.isBlank(ex.getMessage()))
			exceptionModel.setMessage(ex.getTraceMessage());
		
		return exceptionModel;
	}
	
	public static String getLanguageType() {
		String languageType = Locale.CHINA.toString(); 
		try {
	        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
	        Locale local = localeResolver.resolveLocale(request);

	        languageType = local.toString();
        }
        catch (Exception e) {
	        LOG.debug("无法从请求中获取当前语言类型" + e);
        }
        return languageType;
    }
	
	/**
	 * 设置语言区域类型
	 *
	 * @param locale
	 * @param response
	 * @param request 
	 * @author caohui
	 * @date Aug 11, 2014 1:38:42 PM
	 */
	public static void setLanguageType(String locale, HttpServletResponse response, HttpServletRequest request) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (localeResolver == null) {
			throw new SystemException("LocaleResolver未找到，请确认该请求通过DispatcherServlet分配", StatusCodeForFrame.INSIDE_ERROR);
		}

		LocaleEditor localeEditor = new LocaleEditor();
		localeEditor.setAsText(locale);
		localeResolver.setLocale(request, response, (Locale) localeEditor.getValue());
	}
	
}
