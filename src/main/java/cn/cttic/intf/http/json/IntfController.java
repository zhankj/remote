package cn.cttic.intf.http.json;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.rmi.RemoteException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;

import cn.cttic.intf.model.Person;
import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.remote.ReflectionInvoker;
import cn.cttic.sysframe.remote.annotation.ReflectionInvokable;
import cn.cttic.sysframe.common.support.structure.Pair;
import cn.cttic.sysframe.common.util.SpringContextUtil;

@Controller

public class IntfController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	ReflectionInvoker reflectionInvoker;

	public void setReflectionInvoker(ReflectionInvoker reflectionInvoker) {
		this.reflectionInvoker = reflectionInvoker;
	}

	@RequestMapping(value = "/transfer.json")
	@ResponseBody
	public String transfer(
			@RequestBody String inparam) {

		logger.info("Request JSON => " + inparam);
		IntfRequest req = new IntfRequest(inparam);
		IntfResponse resp = new IntfResponse();
		reflectionInvoker.invoke(req, resp);
		logger.info("Response JSON => " + resp.generateMessage());
		return resp.generateMessage();
	}
	
	@RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String hello() {
        return "你好！hello";
    }
	
	 @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.GET)
	    public @ResponseBody
	    Person getPerson(@PathVariable("id") int id) {
	        logger.info("获取人员信息id=" + id);
	        Person person = new Person();
	        person.setName("张三");
	        person.setSex("男");
	        person.setAge(30);
	        person.setId(id);
	        return person;
	    }
	 
	    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.DELETE)
	    public @ResponseBody
	    Object deletePerson(@PathVariable("id") int id) {
	        logger.info("删除人员信息id=" + id);
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("msg", "删除人员信息成功");
	        return jsonObject;
	    }
	 
	    @RequestMapping(value = "/person", method = RequestMethod.POST)
	    public @ResponseBody
	    Object addPerson(Person person) {
	        logger.info("注册人员信息成功id=" + person.getId());
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("msg", "注册人员信息成功");
	        return jsonObject;
	    }
	 
	    @RequestMapping(value = "/person", method = RequestMethod.PUT)
	    public @ResponseBody
	    Object updatePerson(Person person) {
	        logger.info("更新人员信息id=" + person.getId());
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("msg", "更新人员信息成功");
	        return jsonObject;
	    }
	 
	    @RequestMapping(value = "/person", method = RequestMethod.PATCH)
	    public @ResponseBody
	    List<Person> listPerson(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
	 
	        logger.info("查询人员name like " + name);
	        List<Person> lstPersons = new ArrayList<Person>();	 
	        Person person = new Person();
	        person.setName("张三");
	        person.setSex("男");
	        person.setAge(25);
	        person.setId(101);
	        lstPersons.add(person);
	 
	        Person person2 = new Person();
	        person2.setName("李四");
	        person2.setSex("女");
	        person2.setAge(23);
	        person2.setId(102);
	        lstPersons.add(person2);
	 
	        Person person3 = new Person();
	        person3.setName("王五");
	        person3.setSex("男");
	        person3.setAge(27);
	        person3.setId(103);
	        lstPersons.add(person3);
	 
	        return lstPersons;
	    }
	   

}

