package cn.cttic.sysframe.frame.support.mybatis.paginator.jackson2;

import java.text.DateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


public class PageListJsonMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;
	private final Logger LOG = LoggerFactory.getLogger(PageListJsonMapper.class);

	public PageListJsonMapper(DateFormat dateFormat) {
		super();
		
		//指定日期的json的转换格式
		super.setDateFormat(dateFormat);
		super.setSerializationInclusion(Include.NON_NULL);
		
		//PageList到json的序列化支持指定的日期格式
		SimpleModule pageListJSONModule = new SimpleModule("PageListJSONModule", new Version(1, 0, 0, null, null, null));
//		PageListJsonSerializer seri  = new PageListJsonSerializer().setDateFormat(dateFormat);
        pageListJSONModule.addSerializer(PageList.class, new PageListJsonSerializer().setDateFormat(dateFormat));
        registerModule(pageListJSONModule);
	}
	
}
