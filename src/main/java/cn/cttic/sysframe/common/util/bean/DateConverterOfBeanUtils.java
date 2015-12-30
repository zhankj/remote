package cn.cttic.sysframe.common.util.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;
import cn.cttic.sysframe.common.util.DateUtil;

import org.apache.commons.beanutils.converters.StringConverter;

/**
 * java.util.Date的Converter实现类
 * 用于BeanUtils复制bean时完成String到java.util.Date的转换
 * @author caohui
 *
 */
public class DateConverterOfBeanUtils implements Converter {
	
	private static DateConverterOfBeanUtils instance = new DateConverterOfBeanUtils();
	
	private DateConverterOfBeanUtils() {}
	
	public static DateConverterOfBeanUtils getInstance() { return instance; }

	@Override
	public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
		if (value == null) return value;
		
		if (type.equals(java.util.Date.class)) {
			if (value instanceof java.util.Date) return value;
			if (value instanceof java.lang.String) {
				String dateStr = (String) value;
				if (dateStr.trim().length() == 0)  return null;
				try {
					return new SimpleDateFormat(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS).parse(dateStr);
				} catch (ParseException e) {
					throw new SystemException(e, StatusCodeForFrameCore.TYPE_CAST_ERROR);
				}
			}
		}else if(type.equals(java.lang.String.class)){
			if (value instanceof java.util.Date){
				java.util.Date data = (java.util.Date)value;
				return DateUtil.format(data, null);
			}else{
				return StringConverter.convert(type, value);
				
			}
			
		}
		
		return value;
	}
	
	private StringConverter StringConverter = new StringConverter();

}
