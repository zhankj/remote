package cn.cttic.sysframe.common.exception;

import java.io.Serializable;
import java.text.MessageFormat;

import cn.cttic.sysframe.common.exception.code.StatusCode;
import cn.cttic.sysframe.common.exception.code.StatusCodeForUnknown;
import cn.cttic.sysframe.common.model.ExceptionModel;
import cn.cttic.sysframe.common.util.StringUtil;

/**
 * 系统异常类
 * <br/> 所有构造方法中都必须要填入 StatusCode 指定异常编码，StatusCode 是一个统一接口，需要各模块自己实现，可以参考 cn.cttic.sysframe.common.exception.code.StatusCodeForFrame
 * <br/> 使用示例：
 * <li/> throw new SystemException(StatusCodeForFrame.INSIDE_ERROR);
 * <li/> throw new SystemException(StatusCodeForFrame.INSIDE_ERROR, descArgs1, descArgs2);
 * <li/> throw new SystemException("非国际化自定义描述", StatusCodeForFrame.INSIDE_ERROR);
 * <li/> throw new SystemException(otherException, StatusCodeForFrame.INSIDE_ERROR, descArgs1);
 * <li/> throw new SystemException(otherException, "非国际化自定义描述", StatusCodeForFrame.INSIDE_ERROR);
 * 
 * @see cn.cttic.sysframe.common.exception.code.StatusCode
 * @author caohui
 *
 */
