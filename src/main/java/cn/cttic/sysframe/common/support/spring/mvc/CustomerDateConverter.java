package cn.cttic.sysframe.common.support.spring.mvc;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import com.google.common.collect.Maps;
 
public class CustomerDateConverter implements Converter<String, Date> {
	final static private Logger LOG = LoggerFactory.getLogger(CustomerDateConverter.class);
	
	private static final Map<Integer, SimpleDateFormat> formatMaps = Maps.newLinkedHashMap();
	
	static {
		//首次解析失败后再次匹配顺序如下
		formatMaps.put(18, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//长度不包含空格
		formatMaps.put(10, new SimpleDateFormat("yyyy-MM-dd"));
		formatMaps.put(7, new SimpleDateFormat("yyyy-MM"));
		formatMaps.put(4, new SimpleDateFormat("yyyy"));
		for(SimpleDateFormat format : formatMaps.values()) {
			format.setLenient(false);
		}
	}
	
	@Override
	public Date convert(String source) {
		final int strLength = source.trim().replaceAll(" ", "").length();
		Map<Integer, SimpleDateFormat> formats = Maps.newLinkedHashMap(formatMaps);
		
		if(formats.containsKey(strLength)) {
			
		}
		
		SimpleDateFormat dateFormat = formats.get(strLength);
		try {
			return dateFormat.parse(source);
		} catch (Exception e) {
			if (dateFormat != null ) {
				logDateParseError(source, dateFormat);
				formats.remove(strLength);
			}
			//尝试应用其他日期格式进行转换
			for(SimpleDateFormat format : formats.values()) {
				try {
	                return format.parse(source);
                }
                catch (Exception e1) {
                	logDateParseError(source, format);
	                continue;
                }
			}
		}		
		return null;
	}
	
	private static void logDateParseError(String source, SimpleDateFormat dateFormat) {
		LOG.warn(new StringBuilder("字符串 ").append(source).append(" 转换成 ").append(dateFormat.toPattern()).append(" 日期格式失败").toString());
	}
}