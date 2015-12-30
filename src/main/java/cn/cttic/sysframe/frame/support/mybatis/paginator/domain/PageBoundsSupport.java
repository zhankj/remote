package cn.cttic.sysframe.frame.support.mybatis.paginator.domain;

import java.util.List;

import cn.cttic.sysframe.common.model.DataGridModel;
import cn.cttic.sysframe.common.util.StringUtil;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


public class PageBoundsSupport {
	
	/**
	 * 构造分页查询对象 PageBounds
	 * 
	 * @param page 请求的当前页数
	 * @param rows 每页的记录数
	 * @param sort 排序字段
	 * @param order 排序规则 asc OR desc
	 * @return
	 */
	public static PageBounds newPageBounds(int page, int rows, String sort, String order) {
		boolean orderBy = (sort != null && order != null);
		List<Order> orders = Order.formString( orderBy ? sort+"."+order : null);
		for (Order item : orders) {
			item.setProperty((item.getProperty() != null && item.getProperty().indexOf("_") != -1 ) ? item.getProperty() : StringUtil.toUnderScoreCase(item.getProperty()));
		}
		
		return new PageBounds(page, rows, orders);
	}
	
	public static PageBounds newPageBounds(DataGridModel model) {
		return newPageBounds(model.getPage(), model.getRows(), model.getSort(), model.getOrder());
	}

}
