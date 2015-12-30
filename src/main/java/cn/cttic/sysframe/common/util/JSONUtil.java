package cn.cttic.sysframe.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JSONString;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import net.sf.json.processors.JsonValueProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cttic.sysframe.common.constants.CommonConstants;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * JSON工具类，反射的方式转换整个对象
 */
public class JSONUtil {

	private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

	private static JSONUtil instance = null;

	public JSONUtil() {

	}

	/**
	 * 代理类时做的检查.返回应该检查的对象.
	 * 
	 * @param bean
	 * @return
	 */
	protected Object proxyCheck(Object bean) {
		return bean;
	}

	static public String toJSONString(Object obj) throws JSONException {
		return toJSONString(obj, false);
	}

	static public String toJSONString(Object obj, boolean useClassConvert) throws JSONException {
		if (instance == null)
			instance = new JSONUtil();
		return instance.getJSONObject(obj, useClassConvert).toString();
	}

	@SuppressWarnings("unchecked")
	private String getJSONArray(Object arrayObj, boolean useClassConvert) throws JSONException {

		if (arrayObj == null)
			return "null";

		arrayObj = proxyCheck(arrayObj);

		JSONArray jSONArray = new JSONArray();
		if (arrayObj instanceof Collection) {
			Iterator iterator = ((Collection) arrayObj).iterator();
			while (iterator.hasNext()) {
				Object rowObj = iterator.next();
				if (rowObj == null)
					jSONArray.add(new JSONStringObject(null));
				else if (rowObj.getClass().isArray() || rowObj instanceof Collection)
					jSONArray.add(getJSONArray(rowObj, useClassConvert));
				else
					jSONArray.add(getJSONObject(rowObj, useClassConvert));
			}
		}
		if (arrayObj.getClass().isArray()) {
			int arrayLength = Array.getLength(arrayObj);
			for (int i = 0; i < arrayLength; i++) {
				Object rowObj = Array.get(arrayObj, i);
				if (rowObj == null)
					jSONArray.add(new JSONStringObject(null));
				else if (rowObj.getClass().isArray() || rowObj instanceof Collection)
					jSONArray.add(getJSONArray(rowObj, useClassConvert));
				else
					jSONArray.add(getJSONObject(rowObj, useClassConvert));
			}
		}
		return jSONArray.toString();
	}

	@SuppressWarnings("unchecked")
	JSONStringObject getJSONObject(Object value, boolean useClassConvert) throws JSONException {

		// 处理原始类型

		if (value == null) {
			return new JSONStringObject("null");
		}
		value = proxyCheck(value);
		if (value instanceof JSONString) {
			Object o;
			try {
				o = ((JSONString) value).toJSONString();
			}
			catch (Exception e) {
				throw new JSONException(e);
			}
			throw new JSONException("Bad value from toJSONString: " + o);
		}
		if (value instanceof Number) {
			return new JSONStringObject(StringUtil.toString(value));
		}
		if (value instanceof Boolean || value instanceof JSONObject || value instanceof JSONArray) {
			return new JSONStringObject(value.toString());
		}
		if (value instanceof Timestamp) {
			String str = DateUtil.format((Timestamp) value, "yyyy-MM-dd HH:mm:ss");
			return new JSONStringObject(str);
		}
		if (value instanceof Date) {
			String str = DateUtil.format((Date) value, "yyyy-MM-dd HH:mm:ss");
			return new JSONStringObject(str);
		}
		if (value instanceof String)
			return new JSONStringObject(value.toString());
		if (value instanceof Map) {

			JSONObject jSONObject = new JSONObject();

			Iterator iterator = ((Map) value).keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next().toString();
				Object valueObj = ((Map) value).get(key);
				jSONObject.put(key, getJSONObject(valueObj, useClassConvert));
			}
			return new JSONStringObject(jSONObject.toString());
		}

		// class

		if (value instanceof Class)
			return new JSONStringObject(((Class) value).getSimpleName());

		// 数组

		if (value instanceof Collection || value.getClass().isArray()) {
			return new JSONStringObject(getJSONArray(proxyCheck(value), useClassConvert));
		}

