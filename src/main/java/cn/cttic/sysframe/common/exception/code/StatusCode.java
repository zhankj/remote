package cn.cttic.sysframe.common.exception.code;

/**
 * 异常状态编码枚举统一接口
 * <br/> 
 * <br/> 每个模块都需要实现各自的异常编码枚举，不推荐跨模块使用
 * <br/> 每个 StatusCode 实例都要求可以返回一个 ModuleCode 实例，指明抛出异常的模块（子系统）
 * <br/> 每个 ModuleCode 实例也要求可以返回一个 SystemCode 实例，指明抛出异常的系统
 * <br/> 最终，每个 StatusCode 实例都可输出8位异常编码，格式：2位【系统级编码】+2位【子系统编码】+4位【错误状态编码】
 * <br/> 可参考一个典型的实现类 cn.cttic.sysframe.common.exception.code.StatusCodeForFrame
 * <br/> 注意，实现getModuleCode()方法时一定要正确的返回ModuleCode，该ModuleCode将指明异常的来源
 * @see cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore
 * @author caohui
 *
 */
public interface StatusCode {
	
	enum CodeType {
		SYSTEM, //系统级异常
		BUSINESS; //业务级异常
	}

//	/**
//	 * 返回完整的6位状态编码
//	 * @return 
//	 * @author caohui
//	 * @date May 19, 2014 7:49:23 PM
//	 */
//	String getFullCode();
	/**
	 * 返回4位(0000~9999)错误状态编码
	 * @return
	 */
	String getCode();
	/**
	 * 返回错误状态的描述信息，可包含占位符，该描述最终可被输出至前端页面展示 <br/>
	 * 占位符类似于 {0}, {1}，具体抛出异常时可以指定替换占位符的参数，但占位符中体现的信息不会进行语义的国际化处理，建议一般为数字，日期等
	 * @return
	 */
	String getDescription();
	/**
	 * 返回异常的类型：CodeType.SYSTEM 为系统级异常； CodeType.BUSINESS 为业务级异常
	 * @return
	 */
	CodeType getType();
	/**
	 * 返回该错误状态实例所属的模块（子系统）
	 * @return
	 */
	ModuleCode getModuleCode();
}
