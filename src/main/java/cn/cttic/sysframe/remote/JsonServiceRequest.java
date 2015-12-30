package cn.cttic.sysframe.remote;

import java.text.SimpleDateFormat;
import java.util.Map;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;
import cn.cttic.sysframe.common.util.DateUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;


public class JsonServiceRequest extends AbstractServiceRequest {

	public JsonServiceRequest() {
		super();
    }
	
	public JsonServiceRequest(String message) {
	    super(message);
    }

	@Override
	public String serviceCodeNodeName() {
		return "serviceCode";
	}

	@Override
	public String paramsNodeName() {
		return "params";
	}

	@Override
	public Object[] parseParams(Class<?>... paramsType) {
		try {
			ObjectMapper om = new ObjectMapper();
			om.setDateFormat(new SimpleDateFormat(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			
			JsonNode root = om.readTree(this.getMessage());
			JsonNode codeNode = root.get(this.serviceCodeNodeName());
			String code = codeNode.asText();
			this.setServiceCode(code);
			
			JsonNode paramsNode = root.get(this.paramsNodeName());
			
			int paramsAmount = 0;
			if (paramsNode == null || paramsNode.isNull()) paramsAmount = 0;
			else if (!paramsNode.isArray()) paramsAmount = 1;
			else if (paramsNode.isArray()) paramsAmount = paramsNode.size();
			if (paramsAmount != paramsType.length) 
				throw new SystemException(StatusCodeForFrameCore.REMOTE_CALL_PARAMS_AMOUNT_ERROR, paramsType.length, paramsAmount);
			
			Object[] params = new Object[paramsType.length];
			for (int i = 0; i < paramsType.length; i++) {
				JsonNode theNode = !paramsNode.isArray() ? paramsNode : paramsNode.get(i);
				params[i] = om.readValue(theNode.toString(), paramsType[i]);
			}
			return params;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.JSON_PARSE_ERROR);
		}
	}

	@Override
	public String parseCode() {
		try {
			ObjectMapper om = new ObjectMapper();
			JsonNode root = om.readTree(this.getMessage());
			JsonNode queryCodeNode = root.path(this.serviceCodeNodeName());
			String code = queryCodeNode.asText();
			if (code == null || "".equals(code)) throw new SystemException(StatusCodeForFrameCore.MESSAGE_PARSE_KEY_ERROR, this.serviceCodeNodeName());
			return code;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.JSON_PARSE_ERROR);
		}
	}

	@Override
    public String generateMessage(ServiceDefinition service, Object ... params) {
//		this.setServiceDefinition(service);
//		this.setParams(params);
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> message = Maps.newLinkedHashMap();
		//设置附加节点
		message.put(this.additionalNodeName(), this.getAdditionalNodes());

		message.put(this.serviceCodeNodeName(), service.getServiceCode());
		message.put(this.paramsNodeName(), params);
		
		try {
			om.setDateFormat(new SimpleDateFormat(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			return om.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			throw new SystemException(StatusCodeForFrameCore.OBJECT_TO_JSON_ERROR, this);
		}
    }

}
