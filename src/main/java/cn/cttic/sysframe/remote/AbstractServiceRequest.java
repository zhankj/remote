package cn.cttic.sysframe.remote;

import java.lang.reflect.Method;
import java.util.Map;

import cn.cttic.sysframe.common.support.structure.Pair;

import com.google.common.collect.Maps;

/**
 * 抽象的服务请求类 <br/>
 * 承载请求数据；服务端解析请求报文为实体对象，客户端序列化实体对象为请求报文
 * @author caohui@cttic.cn
 * @date May 30, 2014 4:58:39 PM
 * @copyright: Copyright (c) 2014 CTTIC
 */
public abstract class AbstractServiceRequest {
	private String message;
	private String serviceCode;
	private Object[] params;
	private Map<String, String> additional = Maps.newLinkedHashMap();
	
	public AbstractServiceRequest() {
		super();
	}
	/**
	 * 使用请求报文构造对象，用于服务端
	 * @param message
	 */
	public AbstractServiceRequest(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getServiceCode() {
		return serviceCode;
	}
	
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	public Object[] getParams() {
		return params;
	}
	
	public void setParams(Object[] params) {
		this.params = params;
	}
	
	/**
	 * 返回所有附件节点信息
	 *
	 * @return 
	 * @author caohui
	 * @date Aug 13, 2014 5:59:20 PM
	 */
	protected Map<String, String> getAdditionalNodes() {
		return this.additional;
	}
	
	/**
	 * 追加附加节点
	 *
	 * @param key
	 * @param value
	 * @return 
	 * @author caohui
	 * @date Aug 13, 2014 5:59:46 PM
	 */
	public AbstractServiceRequest appendAdditionalNode(String key, String value) {
		this.additional.put(key, value);
		return this;
	}

	/**
	 * 解析报文，初始化请求对象
	 * @param invoker 
	 * @author caohui
	 * @date Jun 4, 2014 1:12:24 PM
	 */
	public void build(ReflectionInvoker invoker) {
		this.serviceCode = this.parseCode();
		Pair<Class<?>,Method> pair = invoker.getServiceTaget(this.serviceCode);
		this.params = this.parseParams(pair.getLast().getParameterTypes());
	}
	
	/**
	 * 业务编码节点名称
	 * @return 
	 * @author caohui
	 * @date May 30, 2014 4:53:27 PM
	 */
	public abstract String serviceCodeNodeName();
	/**
	 * 业务入参节点的名称
	 *
	 * @return 
	 * @author caohui
	 * @date May 30, 2014 4:53:59 PM
	 */
	public abstract String paramsNodeName();
	/**
	 * 所有附加节点的父节点名称
	 *
	 * @return 
	 * @author caohui
	 * @date Aug 13, 2014 4:37:38 PM
	 */
	public String additionalNodeName() {
		return "additional";
	}
	/**
	 * 解析报文中的入参
	 * @param paramsType
	 * @return 
	 * @author caohui
	 * @date May 30, 2014 4:55:47 PM
	 */
	public abstract Object[] parseParams(Class<?>... paramsType);
	/**
	 * 解析报文中的业务编码
	 * @return 
	 * @author caohui
	 * @date May 30, 2014 4:55:19 PM
	 */
	public abstract String parseCode();
	/**
	 * 根据服务定义和运行时的入参对象生成请求报文，用于客户端
	 * @return 
	 * @author caohui
	 * @date May 30, 2014 5:04:48 PM
	 */
	public abstract String generateMessage(ServiceDefinition serviceCode, Object ... params);

}
