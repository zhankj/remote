package cn.cttic.sysframe.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.cttic.sysframe.common.model.DataGridModel;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public class CamelCaseUtil {

	private static final char SEPARATOR = '_';

	// CRM数据源
	public static final String SQL_SESSION_FACTORY = "sqlSessionFactory";

	public static String toUnderlineName(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			boolean nextUpperCase = true;
			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}
			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}

	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/*
	 * 将前台传入的驼峰型排序字段名转换为数据库对应的字段 转换原理：使用mapper配置文件中的resultMap配置，根据property找到column
	 */
	public static String toUnderlineName(String sort, String mappedStatement, String sqlSessionFactoryName) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return toUnderlineName(request, sort, mappedStatement, sqlSessionFactoryName);
	}

	/*
	 * 将前台传入的驼峰型排序字段名转换为数据库对应的字段 转换原理：使用mapper配置文件中的resultMap配置，根据property找到column
	 */
	public static String toUnderlineName(HttpServletRequest request, String sort, String mappedStatement, String sqlSessionFactoryName) {
		String orderColumn = "";
		if (StringUtil.isBlank(sort)) {
			return orderColumn = "";
		}
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ctx.getBean(sqlSessionFactoryName);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Configuration configuration = session.getConfiguration();
			MappedStatement ms = configuration.getMappedStatement(mappedStatement);
			List<ResultMap> resultMapList = ms.getResultMaps();
			for (ResultMap resultMap : resultMapList) {
				List<ResultMapping> resultMappingList = resultMap.getResultMappings();
				for (ResultMapping resultMapping : resultMappingList) {
					if (sort.equals(resultMapping.getProperty())) {
						orderColumn = resultMapping.getColumn();
					}
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
		return orderColumn;
	}

	public static void main(String[] args) {
		System.out.println(CamelCaseUtil.toUnderlineName("ISOCertifiedStaff"));
		System.out.println(CamelCaseUtil.toUnderlineName("CertifiedStaff"));
		System.out.println(CamelCaseUtil.toUnderlineName("UserID"));
		System.out.println(CamelCaseUtil.toCamelCase("iso_certified_staff"));
		System.out.println(CamelCaseUtil.toCamelCase("certified_staff"));
		System.out.println(CamelCaseUtil.toCamelCase("user_id"));
	}

	// 设定分页
	public static PageBounds configPageBounds(HttpServletRequest request, PageBounds pageBounds, DataGridModel model, String url,
	        String sqlSessionFactoryName) {

		if (model == null) {
			return pageBounds;
		}
		pageBounds.setLimit(model.getRows());
		pageBounds.setPage(model.getPage());
		if (model.getSort() != null && !model.getSort().equals("")) {
			String orderColumn = CamelCaseUtil.toUnderlineName(request, model.getSort(), url, sqlSessionFactoryName);
			Order order = new Order(orderColumn, Order.Direction.fromString(model.getOrder()), null);
			List<Order> orderList = new ArrayList<Order>();
			orderList.add(order);
			pageBounds.setOrders(orderList);
		}
		return pageBounds;
	}

	public static PageBounds getPageBounds(DataGridModel model, String mappedStatement, String sqlSessionFactory) {
		if (model == null || model.getRows() == 0 && model.getPage() == 0) {
			return new PageBounds();
		}
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(model.getRows());
		pageBounds.setPage(model.getPage());
		// 将前台传入的驼峰型排序字段名转换为数据库对应的字段
		// 改造成数组 add houbl order 可以是一个或者多个
		String sortName = model.getSort();
		String orderName = StringUtils.defaultIfEmpty(model.getOrder(), "ASC");
		if (StringUtils.isNotBlank(sortName)) {
			String[] sorts = sortName.split(",");
			String[] orders = orderName.split(",");
			String orderResult = "";
			boolean flag = true;
			if (orders.length != sorts.length) {
				flag = false;
			}
			List<Order> orderList = new ArrayList<Order>();
			int i = 0;
			for (String sort : sorts) {
				String orderColumn = CamelCaseUtil.toUnderlineName(sort, mappedStatement, sqlSessionFactory);
				if (!StringUtil.isBlank(orderColumn)) {
					if (flag) {
						orderResult = orders[i];
					} else {
						orderResult = orderName;
					}
					Order order = new Order(orderColumn, Order.Direction.fromString(orderResult), null);
					orderList.add(order);
				}
				i++;
			}
			if ((orderList.size() == 0) && (sorts.length > 0)  && (orders.length > 0))
			{
				i = 0;
				for(String sort : sorts)
				{
					if (i < orders.length)
						orderResult = orders[i];
					Order order = new Order(sort, Order.Direction.fromString(orderResult), null);
					orderList.add(order);
					i++;
				}
			}
			pageBounds.setOrders(orderList);
		}
		return pageBounds;
	}

	public static PageBounds getCrmPageBounds(DataGridModel model, String mappedStatement) {
		return getPageBounds(model, mappedStatement, SQL_SESSION_FACTORY);
	}
}
