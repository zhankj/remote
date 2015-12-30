package cn.cttic.sysframe.frame.support.mybatis.paginator.jackson2;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@SuppressWarnings("rawtypes")
public class PageListJsonSerializer extends JsonSerializer<PageList> {
    @SuppressWarnings("unchecked")
	@Override
    public void serialize(PageList value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Map<String,Object> map = new HashMap<String, Object>();
        if (value.getPaginator() != null) {
        	map.put("total", value.getPaginator().getTotalCount());//总记录数
        	map.put("totalPages", value.getPaginator().getTotalPages());//总页数
        	map.put("pageNumber", value.getPaginator().getPage());//当前页数
        	map.put("pageSize", value.getPaginator().getLimit());//每页显示记录数
        }
        else if (value.size() == 0){
        	map.put("total", 0);//总记录数
        	map.put("totalPages", 0);//总页数
        	map.put("pageNumber", 1);//当前页数
        	map.put("pageSize", 0);//每页显示记录数
        }
        map.put("rows" , new ArrayList(value));//当前页的记录结果集
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        if (this.getDateFormat() != null) mapper.setDateFormat(this.getDateFormat());
        mapper.writeValue(jgen, map);
    }
    
    private DateFormat dateFormat;
    
    public PageListJsonSerializer setDateFormat(DateFormat dateFormat) {
    	this.dateFormat = dateFormat;
    	return this;
    }
    
    public DateFormat getDateFormat() {
    	return this.dateFormat;
    }
}
