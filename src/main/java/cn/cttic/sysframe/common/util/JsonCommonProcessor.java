package cn.cttic.sysframe.common.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonCommonProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		if (null != value) {
				return value.toString();
		}
		return "";
	}

}
