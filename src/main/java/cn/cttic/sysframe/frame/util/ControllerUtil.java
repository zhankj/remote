package cn.cttic.sysframe.frame.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.cttic.sysframe.common.model.DataGridModel;
import cn.cttic.sysframe.common.util.CamelCaseUtil;
import cn.cttic.sysframe.common.util.StringUtil;

public class ControllerUtil {

	public static Map<String, Object> parseReqForMap(HttpServletRequest request) {
		Enumeration<?> names = request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		while (names.hasMoreElements()) {
			String key = names.nextElement().toString();
			Object value = request.getParameter(key);
			if (!StringUtil.isEmpty(value)) {
				if (key.endsWith("$LIKE")) {
					map.put(key.substring(0, key.length() - 5), "%" + value + "%");
				} else {
					map.put(key, value);
				}
			}
		}
		return map;
	}

	public static PageBounds initPageBounds(DataGridModel model, HttpServletRequest request, String methodName) {
		int pageNumber = model.getPage();
		int pageSize = model.getRows();
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(pageSize);
		pageBounds.setPage(pageNumber);
		if (!StringUtil.isBlank(model.getSort())) {
			// 将前台传入的驼峰型排序字段名转换为数据库对应的字段
			String orderColumn = CamelCaseUtil.toUnderlineName(request, model.getSort(), methodName, CamelCaseUtil.SQL_SESSION_FACTORY);
			Order order = new Order(orderColumn, Order.Direction.fromString(model.getOrder()), null);
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(order);
			pageBounds.setOrders(orderList);
		}
		return pageBounds;
	}

	/**
	 * 获取客户端真实IP
	 * 
	 * @param request
	 * @return
	 * @author yangrui
	 * @date 2014-6-18 下午1:47:02
	 */
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	/**
	 * 获取浏览器类型
	 *
	 * @param request
	 * @return 
	 * @author yangrui
	 * @date 2014-6-18 下午2:23:04
	 */
	public static String getBrowserType(HttpServletRequest request) {
		String result = request.getHeader("user-agent");
		return result;
    }

}
