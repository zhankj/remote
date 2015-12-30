package cn.cttic.sysframe.common.support;

import cn.cttic.sysframe.common.exception.code.StatusCode;


public class StatusCodeSupport {
	/**
	 * 返回完整8位异常编码
	 *
	 * @param statusCode
	 * @return 
	 * @author caohui
	 * @date Aug 11, 2014 6:48:28 PM
	 */
	public static String getFullStatusCode(StatusCode statusCode){
		StringBuilder code = new StringBuilder();
		
		if("0000".equals(statusCode.getCode())) code.append("0000");
		else code.append(statusCode.getModuleCode().getSystemCode().getCode()).append(statusCode.getModuleCode().getCode());
		
		code.append(statusCode.getCode());
		
		return code.toString();
	}
}
