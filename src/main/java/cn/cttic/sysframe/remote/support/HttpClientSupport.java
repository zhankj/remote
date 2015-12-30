package cn.cttic.sysframe.remote.support;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrameCore;
import cn.cttic.sysframe.common.util.MD5Util;

/**
 * HTTP Client 支持类 <br/>
 * 方便发送 JSON 类型的 POST 请求
 * @author caohui@cttic.cn
 * @date Jun 4, 2014 1:04:28 PM
 * @copyright: Copyright (c) 2014 CTTIC
 */
public class HttpClientSupport {
	private static final Logger LOG = LoggerFactory.getLogger(HttpClientSupport.class);
	
	public static String postJsonRequest(String path, String json) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(path);
            httppost.addHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity(json, "UTF-8");
            httppost.setEntity(entity);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            
            LOG.debug("执行请求=> " + httppost.getRequestLine() + httppost.getEntity().getContent());
            String responseBody = httpclient.execute(httppost, responseHandler);
            LOG.debug("接收响应=> " + responseBody);
            return responseBody;
        } catch (Exception e) {
        	throw new SystemException(e, StatusCodeForFrameCore.HTTP_IO_ERROR);
        } finally {
            try {
				httpclient.close();
			} catch (IOException e) {
				throw new SystemException(e, StatusCodeForFrameCore.HTTP_IO_ERROR);
			}
        }
	}
	
	 
    /**
     * 发送get请求
     * @param url  路径
     * @return
     */
    public static String httpGetRequest(String url){
        //get请求返回结果
        String strResult = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            //发送get请求
           HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //读取服务器返回过来的json字符串数据
                strResult = EntityUtils.toString(response.getEntity());
                //把json字符串转换成json对象
                LOG.info("httpclient get请求返回过来的字符串:" + strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
            	LOG.error("httpclient get请求提交失败:" + url);
                throw new ClientProtocolException("Unexpected response status: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
        	LOG.error("httpclient get请求提交失败:" + url, e);
        	throw new SystemException(e, StatusCodeForFrameCore.HTTP_IO_ERROR);
        } finally {
        	  try {
        		  client.close();
  			} catch (IOException e) {
  				throw new SystemException(e, StatusCodeForFrameCore.HTTP_IO_ERROR);
  			}
        }
        return strResult;
    }

    public static void main(String[] args) {
    	String url = "http://api.myships.com/DataApiServer/getShipLatest?shipid=413775656" + "&key=";
    	String key = "20150724" + "46b89d08390b4fdfa4cbcd5b7d1e1927";
    	url += MD5Util.encode(key).toLowerCase();
    	System.out.println(httpGetRequest(url));
	}
}
