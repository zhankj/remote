package cn.cttic.sysframe.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取页面传送参数
 */
public class HttpUtil {

	public static String getToString(HttpServletRequest request, String code) {
//		request.setCharacterEncoding("UTF-8");
		return request.getParameter(code) == null ? "" : request.getParameter(code).toString().trim();
	}
}
