package cn.cttic.sysframe.common.util;

import java.util.UUID;

/**
 * UUID生成类. <br>
 * 类详细说明.
 * @author zhangzechen@cttic.cn
 * @date 2015年6月30日 下午1:08:37 <br>
 * Copyright: Copyright (c) 2015 CTTIC
 */
public class UUIDGenerator {
	public static final String ORI = "ORI";
	public static final String SIMPLE = "SIMPLE";
	
	// 系统生成UUID
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
    
    // 格式化UUID
	public static String getUUIDSimple() {
		String str = UUID.randomUUID().toString();
		// 去掉"-"符号
		return  str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
	}
	
	// 获取指定数目的UUID
	public static String[] getUUID(int number) {
		return getUUID(number, ORI);
	}
	
	// 获取指定数目的UUID
	public static String[] getUUID(int number, String type) {
		if (number < 1) {
			return null;
		}

		String[] ss = new String[number];
		for(int i=0; i<number; i++){
			if (null != type && SIMPLE.equals(type)) {
				ss[i] = getUUIDSimple();
			} else {
				ss[i] = getUUID();
			}
		}

		return ss;
	}
}
