package cn.cttic.sysframe.remote;

import cn.cttic.sysframe.remote.support.HttpClientSupport;

/**
 * 抽象的 REST Service 客户端 <br/>
 * 类详细说明.
 * @author caohui@cttic.cn
 * @date Jun 4, 2014 1:00:11 PM
 * @copyright: Copyright (c) 2014 CTTIC
 */
public abstract class AbstractRestServiceClient {
	
	private String baseURL;
	private AbstractServiceRequest request;
	private AbstractServiceResponse response;
	/**
	 * 设置客户端所使用的 <code> AbstractServiceRequest </code> 实现类，用于将入参对象生成请求报文
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 12:50:10 PM
	 */
    public abstract AbstractServiceRequest setupRequest();
    /**
     * 设置客户端所使用的 <code> AbstractServiceResponse </code> 实现类，用于将响应报文转换为具体对象
     * @return 
     * @author caohui
     * @date Jun 4, 2014 12:53:38 PM
     */
    public abstract AbstractServiceResponse setupResponse();

	public AbstractRestServiceClient(String baseURL) {
		this.baseURL = baseURL;
		this.request = this.setupRequest();
		this.response = this.setupResponse();
	}
	/**
	 * 使用指定的服务定义与具体入参对象调用远程服务，返回服务定义期望的返回类型的具体对象
	 * @param service
	 * @param params
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 12:58:34 PM
	 */
	public <R> R invokeService(ServiceDefinition service, Object ... params) {
		String message = HttpClientSupport.postJsonRequest(this.baseURL + service.getServicePath(), request.generateMessage(service, params));
		return response.<R>generateReturnObject(message, service);
	}
	/**
	 * 设置请求报文的附加节点信息
	 * @param key
	 * @param value
	 * @return 
	 * @author caohui
	 * @date Aug 13, 2014 5:55:29 PM
	 */
	public AbstractRestServiceClient setRequestAdditionalNode(String key, String value) {
		this.request.appendAdditionalNode(key, value);
		return this;
	}
}
