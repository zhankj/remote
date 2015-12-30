package cn.cttic.sysframe.remote;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class StatusJsonServiceResponse extends JsonServiceResponse {

	public static class ServiceResponseModel {
		private String statusCode;
		private String statusDesc;
		private Object items;
		private String exceptionDesc;
		
		public String getStatusCode() {
			return statusCode;
		}
		
		public String getStatusDesc() {
			return statusDesc;
		}
		
		@SuppressWarnings("unchecked")
		public <R> R getItems() {
			return (R)items;
		}
		
        public String getExceptionDesc() {
        	return exceptionDesc;
        }
		
	}

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

	@SuppressWarnings("unchecked")
    @Override
    public ServiceResponseModel generateReturnObject(String message, ServiceDefinition service) {
		ObjectMapper om = new ObjectMapper();
		
		try {
			JsonNode root = om.readTree(message);
			JsonNode statusCode = root.get(this.statusCodeNodeName());
			JsonNode statusDesc = root.get(this.statusDescNodeName());
			JsonNode exceptionDesc = root.get(this.exceptionNodeName());
			
			ServiceResponseModel model = new ServiceResponseModel();
			model.statusCode = statusCode.asText();
			model.statusDesc = statusDesc.asText();
			model.items = super.generateReturnObjectWithoutStatus(message, service);
			if (exceptionDesc != null) model.exceptionDesc = exceptionDesc.asText();

			return model;
			
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.JSON_PARSE_ERROR, message, service.getReturnType().getName());
		}
    }
	
}
