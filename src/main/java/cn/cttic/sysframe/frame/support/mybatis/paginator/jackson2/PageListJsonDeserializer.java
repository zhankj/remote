package cn.cttic.sysframe.frame.support.mybatis.paginator.jackson2;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

/**
 * 
 * @author caohui
 * 
 */
public class PageListJsonDeserializer extends JsonDeserializer<PageList<?>> {

	@Override
	public PageList<?> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);

		ObjectMapper om = new ObjectMapper().setDateFormat(this.getDateFormat());
		JavaType type = om.getTypeFactory().constructParametricType(ArrayList.class, this.getMemberType());
		@SuppressWarnings("rawtypes")
		List rows = om.readValue(node.get("rows").toString(), type);

		if (node.get("pageNumber") != null && node.get("pageSize") != null && node.get("total") != null) {
			int page = node.get("pageNumber").asInt();
			int limit = node.get("pageSize").asInt();
			int totalCount = node.get("total").asInt();
			Paginator paginator = new Paginator(page, limit, totalCount);
			@SuppressWarnings({ "rawtypes", "unchecked" })
			PageList<?> list = new PageList(rows, paginator);
			return list;
		} else {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			PageList<?> list = new PageList(rows);
			return list;
		}

	}

	private Class<?> memberType;

	public Class<?> getMemberType() {
		return memberType;
	}

	public void setMemberType(Class<?> memberType) {
		this.memberType = memberType;
	}

	private DateFormat dateFormat;

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

}
