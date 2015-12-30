package cn.cttic.sysframe.remote;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;
import cn.cttic.sysframe.common.support.AnnotatedClassScaner;
import cn.cttic.sysframe.common.support.StatusCodeSupport;
import cn.cttic.sysframe.common.support.structure.Pair;
import cn.cttic.sysframe.common.util.SpringContextUtil;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;

import com.google.common.collect.Maps;
/**
 * 扫描 @ReflectionInvokable 注解，加载被注解的方法调用元数据
 * @author caohui
 *
 */
@Service
public class ReflectionInvoker {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private static Map<String, Pair<Class<?>, Method>> methodMap = Maps.newHashMap();
	
	@Autowired
	public ReflectionInvoker(AnnotatedClassScaner annotatedClassScaner) {
		List<Class<?>> targetClasses = annotatedClassScaner.getAnnotatedClassMap().get(ReflectionInvokable.class);
		if(targetClasses!=null){
			for (Class<?> targetClass : targetClasses) {
				ReflectionInvokable riForTarget = targetClass.getAnnotation(ReflectionInvokable.class);
				for (Method method : targetClass.getMethods() ) {
					ReflectionInvokable riForMethod = method.getAnnotation(ReflectionInvokable.class);
					if (riForMethod == null) continue;
					String key = new StringBuilder().append(riForTarget.name()).append(".").append(riForMethod.name()).toString();
					if (methodMap.get(key) != null) {
						LOG.error("发现重复的ReflectionInvokable服务映射定义，" + key + " 已经存在，请检查 @ReflectionInvokable 注解的 name 属性是否有重复定义");
						throw new SystemException(StatusCodeForFrameCore.REFLECTION_INVOKABLE_LOAD_ERROR);
					} else {
						Pair<Class<?>, Method> value = new Pair<Class<?>, Method>(targetClass, method);
						methodMap.put(key, value);
					}
				}
			}
		}

	}

	public Map<String, Pair<Class<?>, Method>> getMethodMap() {
		return methodMap;
	}
	
	public Pair<Class<?>, Method> getServiceTaget(String serviceCode) {
		Pair<Class<?>, Method> pair = this.getMethodMap().get(serviceCode);
		if(pair == null) throw new SystemException(StatusCodeForFrameCore.REMOTE_SERVICE_CODE_NOT_EXIST, serviceCode);
		return pair;
	}
	
	public void invoke(AbstractServiceRequest req, AbstractServiceResponse resp, boolean allowThrowException) throws SystemException {
		SystemException status = null;
		try {
			req.build(this);
			Pair<Class<?>, Method> pair = this.getServiceTaget(req.getServiceCode());
			Object target = SpringContextUtil.getBean(pair.getFirst());
			Method method = pair.getLast();
			Object result = method.invoke(target, req.getParams());
			resp.setResult(result);
		}
		catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof SystemException) {
				LOG.error("catch SystemException => ", e.getTargetException());
				status = (SystemException) e.getTargetException();
			}
			else {
				LOG.error("catch unknown Exception => ", e);
				status = new SystemException(e, StatusCodeForFrameCore.INSIDE_ERROR);
			}
		}
		catch (SystemException e) {
			LOG.error("catch SystemException => ", e);
			status = e;
		}
		catch (Exception e) {
			LOG.error("catch unknown Exception => ", e);
			status = new SystemException(e, StatusCodeForFrameCore.INSIDE_ERROR);
		}
		
		
		String statusCode = status == null ? StatusCodeSupport.getFullStatusCode(StatusCodeForFrameCore.SUCCESS) : status.toModel().getCode();
		String statusDesc = status == null ? StatusCodeForFrameCore.SUCCESS.getDescription() : status.toModel().getDesc() + status.getTraceMessage();
		resp.setStatusCode(statusCode);
		resp.setStatusDesc(statusDesc);
		
		this.setServiceCode(req, resp);

		if (allowThrowException && status != null) throw status;
	}
	
	/**
	 * 设置响应的ServiceCode
	 *
	 * @param req
	 * @param resp 
	 * @author caohui
	 * @date Aug 11, 2014 6:02:11 PM
	 */
	public void setServiceCode(AbstractServiceRequest req, AbstractServiceResponse resp) {
		if (req != null && req.getServiceCode() != null) {
			resp.setServiceCode(req.getServiceCode());
		}
		else {
			//再次尝试从JSON中获取queryCode
			try {
	            resp.setServiceCode(req.parseCode());
            }
            catch (Exception e) {
	            LOG.warn("再次尝试从报文中解析业务编码失败", e);
            }
		}
	}
	
	public void invoke(AbstractServiceRequest req, AbstractServiceResponse resp) {
		this.invoke(req, resp, false);
	}
	
}
