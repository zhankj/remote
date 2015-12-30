package cn.cttic.sysframe.common.util.bean;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.LazyDynaMap;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;

public class BeanCopyUtil {
	private static Logger LOG = LoggerFactory.getLogger(BeanCopyUtil.class);
	
	public static Class<?> classForName(String packageName, String classSimpleName) {
		try {
			return Class.forName(packageName + "." + classSimpleName);
		} catch (ClassNotFoundException e) {
			throw new SystemException(e, StatusCodeForFrameCore.NEW_INSTANCE_ERROR);
		}
	}

	public static <E> E newInstanceByClass(Class<E> theClass) {
		try {
			return theClass.newInstance();
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.NEW_INSTANCE_ERROR);
		}
	}

	public static Object newInstanceByClassSimpleName(String packageName, String classSimpleName) {
		return newInstanceByClass(classForName(packageName, classSimpleName));
	}

	public static <E> E buildInstance(Element element, Class<E> classOfIns) {
		LazyDynaMap bean = new LazyDynaMap();
		for (Element prop : element.getChildren()) bean.set(prop.getName(), prop.getValue());
		for (Attribute attr : element.getAttributes()) bean.set(attr.getName(), attr.getValue());

		E ins = newInstanceByClass(classOfIns);

		// 注册转换对象
		ConvertUtils.register(DateConverterOfBeanUtils.getInstance(), java.util.Date.class);
		
		try {
			BeanUtils.copyProperties(ins, bean);
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.TYPE_CAST_ERROR);
		}

		return ins;
	}

	public static Object buildInstance(Element element, String packageName) {
		String className = element.getName();
		return buildInstance(element, classForName(packageName, className));
	}
	
	/**
	 * 更新目标对象的指定属性为指定值
	 * @param bean 目标对象
	 * @param name 属性名
	 * @param value 指定值
	 */
	public static void setProperty(Object bean, String name, Object value) {
		try {
			BeanUtils.setProperty(bean, name, value);
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.UPDATE_PROP_ERROR, bean.getClass().getSimpleName(), name);
		}
	}
	
	/**
	 * 获取目标对象的指定属性
	 * @param bean
	 * @param name
	 * @return 
	 * @author caohui
	 * @date Jun 10, 2014 2:54:36 PM
	 */
	public static Object getProperty(Object bean, String name) {
		Object property;
		try {
			property = BeanUtils.getProperty(bean, name);
		}catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.GET_PROP_ERROR, bean.getClass().getSimpleName(), name);
		}
		return property;
	}
	
	/**
	 * 如果指定目标对象的指定属性为空字符串，则重置为null
	 * @param bean
	 * @param fieldNames
	 * @param isTrim 
	 * @author caohui
	 * @date Jul 12, 2014 11:41:09 AM
	 */
	public static void setEmptyStringToNull(Object bean, List<String> fieldNames, boolean isTrim) {
		for (String fieldName : fieldNames) {
			Object field = getProperty(bean, fieldName);
			if (field.getClass().equals(String.class)) {
				String strField = (String) field;
				if (isTrim) strField = strField.trim();
				if (strField.length() == 0) setProperty(bean, fieldName, null);
			}
		}
	}
	
	public static void copyProperties(Object dest, Object orig, List<String> fieldNames, boolean copyNullField, boolean copyEmptyStringField) {
		for (String fieldName : fieldNames) {
			if (!existField(dest, fieldName) || !existField(orig, fieldName)) {
	            continue;
            }
			try {
				Object origField = BeanUtils.getProperty(orig, fieldName);
				if (!copyNullField && origField == null) continue;
				if (!copyEmptyStringField  && origField != null) {
	                if (origField instanceof String && ((String)origField).trim().equals("")) continue;
                }
				
	            BeanUtils.setProperty(dest, fieldName, BeanUtils.getProperty(orig, fieldName));
            }
            catch (Exception e) {
	            throw new SystemException(e, StatusCodeForFrameCore.FIELD_COPY_ERROR, fieldName, orig, dest);
            }
        }
	}
	
	public static void copyProperties(Object dest, Object orig, List<String> fieldNames) {
		copyProperties(dest, orig, fieldNames, true, true);
	}
	
	public static void copyNonEmptyProperties(Object dest, Object orig, List<String> fieldNames) {
		copyProperties(dest, orig, fieldNames, false, false);
	}
	
	/**
	 * 返回指定的类是否包含指定的属性字段
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static boolean existField(Class<?> clazz, String fieldName) {
		boolean result = true;
		try {
			clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			LOG.debug("Class " +clazz.getSimpleName() + " 的字段 "+fieldName+" 不存在");
			return false;
		}
		
		return result;
	}
	
	public static boolean existField(Object obj, String fieldName) {
		return existField(obj.getClass(), fieldName);
	}
	
}
