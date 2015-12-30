package cn.cttic.sysframe.common.model;

import cn.cttic.sysframe.common.exception.code.StatusCode;

/**
 * 异常 model
 * 用于向前端页面展示错误信息
 * 
 * @author caohui
 *
 */
public class ExceptionModel {
	private final String __model = "error";//仅用于前端JS识别model类型
	public String get__model() {
		return __model;
	}

	private String code;//8位异常编码
	private String desc;//异常编码描述
	private StatusCode.CodeType type;//异常类型：业务异常 或 系统异常
	private String message;//（抛出时）自定义的异常描述

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public StatusCode.CodeType getType() {
		return type;
	}

	public void setType(StatusCode.CodeType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
