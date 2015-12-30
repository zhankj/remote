package cn.cttic.sysframe.common.exception.code;

/**
 * 系统编码枚举类
 * 
 * @author caohui
 *
 */
public enum SystemCodeForFrame implements SystemCode {
	FRAME("00", "系统框架");

	private String code;
	private String description;

	private SystemCodeForFrame(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getSystemName() {
		return description;
	}

}
