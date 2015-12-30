package cn.cttic.sysframe.common.exception.code;

/**
 * 系统标识枚举接口
 * @author caohui
 *
 */
public interface SystemCode {
	/**
	 * 返回2位的系统标识编码，如"00", "01"
	 * @return
	 */
	String getCode();
	/**
	 * 返回系统名称，如"CRM系统", "BILLING系统"
	 * @return
	 */
	String getSystemName();
}
