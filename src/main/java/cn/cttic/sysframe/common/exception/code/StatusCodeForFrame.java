package cn.cttic.sysframe.common.exception.code;



/**
 * Frame模块状态编码枚举类
 * 
 * @author caohui
 */
public enum StatusCodeForFrame implements StatusCode {
	SUCCESS("0000", CodeType.BUSINESS, "成功"),
	INSIDE_ERROR("0001", CodeType.SYSTEM, "系统内部异常"),
	DB_OPERATION_ERROR("0002", CodeType.SYSTEM, "数据库操作错误"),
	PROPERTY_IS_NULL("0003", CodeType.BUSINESS, "属性不能为空"),
	RECORD_NOT_EXISTS("0004", CodeType.BUSINESS, "记录不存在"),
	RECORD_NOT_UNIQUELY("0005", CodeType.BUSINESS, "记录不唯一"),
	RECORD_STATE_ERROR("0006", CodeType.BUSINESS, "记录状态异常"),
	RECORD_CONFIG_ERROR("0007", CodeType.BUSINESS, "数据配置异常"),
	CACHE_INITIALIZE_ERROR("0008", CodeType.SYSTEM, "初始化加载业务缓存失败"),
	CACHE_NOT_EXIST_ERROR("0009", CodeType.SYSTEM, "缓存区 {0} 不存在"),
	CACHE_LOADER_NOT_EXIST_ERROR("0010", CodeType.SYSTEM, "缓存区 {0} 不支持手动加载"),
	REQUEST_PARAMETER_IS_NULL("0011", CodeType.BUSINESS, "请求参数 {0} 不存在或其值为空"),
	PROPERTITY_NOT_EXISTS("0012", CodeType.BUSINESS, "属性不存在"),
	JSON_GENERATE_ERROR("0015", CodeType.SYSTEM, "JSON解析错误"),
	OPERATOR_NOT_EXIST_ERROR("9995", CodeType.BUSINESS, "工号 {0} 不存在"),
	INVALID_PASSWORD_OF_OPERATOR_ERROR("9996", CodeType.BUSINESS, "工号 {0} 的密码输入不正确"),
	INVALID_STATUS_OF_OPERATOR_ERROR("9997", CodeType.BUSINESS, "工号 {0} 的状态已经失效"),
	UN_LOGIN("9998", CodeType.BUSINESS, "链接已失效，请重新登录"),
	USER_DEFINDES_ERROR("9999", CodeType.BUSINESS, "{0}"),
	INVALID_ORGAN("2001", CodeType.BUSINESS, "部门[{0}]已失效，请检查数据"),
	NONEXISTENT_ORGAN("2002", CodeType.BUSINESS, "部门[{0}]不存在，请检查数据"),
	EXISTENT_ORGAN("2003", CodeType.BUSINESS, "部门编码[{0}]已存在，请检查数据"),
	INVALID_ORGAN_TREE("2004", CodeType.BUSINESS, "该组织机构的上级组织机构为该组织机构的下级组织机构，请检查数据"),
	INVALID_STAFF( "3001", CodeType.BUSINESS, "人员[{0}]已失效，请检查数据"),
	NONEXISTENT_STAFF( "3002", CodeType.BUSINESS, "人员[{0}]不存在，请检查数据"),
	REPEAT_STAFF( "3003", CodeType.BUSINESS, "人员编码[{0}]已存在，请检查数据"),
	UNOPERATE("4000", CodeType.BUSINESS, "不允许对超级管理员工号做该操作"),
	EXISTENT_OPER( "4001", CodeType.BUSINESS, "工号[{0}]已存在，请检查数据"),
	NORMAL_OPER("4002", CodeType.BUSINESS, "工号[{0}]为正常状态，不需要做解禁操作"),
	CANCEL_OPER("4003", CodeType.BUSINESS, "工号[{0}]为禁用状态，不需要做禁用操作"),
	RESET_OPER("4004", CodeType.BUSINESS, "工号[{0}]不能重置自己的密码"),
	NONEXISTENT_MENU( "5001", CodeType.BUSINESS, "菜单[{0}]不存在，请检查数据"),
	INVALID_MENU_TREE( "5002", CodeType.BUSINESS, "该菜单的上级菜单为该菜单的下级菜单，请检查数据"),
	MAIL_SEND_DELAY( "6001", CodeType.BUSINESS, "延时发送模式，预发送时间不能为空"),
	UNKNOW_MAIL_SEND_TYPE( "6002", CodeType.BUSINESS, "发送类型不正确"),
	MAIL_SEND_FAILURE( "6003", CodeType.BUSINESS, "[邮件发送失败]{0}"),
	FILE_UPLOAD_FAILURE( "6004", CodeType.BUSINESS, "上传文件不存在！"),
	FILE_JOB_CODE_FAILURE( "6005", CodeType.BUSINESS, "获取系统JOB_CODE出错{0}"),
	FILE_JOB_CONFIG_FAILURE( "6006", CodeType.BUSINESS, "加载[SM_JOB_CONFIG 配置]缓存数据异常..."),
	FAVORITES_MAX( "7001", CodeType.BUSINESS, "常用菜单最多添加8个，请检查"),
	POST_ERROR( "8001", CodeType.BUSINESS, "POST 提交数据失败"),
	CALLSERVICE_ERROR("8002", CodeType.BUSINESS, "调用webservice失败"),
	POST_REST_ERROR("8003", CodeType.BUSINESS, "调用rest service失败");
	

	
	
	private String code;

	private CodeType type;

	private String description;

	private StatusCodeForFrame(String code, CodeType type, String description) {
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
		return ModuleCodeForFrame.SYSTEM_MANAGEMENT;
	}

}
