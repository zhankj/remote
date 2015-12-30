package cn.cttic.intf.service.impl;

import org.springframework.stereotype.Service;

import cn.cttic.intf.service.testSerivce;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;
@Service
@ReflectionInvokable(name = "testSerivce")
public class testSerivceImpl implements testSerivce {

	@SuppressWarnings("null")
	@Override
	@ReflectionInvokable(name = "sayHello")
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "hello," + name;
	}	
}
