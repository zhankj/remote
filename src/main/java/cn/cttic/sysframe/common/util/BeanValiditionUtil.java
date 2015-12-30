package cn.cttic.sysframe.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.util.bean.BeanCopyUtil;

public class BeanValiditionUtil {
	private final static Logger LOG = LoggerFactory.getLogger(BeanValiditionUtil.class);
	
	public static void checkString(String string, String name) {
		if(StringUtil.isBlank(string)){
			throw new SystemException(name + "不能为空", StatusCodeForFrame.PROPERTY_IS_NULL);
		}
	}
	
	/**
	 * 检查实体<b>entity</b>的名为<b>propName</b>属性值是否为空
	 * @param entity
	 * @param propName
	 */
	public static void checkPropertiesOfEntity(Object entity, String propName) {
		if (BeanCopyUtil.getProperty(entity, propName) == null) {
			throw new SystemException("The value of "+entity.getClass().getSimpleName()+"."+propName+" is null", StatusCodeForFrame.PROPERTITY_NOT_EXISTS);
		}
	}
	
	/**
	 * 检查实体<b>entity</b>的名为<b>propNames</b>的一组属性值是否为空
	 * @param entity
	 * @param propNames
	 */
	public static void chackPropertiesOfEntity(Object entity, String... propNames) {
		for (String propName : propNames) {
			checkPropertiesOfEntity(entity, propName);
		}
	}
}
