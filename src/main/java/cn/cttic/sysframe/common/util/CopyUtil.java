package cn.cttic.sysframe.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;

public class CopyUtil {

	/**
	 * 拷贝对象方法
	 */
	public static Object copy(Object objSource) {
		// 实例化出目标对象
		Object objDes = null;
		try {
			// 获取源对象类型
			Class<?> clazz = objSource.getClass();
			// 获取源对象构造函数
			Constructor<?> construtctor = clazz.getConstructor();
			objDes = construtctor.newInstance();
			// 获得源对象所有属性
			Field[] fields = clazz.getDeclaredFields();
			// 遍历所有属性
			for (int i = 0; i < fields.length; i++) {
				// 属性对象
				Field field = fields[i];
				// 属性名
				String fieldName = field.getName();
				// 获取属性首字母
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				// 拼接get方法名如getName
				String getMethodName = "get" + firstLetter + fieldName.substring(1);
				// 得到get方法对象
				Method getMethod = clazz.getMethod(getMethodName);
				// 对源对象调用get方法获取属性值
				Object value = getMethod.invoke(objSource);

				// 拼接set方法名
				String setMethodName = "set" + firstLetter + fieldName.substring(1);
				// 获取set方法对象
				Method setMethod = clazz.getMethod(setMethodName,
						new Class[] { field.getType() });
				// 对目标对象调用set方法装入属性值
				setMethod.invoke(objDes, new Object[] { value });
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,StatusCodeForFrame.DB_OPERATION_ERROR);
		}
		return objDes;
	}


}
