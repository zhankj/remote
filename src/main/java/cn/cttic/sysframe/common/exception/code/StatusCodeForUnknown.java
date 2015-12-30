package cn.cttic.sysframe.common.exception.code;

/**
 * 未知的模块状态编码枚举类
 * 
 * @author caohui
 *
 */
public enum StatusCodeForUnknown implements StatusCode {
	INSIDE_ERROR("0001", CodeType.SYSTEM, "系统内部异常");
	
	private String code;
	private CodeType type;
	private String description;
	
	private StatusCodeForUnknown(String code, CodeType type, String description) {
		this.code = code;
		this.type = type;
		this.description = description;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public CodeType getType() {
		return type;
	}

	@Override
	public ModuleCode getModuleCode() {
		return ModuleCodeForFrameCore.UNKNOWN;
	}

}
