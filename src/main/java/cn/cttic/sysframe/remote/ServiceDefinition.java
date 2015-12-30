package cn.cttic.sysframe.remote;

/**
 * 业务编码定义枚举接口类 <br/>
 * 用于服务调用的客户端，定义业务调用的方法签名
 * @author caohui@cttic.cn
 * @date May 30, 2014 5:01:14 PM
 * @copyright: Copyright (c) 2014 CTTIC
 */
public interface ServiceDefinition {
	/**
	 * 定义服务是否集合类型 <br/>
	 * 如果<code> getReturnCollectionType() </code> 返回 null 则此方法的实现应该返回false，否则返回true;
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 12:30:59 PM
	 */
	boolean isReturnCollection();
	/**
	 * 定义服务返回的具体集合类型 <br/>
	 * 如果服务返回单一结果对象而不是集合，此方法的实现应该返回null
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 12:34:51 PM
	 */
	Class<?> getReturnCollectionType();
	/**
	 * 定义服务返回的具体类型 <br/>
	 * 如果服务返回一个集合，则该方法实现返回集合容器的具体对象类型
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 12:37:43 PM
	 */
	Class<?> getReturnType();
	/**
	 * 定义服务入参的具体类型 <br />
	 * 由于可能有多个入参对象，所以该方法的实现返回一个类型数组
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 12:42:04 PM
	 */
	Class<?>[] getParamsType();
	
	/**
	 * 定义服务的业务编码
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 12:43:14 PM
	 */
	String getServiceCode();
	/**
	 * 定义服务的请求URL路径 <br/>
	 * 该路径不包含所有服务公用的基本路径
	 * @return 
	 * @author caohui
	 * @date Jun 4, 2014 12:43:53 PM
	 */
	String getServicePath();
}
