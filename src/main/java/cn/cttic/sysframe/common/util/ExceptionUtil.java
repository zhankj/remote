package cn.cttic.sysframe.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cttic.sysframe.common.constants.CommonConstants;
import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCode;

public class ExceptionUtil {
	private final static Logger LOG = LoggerFactory.getLogger(ExceptionUtil.class);

	public static JSONObject toJSON(String message, Exception exception, HttpServletRequest request) {
		return toJSON(message, exception);
	}

	public static JSONObject toJSON(String message, Exception ex) {
		SystemException se = SystemException.wrapException(ex);
		LOG.debug("error to json =>",se);

		String excepPrintex = getTrace(ex);
		JSONObject json = new JSONObject();
		json.put(CommonConstants.AjaxReturn.STATUS_CODE, CommonConstants.AjaxStatus.STATUS_FAILURE);
		json.put(CommonConstants.AjaxReturn.STATUS_INFO, se.toModel());
		json.put("EXCEP_PRINTEX", excepPrintex);
		
		//8位异常编码
		StatusCode statusCode = se.getStatusCode();
		StringBuilder code = new StringBuilder()
							.append(statusCode.getModuleCode().getSystemCode().getCode())
							.append(statusCode.getModuleCode().getCode())
							.append(statusCode.getCode());
		//异常描述
		StringBuilder desc = new StringBuilder()
							.append(statusCode.getModuleCode().getSystemCode().getSystemName())
							.append(statusCode.getModuleCode().getModuleName())
							.append("出现异常：");
		
		desc.append(statusCode.getDescription());
		
		json.put("__model", "error");
		json.put("code", code.toString());
		json.put("desc", desc.toString());
		json.put("type", statusCode.getType());

		return json;
	}

	public static JSONObject toJSON(Exception ex) {
		SystemException se = SystemException.wrapException(ex);
		LOG.debug("error to json =>",se);
		
		String excepPrintex = getTrace(ex);
		JSONObject json = new JSONObject();
		json.put(CommonConstants.AjaxReturn.STATUS_CODE, CommonConstants.AjaxStatus.STATUS_FAILURE);
		json.put(CommonConstants.AjaxReturn.STATUS_INFO, se.toModel());
		json.put("EXCEP_PRINTEX", excepPrintex);
		
		//8位异常编码
		StatusCode statusCode = se.getStatusCode();
		StringBuilder code = new StringBuilder()
							.append(statusCode.getModuleCode().getSystemCode().getCode())
							.append(statusCode.getModuleCode().getCode())
							.append(statusCode.getCode());
		//异常描述
		StringBuilder desc = new StringBuilder()
							.append(statusCode.getModuleCode().getSystemCode().getSystemName())
							.append(statusCode.getModuleCode().getModuleName())
							.append("出现异常：");
		
		desc.append(statusCode.getDescription());
		
		json.put("__model", "error");
		json.put("code", code.toString());
		json.put("desc", desc.toString());
		json.put("type", statusCode.getType());

		return json;
	}

	public static String toString(Exception ex) {
		String retMsg = ex.getCause() == null ? ex.getMessage() : ex.getCause().getMessage();
		return retMsg;
	}

	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		try {
			t.printStackTrace(printWriter);
			return stringWriter.toString();
		}
		finally {
			printWriter.close();
		}
	}
}
