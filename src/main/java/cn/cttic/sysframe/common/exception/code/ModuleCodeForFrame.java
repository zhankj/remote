package cn.cttic.sysframe.common.exception.code;

/**
 * Frame模块编码枚举类
 * 
 * @author caohui
 *
 */
public enum ModuleCodeForFrame implements ModuleCode  {
	UNKNOWN("00","未知模块"),
	SYSTEM_MANAGEMENT("01", "系统管理模块"),
	ASKING_MANAGEMENT("02", "申报管理模块"),
	APPROVAL_MANAGEMENT("03", "审批管理模块");

	private String code;
	private String moduleName;

	private ModuleCodeForFrame(String code, String moduleName) {
		this.code = code;
		this.moduleName = moduleName;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getModuleName() {
		return moduleName;
	}

	@Override
	public SystemCode getSystemCode() {
		return SystemCodeForFrame.FRAME;
	}

}
