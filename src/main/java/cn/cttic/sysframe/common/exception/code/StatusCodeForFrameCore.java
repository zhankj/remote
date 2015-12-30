package cn.cttic.sysframe.common.exception.code;





/**
 * Frame模块状态编码枚举类
 * 
 * @author caohui
 *
 */
public enum StatusCodeForFrameCore implements StatusCode {
	SUCCESS("0000", CodeType.BUSINESS, "成功"),
	INSIDE_ERROR("0001", CodeType.SYSTEM, "系统内部异常"),
	DB_OPERATION_ERROR("0002", CodeType.SYSTEM, "数据库操作错误"),
	PROPERTY_IS_NULL("0003", CodeType.BUSINESS, "属性不能为空"),
	RECORD_NOT_EXISTS("0004", CodeType.BUSINESS, "记录不存在"),
	REFLECTION_INVOKABLE_LOAD_ERROR("0005", CodeType.SYSTEM, "反射服务调用加载失败"),
	NEW_INSTANCE_ERROR("0006", CodeType.SYSTEM, "构造实体出错"),
	TYPE_CAST_ERROR("0007", CodeType.SYSTEM, "类型转换错误"),
	XML_PARSE_ERROR("0008", CodeType.SYSTEM, "XML解析错误"),
	NOT_EXIST_OPER_TYPE_ERROR("0009", CodeType.SYSTEM, "不存在的operType"),
	UPDATE_PROP_ERROR("00010", CodeType.SYSTEM, "更新实体对象 {0} 的属性 {1} 时出错"),
	GET_PROP_ERROR("0011", CodeType.SYSTEM, "获取实体对象 {0} 的属性 {1} 时出错"),
	NOT_EXIST_SERVICE_CODE_ERROR("0012", CodeType.BUSINESS, "无法找到匹配的serviceCode {0}"),
	JSON_PARSE_ERROR("0013", CodeType.SYSTEM, "JSON解析错误"),
	REMOTE_CALL_PARAMS_AMOUNT_ERROR("0014", CodeType.SYSTEM, "远程调用传入的参数数量不准确，服务端期望参数个数为 {0}, 实际传入个数为 {1}"),
	JSON_GENERATE_ERROR("0015", CodeType.SYSTEM, "JSON解析错误"),
	MESSAGE_PARSE_KEY_ERROR("0016", CodeType.SYSTEM, "无法从报文中找到名为 {0} 的值"),
	OBJECT_TO_JSON_ERROR("0017", CodeType.SYSTEM, "对象 {0} 转换到JSON格式失败"),
	HTTP_IO_ERROR("0018", CodeType.SYSTEM, "远程调用连接请求失败"),
	REMOTE_CALL_ERROR("0019", CodeType.BUSINESS, "远程调用返回失败：{0}"),
	FIELD_COPY_ERROR("0020", CodeType.SYSTEM, "{0} 属性拷贝异常，源类型 {1}， 目标类型 {2} "),
	REMOTE_SERVICE_CODE_NOT_EXIST("0021", CodeType.SYSTEM, "接口 {0} 不存在，请检查serviceCode的值是否正确");
	
	private String code;
	private CodeType type;
	private String description;
	
	private StatusCodeForFrameCore(String code, CodeType type, String description) {
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
		return ModuleCodeForFrameCore.SYSTEM_MANAGEMENT;
	}

}
