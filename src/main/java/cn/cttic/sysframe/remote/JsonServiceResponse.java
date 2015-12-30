package cn.cttic.sysframe.remote;

import java.text.SimpleDateFormat;
import java.util.Map;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;
import cn.cttic.sysframe.common.util.DateUtil;
import cn.cttic.sysframe.frame.support.mybatis.paginator.jackson2.PageListJsonDeserializer;
import cn.cttic.sysframe.frame.support.mybatis.paginator.jackson2.PageListJsonMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;


public class JsonServiceResponse extends AbstractServiceResponse {

	@Override
	public String serviceCodeNodeName() {
		return "serviceCode";
	}

	@Override
	public String statusCodeNodeName() {
		return "statusCode";
	}

	@Override
	public String statusDescNodeName() {
		return "statusDesc";
	}

	@Override
	public String resultNodeName() {
		return "items";
	}
	
	/**
	 * 异常详细信息节点名称
	 *
	 * @return 
	 * @author caohui
	 * @date Aug 13, 2014 5:49:15 PM
	 */
	public String exceptionNodeName() {
		return "exceptionDesc";
	}
	/**
	 * 设置异常详细信息
	 *
	 * @param exceptionDesc 
	 * @author caohui
	 * @date Aug 13, 2014 5:49:50 PM
	 */
	public void setExceptionDesc(String exceptionDesc) {
		this.appendNode(this.exceptionNodeName(), exceptionDesc);
	}

	@Override
	public String generateMessage() {
		Map<String, Object> message = Maps.newLinkedHashMap();
		if (this.getServiceCode() != null) message.put(this.serviceCodeNodeName(), this.getServiceCode());
		message.put(this.statusCodeNodeName(), this.getStatusCode());
		message.put(this.statusDescNodeName(), this.getStatusDesc());
		
		//设置附加节点
		for (Map.Entry<String, String> item : this.getAdditionalNodes().entrySet()) {
			message.put(item.getKey(), item.getValue());
        }
		
		if (this.getResult() != null) message.put(this.resultNodeName(), this.getResult());
		
		PageListJsonMapper om = new PageListJsonMapper(new SimpleDateFormat(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
		try {
	        return om.writeValueAsString(message);
        }
        catch (JsonProcessingException e) {
	        throw new SystemException(e, StatusCodeForFrameCore.JSON_GENERATE_ERROR);
        }
	}

	@Override
    public <R> R generateReturnObject(String message, ServiceDefinition service) {
		ObjectMapper om = new ObjectMapper();
		
		try {
			JsonNode root = om.readTree(message);
			
			JsonNode statusCode = root.get(this.statusCodeNodeName());
			JsonNode statusDesc = root.get(this.statusDescNodeName());
			// TODO 记录日志
			if (!statusCode.asText().endsWith("0000")){
				throw new SystemException(StatusCodeForFrameCore.REMOTE_CALL_ERROR, statusDesc.asText(), statusCode.asText());
			}
			
			return this.<R>generateReturnObjectWithoutStatus(message, service);
			
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.JSON_PARSE_ERROR, message, service.getReturnType().getName());
		}
    }
	
	private boolean checkResultNodeIsNull(JsonNode root) {
		return root.get(this.resultNodeName()) == null || root.get(this.resultNodeName()).isNull();
	}
	
	protected <R> R generateReturnObjectWithoutStatus(String message, ServiceDefinition service) {
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		
		try {
			JsonNode root = om.readTree(message);
			
			if (this.checkResultNodeIsNull(root)) {
				return null;
			}
			
			if (service.isReturnCollection() && service.getReturnCollectionType().equals(PageList.class)) {
				
				SimpleModule pageListJSONModule = new SimpleModule("PageListJSONModule", new Version(1, 0, 0, null, null, null));
				PageListJsonDeserializer jsonDeserializer =  new PageListJsonDeserializer();
				jsonDeserializer.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
				jsonDeserializer.setMemberType(service.getReturnType());
				pageListJSONModule.addDeserializer(PageList.class, jsonDeserializer);
				om.registerModule(pageListJSONModule);

				@SuppressWarnings("unchecked")
				R returnType = (R) om.readValue(root.get(this.resultNodeName()).toString(), PageList.class);
				return returnType;
			} else if (service.isReturnCollection()) {
				JavaType type = om.getTypeFactory().constructParametricType(service.getReturnCollectionType(), service.getReturnType());
				@SuppressWarnings("unchecked")
                R returnType = (R) om.readValue(root.get(this.resultNodeName()).toString(), type);
				return returnType;
			} else {
				@SuppressWarnings("unchecked")
				R returnType = (R) om.readValue(root.get(this.resultNodeName()).toString(), service.getReturnType());
				return returnType;
			}
			
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.JSON_PARSE_ERROR, message, service.getReturnType().getName());
		}
    }

}
