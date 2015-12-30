package cn.cttic.sysframe.frame.support;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RestWebServiceSupport {

	public static enum NodeName {
		serviceCode(true, false), locale(false, true), channel(true, true), sid(false, true), secretKey(true, true), statusCode(true, false), statusDesc(false, false), exceptionDesc(false, false);
		
		public final boolean required;
		public final boolean additional;

		private NodeName(boolean required, boolean additional) {
	        this.required = required;
	        this.additional = additional;
        }
	}
	
	/**
	 * 从JSON报文中解析locale语言区域属性
	 *
	 * @param json
	 * @return 
	 * @author caohui
	 * @date Aug 11, 2014 1:48:58 PM
	 */
	public static String parseNode(String json, NodeName nodename) {
		try {
			ObjectMapper om = new ObjectMapper();
			JsonNode root = om.readTree(json);
			JsonNode codeNode = nodename.additional ? root.path("additional").path(nodename.name()) : root.path(nodename.name());
			if (codeNode.isMissingNode() && nodename.required) {
				throw new SystemException(StatusCodeForFrame.REQUEST_PARAMETER_IS_NULL, nodename.name());
			}
			else if (codeNode.isMissingNode() && !nodename.required) {
				return null;
			}
			else {
				return codeNode.asText();
			}
		}
		catch (Exception e) {
			throw new SystemException(e, StatusCodeForFrameCore.JSON_PARSE_ERROR);
		}
	}
}