		return reflectObject(value, useClassConvert);
	}// value.equals(null)

	@SuppressWarnings("unchecked")
	private JSONStringObject reflectObject(Object bean, boolean useClassConvert) {
		JSONObject jSONObject = new JSONObject();

		Class klass = bean.getClass();
		Method[] methods = klass.getMethods();
		for (int i = 0; i < methods.length; i += 1) {
			try {
				Method method = methods[i];
				String name = method.getName();
				String key = "";
				if (name.startsWith("get")) {
					key = name.substring(3);
				} else if (name.startsWith("is")) {
					key = name.substring(2);
				}
				if (key.length() > 0 && Character.isUpperCase(key.charAt(0)) && method.getParameterTypes().length == 0) {
					if (key.length() == 1) {
						key = key.toLowerCase();
					} else if (!Character.isUpperCase(key.charAt(1))) {
						key = key.substring(0, 1).toLowerCase() + key.substring(1);
					}
					Object elementObj = method.invoke(bean, null);
					if (!useClassConvert && elementObj instanceof Class)
						continue;

					jSONObject.put(key, getJSONObject(elementObj, useClassConvert));
				}
			}
			catch (Exception e) {
				/**//* forget about it */
			}
		}
		return new JSONStringObject(jSONObject.toString());
	}

	/**
	 * 
	 * 
	 * @param json
	 * @return
	 * @author KONGJF
	 * @date 2014-5-13 下午3:26:21
	 */
	public static List formatJSONToList(String json, Class<?> clazz) {
		JSONArray jsonarray = JSONArray.fromObject(json);
		List list = (List) JSONArray.toCollection(jsonarray, clazz);
		return list;
	}

	/**
	 * 使用自定义日期格式，处理Date对象
	 * 
	 * @param javaObj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final JSONArray formatJSONDateList(Object javaObj) {
		JSONArray jsonArray = new JSONArray();
		if (javaObj instanceof Collection) {
			Iterator iterator = ((Collection) javaObj).iterator();
			while (iterator.hasNext()) {
				Object rowObj = iterator.next();
				if (rowObj != null) {
					jsonArray.add(formatJSONDate(rowObj, null));
				}
			}
		} else {
			jsonArray.add(formatJSONDate(javaObj, null));
		}
		return jsonArray;
	}

	/**
	 * 使用自定义日期格式，处理Date对象
	 * 
	 * @param javaObj,dateFormatPattern
	 * @return JSON
	 */
	public static final JSON formatJSONDate(Object javaObj, String dateFormatPattern) {
		JsonConfig jsonCfg = null;
		if (null == dateFormatPattern || "".equals(dateFormatPattern.trim())) {
			jsonCfg = getJsonCfgDeault();
		} else {
			jsonCfg = getJsonCfgWithPatten(dateFormatPattern);
		}
		return JSONSerializer.toJSON(javaObj, jsonCfg);
	}

	/**
	 * 以yyyy-MM-dd HH:mm:ss格式创建JsonConfig对象
	 * 
	 * @return
	 */
	private static JsonConfig getJsonCfgDeault() {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getJsonCfgWithPatten(pattern);
	}

	/**
	 * 以dateFormatPattern格式，创建新的JsonConfig对象
	 * 
	 * @param dateFormatPattern Date的格式化模式
	 * @return
	 */
	private static JsonConfig getJsonCfgWithPatten(String dateFormatPattern) {
		final SimpleDateFormat fm = new SimpleDateFormat(dateFormatPattern);
		JsonConfig jsonCfg = new JsonConfig();
		jsonCfg.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {

			@Override
			public Object processObjectValue(String key, Object value, JsonConfig cfg) {
				if (value == null) {
					return "";
				} else {
					return fm.format((Date) value);
				}
			}

			@Override
			public Object processArrayValue(Object date, JsonConfig arg1) {
				return fm.format((Date) date);
			}
		});
		return jsonCfg;
	}

	/**
	 * 使用符合Javascript中Date类型的数据格式转换java对象中的java.util.Date实例
	 * 转换后的Date类型的对象所产生的json数据如: { dateFile:{ "year":2013, "month":9, "day":9,
	 * "hours":21, "minutes":22, "seconds":41, "milliseconds":991 } }
	 * 
	 * @param javaObj
	 * @return JSON
	 */
	public static final JSON formatJSONDateDeault(Object javaObj) {
		JsonConfig jsonCfg = new JsonConfig();
		jsonCfg.registerJsonValueProcessor(Date.class, new JsDateJsonValueProcessor());
		return JSONSerializer.toJSON(javaObj, jsonCfg);
	}

	/**
	 * 针对复杂javabean转化map
	 * 
	 * @param resultMap
	 * @param object
	 * @return
	 * @author houbl
	 * @date 2014年12月10日 上午9:11:32
	 */
	public static Map<String, Object> covertBeanToMap(Map<String, Object> resultMap, Object object) {
		if (object instanceof JSONObject) {
			jsonObjectToMap(resultMap, object);
		} else if (object instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray) object;
			for (Object obj : jsonArray) {
				jsonObjectToMap(resultMap, obj);
			}
		} else {
			JSONArray jsonArray = formatJSONDateList(object);
			covertBeanToMap(resultMap, jsonArray);
		}

		return resultMap;
	};

	public static Map<String, Object> jsonObjectToMap(Map<String, Object> resultMap, Object object) {
		JSONObject jsonObject = (JSONObject) object;
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = jsonObject.get(key);
			if (value instanceof JSONArray || value instanceof JSONObject) {
				covertBeanToMap(resultMap, value);
			}
			if (!(value instanceof JSONArray) && !(value instanceof JSONObject)) {
				if (resultMap.get(key) == null || "".equals(resultMap.get(key).toString())) {
					resultMap.put((String) key, value);
				}
			}
		}
		return resultMap;
	};

	/**
	 * 将一个 JavaBean 对象转化为一个 Map<String,Object>
	 * 
	 * @param resultMap 返回的Map
	 * @param bean 要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 */
	public static Map<String, Object> convertBean(Map<String, Object> resultMap, Object bean) throws Exception {
		Class<? extends Object> type = bean.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					if (result instanceof Date) {
						String dataResult = DateUtil.DateFormatStr((Date) result, DateUtil.PATTERNYYYYMMDD);
						resultMap.put(propertyName, dataResult);
					} else {
						resultMap.put(propertyName, result);
					}
				}
			}
		}
		return resultMap;
	}

	public static Map<String, Object> convertBeanNullToBlank(Map<String, Object> resultMap, Object bean) throws Exception {
		Class<? extends Object> type = bean.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					if (result instanceof Date) {
						String dataResult = DateUtil.DateFormatStr((Date) result, DateUtil.PATTERN_UNIT_YYYYMMDD);
						resultMap.put(propertyName, dataResult);
					} else {
						resultMap.put(propertyName, result);
					}
				} else {
					resultMap.put(propertyName, "");
				}
			}
		}
		return resultMap;
	}

	/**
	 * json转换为java对象
	 * 
	 * <pre>
	 * return JackJson.fromJsonToObject(this.answersJson, JackJson.class);
	 * </pre>
	 * 
	 * @param <T> 要转换的对象
	 * @param json 字符串
	 * @param valueType 对象的class
	 * @return 返回对象
	 */
	public static <T> T fromJsonToObject(String json, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(fmt);
			return mapper.readValue(json, valueType);
		}
		catch (JsonParseException e) {
			logger.error("JsonParseException: ", e);
		}
		catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		}
		catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	public static void getQueryJSONValue(JSONObject rtnJson, List<?> list) {
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_CODE, CommonConstants.AjaxStatus.STATUS_SUCCESS);
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_INFO, "SUCCESS");
		if (list != null) {
			JSONArray jsonArray = JSONArray.fromObject(list);
			rtnJson.put("total", list.size());
			rtnJson.put("rows", jsonArray);
		}
	}

	public static void getQueryJSONValue(JSONObject rtnJson, PageList<?> list) {
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_CODE, CommonConstants.AjaxStatus.STATUS_SUCCESS);
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_INFO, "SUCCESS");
		if (list != null) {
			JSONArray jsonArray = JSONArray.fromObject(list);
			rtnJson.put("total", list.getPaginator().getTotalCount());
			rtnJson.put("rows", jsonArray);
		}
	}

	public static void getEditJSONvalue(JSONObject rtnJson) {
		getEditJSONvalue(rtnJson, null);
	}

	public static void getEditJSONvalue(JSONObject rtnJson, String info) {
		if (StringUtil.isBlank(info)) {
			info = "SUCCESS";
		}
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_CODE, CommonConstants.AjaxStatus.STATUS_SUCCESS);
		rtnJson.put(CommonConstants.AjaxReturn.STATUS_INFO, info);
	}
}

class JSONStringObject implements JSONString {

	private String jsonString = null;

	public JSONStringObject(String jsonString) {
		this.jsonString = jsonString;
	}

	@Override
	public String toString() {
		return jsonString;
	}

	public String toJSONString() {
		return jsonString;
	}
}
