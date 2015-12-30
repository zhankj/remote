package cn.cttic.sysframe.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * java class 反射的工具类
 */
public class ClassUtil {
	
	/**
	 * 根据class的name取得对象实例
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object getClassInstance(String className)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException {
		Class clazz = getDefaultClassLoader().loadClass(className);	
		return clazz.newInstance();
	}

	/**
	 * 根据传入的对象实例,方法名称,方法参数取得对象的方法

	 * @param obj
	 * @param methodName
	 * @param param
	 * @return
	 * @throws NoSuchMethodException
	 */
	public static Method getMethod(Object obj, String methodName,
			Class... param) throws NoSuchMethodException {

		return obj.getClass().getMethod(methodName, param);

	}
	/**
	 * 取得当前默认的classLoader
	 * @return
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader classLoader = null;
		try {
			classLoader = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
			return null;
		}
		if (classLoader == null) {
			classLoader = ClassUtil.class.getClassLoader();
		}
		return classLoader;
	}
	
	public static boolean hasMethod(Class clazz, String methodName, Class[] paramTypes) {
		return (getMethodIfAvailable(clazz, methodName, paramTypes) != null);
	}


	public static Method getMethodIfAvailable(Class clazz, String methodName, Class[] paramTypes) {

		try {
			return clazz.getMethod(methodName, paramTypes);
		}
		catch (NoSuchMethodException ex) {
			return null;
		}
	}
	
	/**  
	* 通过反射绕过java的访问控制，向对象中SET属性。  
	* @param target  
	* @param fname  
	* @param ftype  
	* @param fvalue  
	*/
	public static void setFieldValue(Object target, String fname, Class ftype, Object fvalue) {
		if (target == null || fname == null || "".equals(fname)
				|| (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();
		try {
			Method method = clazz.getDeclaredMethod(
					"set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1), ftype);
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			method.invoke(target, fvalue);

		} catch (Exception me) {
			try {
				Field field = clazz.getDeclaredField(fname);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.set(target, fvalue);
			} catch (Exception fe) {
				fe.printStackTrace();
			}
		}
	}
	
	/**
	 * 类属性复制工具 类，定制类。
	 * 	当目标对象中存在属性值时，跳过复制
	 *
	 * @param targetBean
	 * @param sourceBean
	 * @return
	 * @throws Exception 
	 * @author zhangzechen
	 * @date 2015年7月9日 下午2:52:20
	 */
	public static <T> T getBean(T sourceBean, T targetBean) throws Exception {
		if (targetBean == null)
			return null;
		Field[] tFields = getAllDeclaredFields(targetBean); //targetBean.getClass().getDeclaredFields();
		Field[] sFields = getAllDeclaredFields(sourceBean); //sourceBean.getClass().getDeclaredFields();
		
		try {
			for (Field field : tFields) {
				String fieldName = field.getName();
				
				// 取出目标对象中的属性值
				String fieldMethodStr = "";
				if(fieldName.equals("pEntId")){
					fieldMethodStr = getGetMethodName(fieldName, false);
				} else {
					fieldMethodStr = getGetMethodName(fieldName);
				}
				Method fieldMethod = targetBean.getClass().getMethod(fieldMethodStr, null);;
				Object fieldValue = fieldMethod.invoke(sourceBean, null);
				
				// 如果属性值不为空跳过
				if(null == fieldValue){
					continue;
				}
				
				// 当前拷贝为浅拷贝，不复制集合类对象
				if (field.getType() == Map.class)
					continue;
				if (field.getType() == Set.class)
					continue;
				if (field.getType() == List.class)
					continue;
				
				// 寻找源对象中的属性值，进行数据拷贝
				for (Field sField : sFields) {
					if (!sField.getName().equals(fieldName)) {
						continue;
					}
					
					Class type = field.getType();
					
					String setName = "";
					String getName = "";
					if(fieldName.equals("pEntId")){
						setName = getSetMethodName(fieldName, false);
						getName = getGetMethodName(fieldName, false);
					} else {
						setName = getSetMethodName(fieldName);
						getName = getGetMethodName(fieldName);
					}
					Method tMethod = targetBean.getClass().getMethod(setName, new Class[] { type });
					Method sMethod = sourceBean.getClass().getMethod(getName, null);
					
					Object setterValue = sMethod.invoke(sourceBean, null);
					tMethod.invoke(targetBean, new Object[] { setterValue });
				}
			}
		} catch (Exception e) {
			throw new Exception("设置参数信息发生异常", e);
		}
		return targetBean;
	}

	/**
	 * 取出对象类属性、对象父类属性
	 *
	 * @param bean
	 * @return 
	 * @author zhangzechen
	 * @date 2015年7月13日 上午10:09:42
	 */
	public static <T> Field[] getAllDeclaredFields(T bean){
		List<Field> fieldList = new ArrayList<Field>();
		Class<?> clazz = bean.getClass() ;
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields();
				if(null != fields && fields.length > 0){
					fieldList.addAll(Arrays.asList(fields));
				}
			} catch (Exception e) {
			}
		}
		Field[] fieldArry = new Field[fieldList.size()];
		return fieldList.toArray(fieldArry);
	}
	
	private static String getGetMethodName(String fieldName, boolean firstCharUpper) {
		if(firstCharUpper){
			fieldName = replaceFirstCharToUpper(fieldName);
		}
		return "get" + fieldName;
	}
	
	private static String getGetMethodName(String fieldName) {
		fieldName = replaceFirstCharToUpper(fieldName);
		return "get" + fieldName;
	}

	private static String getSetMethodName(String fieldName, boolean firstCharUpper) {
		if(firstCharUpper){
			fieldName = replaceFirstCharToUpper(fieldName);
		}
		return "set" + fieldName;
	}
	
	private static String getSetMethodName(String fieldName) {
		fieldName = replaceFirstCharToUpper(fieldName);
		return "set" + fieldName;
	}

	private static String replaceFirstCharToUpper(String fieldName) {
		char[] chars = new char[1];
		chars[0] = fieldName.charAt(0);
		String temp = new String(chars);
		if (chars[0] >= 'a' && chars[0] <= 'z') {
			fieldName = fieldName.replaceFirst(temp, temp.toUpperCase());
		}
		return fieldName;
	}
}
