package cn.cttic.sysframe.common.exception.code;

/**
 * 模块（子系统）编码枚举接口
 * @author caohui
 *
 */
public interface ModuleCode {
	/**
	 * 返回2位的模块（子系统）编码，如"00", "01"
	 * @return
	 */
	String getCode();
	/**
	 * 返回模块（子系统）名称，如"资源管理模块'，"业务受理模块"
	 * @return
	 */
	String getModuleName();
	/**
	 * 返回所属的系统枚举
	 * @return
	 */
	SystemCode getSystemCode();
}