public class SystemException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Deprecated
//	private ExceptionCode exceptionCode;
//	
//	@Deprecated
//	public SystemException(ExceptionCode exceptionCode) {
//		this.exceptionCode = exceptionCode;
//	}
//	
//	@Deprecated
//	public SystemException(ExceptionCode exceptionCode, Throwable cause) {
//		super(cause);
//		this.exceptionCode = exceptionCode;
//	}
//	
//	@Deprecated
//	public SystemException(ExceptionCode exceptionCode, String message) {
//		super(message);
//		this.exceptionCode = exceptionCode;
//	}
//	
//	@Deprecated
//	public SystemException(ExceptionCode exceptionCode, Throwable cause, String message) {
//		super(message, cause);
//		this.exceptionCode = exceptionCode;
//	}
//	
//	@Deprecated
//	public ExceptionCode getExceptionCode() {
//		return exceptionCode;
//	}
	
	private StatusCode statusCode;
	private Object[] statusCodeArgs;
	
	/**
	 * @param statusCode 不包含描述占位符的 StatusCode
	 */
	public SystemException(StatusCode statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * @param statusCode 包含描述占位符的 StatusCode
	 * @param statusCodeArgs 替代 StatusCode 描述占位符的变长参数
	 */
	public SystemException(StatusCode statusCode, Object ... statusCodeArgs) {
		this.statusCode = statusCode;
		this.statusCodeArgs = statusCodeArgs;
	}
	
	/**
	 * @param cause 实际导致错误的异常
	 * @param statusCode 不包含描述占位符的 StatusCode
	 */
	public SystemException(Throwable cause, StatusCode statusCode) {
		super(cause);
		this.statusCode = statusCode;
	}
	
	/**
	 * @param cause 实际导致错误的异常
	 * @param statusCode 包含描述占位符的 StatusCode
	 * @param statusCodeArgs 替代 StatusCode 描述占位符的变长参数
	 */
	public SystemException(Throwable cause, StatusCode statusCode, Object ... statusCodeArgs) {
		super(cause);
		this.statusCode = statusCode;
		this.statusCodeArgs = statusCodeArgs;
	}
	
	/**
	 * @param message 自定义异常描述消息，仅作日志描述或调试使用，不会被显示在前端页面
	 * @param statusCode 不包含描述占位符的 StatusCode
	 */
	public SystemException(String message, StatusCode statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
	
	/**
	 * @param message 自定义异常描述消息，仅作日志描述或调试使用，不会被显示在前端页面
	 * @param statusCode 包含描述占位符的 StatusCode
	 * @param statusCodeArgs 替代 StatusCode 描述占位符的变长参数
	 */
	public SystemException(String message, StatusCode statusCode, Object ... statusCodeArgs) {
		super(message);
		this.statusCode = statusCode;
		this.statusCodeArgs = statusCodeArgs;
	}
	
	/**
	 * @param cause 实际导致错误的异常
	 * @param message 自定义异常描述消息，仅作日志描述或调试使用，不会被显示在前端页面
	 * @param statusCode 不包含描述占位符的 StatusCode
	 */
	public SystemException(Throwable cause, String message, StatusCode statusCode) {
		super(message, cause);
		this.statusCode = statusCode;
	}
	
	/**
	 * @param cause 实际导致错误的异常
	 * @param message 自定义异常描述消息，仅作日志描述或调试使用，不会被显示在前端页面
	 * @param statusCode 包含描述占位符的 StatusCode
	 * @param statusCodeArgs 替代 StatusCode 描述占位符的变长参数
	 */
	public SystemException(Throwable cause, String message, StatusCode statusCode, Object ... statusCodeArgs) {
		super(message, cause);
		this.statusCode = statusCode;
		this.statusCodeArgs = statusCodeArgs;
	}
	
	
	public StatusCode getStatusCode() {
		return statusCode;
	}
	
    public Object[] getStatusCodeArgs() {
    	return statusCodeArgs;
    }
    
    public void setStatusCodeArgs(Object[] statusCodeArgs) {
    	this.statusCodeArgs = statusCodeArgs;
    }

	public boolean hasMessageArguments() {
		return this.statusCodeArgs != null && this.statusCodeArgs.length != 0;
	}

	public ExceptionModel toModel() {
		//8位异常编码
		StringBuilder code = new StringBuilder()
							.append(this.statusCode.getModuleCode().getSystemCode().getCode())
							.append(this.statusCode.getModuleCode().getCode())
							.append(this.statusCode.getCode());
		//异常描述
		StringBuilder desc = new StringBuilder();
//							.append(this.statusCode.getModuleCode().getSystemCode().getSystemName())
//							.append(this.statusCode.getModuleCode().getModuleName())
//							.append("出现异常：");
		
		if (this.hasMessageArguments()) {//拼装异常描述中的占位符
			desc.append(MessageFormat.format(this.statusCode.getDescription(), this.statusCodeArgs));
		} else {
			desc.append(this.statusCode.getDescription());
		}
		
		ExceptionModel model = new ExceptionModel();
		model.setCode(code.toString());
		model.setDesc(desc.toString());
		model.setType(this.statusCode.getType());

		if (!StringUtil.isBlank(this.getMessage()))
			model.setMessage(this.getMessage());

		return model;
	}

	/**
	 * 包装未知的系统异常
	 * @param cause
	 * @return
	 */
	public static SystemException wrapException(Throwable cause) {
		SystemException se = null;
		if (cause instanceof SystemException) {
			se = (SystemException) cause;
		} else {
			se = new SystemException(cause, StatusCodeForUnknown.INSIDE_ERROR);
		}
		return se;
	}
	
	/**
	 * 遍历异常堆栈信息，以单行String形式返回
	 * @return
	 */
	public String getTraceMessage() {
		StringBuilder messages = new StringBuilder();
		Throwable cause = this;
		
		int i = 1;
		while (cause != null) {
			if (cause.getMessage() != null && !"".equals(cause.getMessage().trim())) {
				messages.append(i++).append("#[ ").append(cause.getMessage()).append(".] ");
			}
			cause = cause.getCause();
		}
		
		return "".equals(messages.toString().trim()) ? "" : messages.toString();
	}
	
	@Override
    public String toString() {
		StringBuilder string = new StringBuilder(getClass().getName()).append(" [")
		.append(this.toModel().getCode()).append("] ")
		.append(this.toModel().getDesc());
		
		if(this.getMessage() != null)
			string.append(" => ").append(this.getMessage());
        
        return string.toString();
    }
	
    public String toErrorString() {
		StringBuilder string = new StringBuilder("[")
		.append(this.toModel().getCode()).append("] ")
		.append(this.toModel().getDesc());
		
		if(this.getMessage() != null)
			string.append(" => ").append(this.getMessage());
        
        return string.toString();
    }

}
