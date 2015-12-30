package cn.cttic.pay.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;
import com.wordnik.swagger.annotations.ApiOperation;
@Controller
public class orderPayController {
	
	@RequestMapping(value = "/charge", method = RequestMethod.GET)
	 @ApiOperation(value = "发起交易请求", httpMethod = "GET", response = Charge.class, notes = "创建交易")
	public @ResponseBody Charge getCharge() {  
	    Charge charge = null;  
	    Pingpp.apiKey = "sk_test_nvXLSOybLCS0u18qnPGy9OO8";  
	  
	    Map<String, Object> chargeMap = new HashMap<String, Object>();  
	    // 某些渠道需要添加extra参数，具体参数详见接口文档  
	    chargeMap.put("amount", 100);  
	    chargeMap.put("currency", "cny");  
	    chargeMap.put("subject", "test subject");  
	    chargeMap.put("body", "NO BODY");  
	    chargeMap.put("order_no", "17814213");  
	    chargeMap.put("channel", "alipay_wap");  
	    chargeMap.put("client_ip", "127.0.0.1");  
	    Map<String, String> app = new HashMap<String, String>();          
	    app.put("id", "app_8C4mLKbH8GuLX54m");  
	    Map<String, String> extramap = new HashMap<String, String>();  
	               //extra的参数根据文档: https://pingxx.com/document/api#api-c-new  
	               extramap.put("success_url", "http://127.0.0.1:8070/PartTimeJob/pinus_webview.html");  
	    chargeMap.put("extra", extramap);  
	    chargeMap.put("app", app);  
	      
	    try {  
	        // 发起交易请求  
	        charge = Charge.create(chargeMap);  
	        System.out.println(charge);  
	    } catch (PingppException e) {  
	        e.printStackTrace();  
	    }  
	    return charge;  
	}  
	
	 @RequestMapping(value = "/payhook", method = RequestMethod.POST)
	 public  void payhook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF8");
	        //获取头部所有信息
	        Enumeration headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = (String) headerNames.nextElement();
	            String value = request.getHeader(key);
	            System.out.println(key+" "+value);
	        }
	        // 获得 http body 内容
	        BufferedReader reader = request.getReader();
	        StringBuffer buffer = new StringBuffer();
	        String string;
	        while ((string = reader.readLine()) != null) {
	            buffer.append(string);
	        }
	        reader.close();
	        // 解析异步通知数据
	        Event event = Webhooks.eventParse(buffer.toString());
	        if ("charge.succeeded".equals(event.getType())) {
	            response.setStatus(200);
	        } else if ("refund.succeeded".equals(event.getType())) {
	            response.setStatus(200);
	        } else {
	            response.setStatus(500);
	        }
	    }
}
