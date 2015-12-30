package cn.cttic.sysframe.remote;

import java.util.Map;

import com.google.common.collect.Maps;


public abstract class AbstractServiceResponse {
	
	private String serviceCode;
	private String statusCode;
	private String statusDesc;
	private Object result;
	private Map<String, String> additionalNodes = Maps.newHashMap();
	
    public String getServiceCode() {
    	return serviceCode;
    }
	
    public void setServiceCode(String serviceCode) {
    	this.serviceCode = serviceCode;
    }
	
    public String getStatusCode() {
    	return statusCode;
    }
	
    public void setStatusCode(String statusCode) {
    	this.statusCode = statusCode;
    }
	
    public String getStatusDesc() {
    	return statusDesc;
    }
	
    public void setStatusDesc(String statusDesc) {
    	this.statusDesc = statusDesc;
    }
    /**
     * 获取所有附件节点
     *
     * @return 
     * @author caohui
     * @date Aug 13, 2014 5:48:04 PM
     */
    protected Map<String, String> getAdditionalNodes() {
    	return this.additionalNodes;
    }
    /**
     * 追加附件节点
     *
     * @param key
     * @param value
     * @return 
     * @author caohui
     * @date Aug 13, 2014 5:48:18 PM
     */
    public AbstractServiceResponse appendNode(String key, String value) {
    	this.additionalNodes.put(key, value);
    	return this;
    }
	
    public Object getResult() {
    	return result;
    }
	
    public void setResult(Object result) {
    	this.result = result;
    }
    /**
     * 业务编码节点名称
     * @return 
     * @author caohui
     * @date Jun 4, 2014 1:14:48 PM
     */
	public abstract String serviceCodeNodeName();
	/**
	 * 服务调用结果状态码节点的名称
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 1:15:07 PM
	 */
	public abstract String statusCodeNodeName();
	/**
	 * 服务调用结果描述节点的名称
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 1:15:56 PM
	 */
	public abstract String statusDescNodeName();
	/**
	 * 服务调用结果集节点的名称
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 1:16:26 PM
	 */
	public abstract String resultNodeName();
	/**
	 * 根据服务返回结果生成响应报文，用于服务端
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 1:28:06 PM
	 */
	public abstract String generateMessage();
	/**
	 * 根据响应报文和服务定义，生成服务的返回对象
	 * @param message
	 * @param service
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 1:29:36 PM
	 */
	public abstract <R> R generateReturnObject(String message, ServiceDefinition service);
}
