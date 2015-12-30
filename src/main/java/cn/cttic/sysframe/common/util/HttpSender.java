package cn.cttic.sysframe.common.util;


import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class HttpSender {
	  private static int timeout = 10000;

	  private static Logger logger = Logger.getLogger(HttpSender.class);
	  
	  public static String doPost(String xml, String requestURL)
	    throws SystemException
	  {
	    String retStr = "";
	    try {
	      URL url = new URL(requestURL);
	      URLConnection uc = url.openConnection();
	      uc.setConnectTimeout(timeout);
	      uc.setReadTimeout(timeout);
	      uc.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
	      uc.setRequestProperty("User-Agent", "Mozilla/4.0");
	      uc.setRequestProperty("Accept", "application/soap+xml, application/dime, multipart/related, text/*");
	      uc.setRequestProperty("Accept-Encoding", "gzip, deflate");
	      uc.setRequestProperty("Cache-Control", "no-cache");
	      uc.setRequestProperty("no-cache", "no-cache");
	      uc.setDoOutput(true);
	      uc.setDoInput(true);

	      HttpURLConnection httpConnection = (HttpURLConnection)uc;
	      httpConnection.setRequestMethod("POST");

	      OutputStream os = httpConnection.getOutputStream();

	      byte[] bt = (byte[])null;
	      bt = xml.getBytes("utf-8");
	      os.write(bt);
	      os.close();
	      int rspCode = httpConnection.getResponseCode();

	      InputStream is = null;
	      if (rspCode == 200)
	        is = httpConnection.getInputStream();
	      else {
	        is = httpConnection.getErrorStream();
	      }

	      InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	      BufferedReader br = new BufferedReader(isr);
	      String tstr = "";
	      String data = "";
	      String dataTmp = "";
	      while ((tstr = br.readLine()) != null) {
	        data = data + tstr + "\n";
	        dataTmp = dataTmp + tstr;
	      }
	      retStr = data;
	      isr.close();
	      is.close();
	    } catch (Exception ee) {
	      ee.printStackTrace();
	      throw new SystemException(StatusCodeForFrame.POST_ERROR);
	    }
	    return retStr;
	  }

	  public static String callService(String xmlStr, String soapAction, String requestURL) throws SystemException {
		    String retStr = "";
		    try {
		    	logger.debug(String.format("callService req:[%s]", xmlStr));
		      URL url = new URL(requestURL);
		      URLConnection urlConnection = url.openConnection();

		      urlConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		      urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0");
		      urlConnection.setRequestProperty("Accept", "application/soap+xml, application/dime, multipart/related, text/*");

		      urlConnection.setRequestProperty("SOAPAction", soapAction);
		      urlConnection.setRequestProperty("Cache-Control", "no-cache");
		      urlConnection.setRequestProperty("Pragma", "no-cache");
		      urlConnection.setDoOutput(true);
		      urlConnection.setDoInput(true);

		      HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
		      httpConnection.setRequestMethod("POST");

//		      OutputStream os = httpConnection.getOutputStream();
//		      byte[] bt = xmlStr.getBytes();
//		      os.write(bt);
//		      os.flush();
//		      os.close();


				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(httpConnection.getOutputStream(), "UTF-8"));
				bw.write(xmlStr);
				bw.flush();
				bw.close();


		      InputStream is = null;
		      int rspCode = httpConnection.getResponseCode();
		      logger.debug("HttpResponseCode=" + rspCode);
		      if (rspCode == 200)
		        is = httpConnection.getInputStream();
		      else {
		        is = httpConnection.getErrorStream();
		      }

		      InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		      BufferedReader br = new BufferedReader(isr);
		      String tstr = "";
		      String data = "";
		      String dataTmp = "";
		      while ((tstr = br.readLine()) != null) {
		        data = data + tstr + "\n";
		        dataTmp = dataTmp + tstr;
		      }
		      if (dataTmp.trim().length() <= 0)
		        throw new SystemException(StatusCodeForFrame.CALLSERVICE_ERROR);

		      retStr = data;
		      isr.close();
		      is.close();
		    } catch (Exception ee) {
		      ee.printStackTrace();
		      retStr = "";
		    }
		    
		    logger.debug(String.format("callService resp:[%s]", retStr));
		    return retStr;
		  }
	  
	  
	  public static StatusCodeForFrame postRestService(String requestURL,Map<String, String> parammap ,StringBuffer respContext)  {
		  StatusCodeForFrame code = StatusCodeForFrame.SUCCESS;
		  try {
			  if(!Strings.isNullOrEmpty(requestURL)){
				  @SuppressWarnings({ "resource", "deprecation" })
				HttpClient client = new DefaultHttpClient();
				  HttpPost post = new HttpPost(requestURL);
				  List<BasicNameValuePair> array = Lists.newArrayList();
				  
				  BasicNameValuePair pair=null;
				  for (Map.Entry<String, String> entry : parammap.entrySet()) {
					  pair = new BasicNameValuePair(entry.getKey(),entry.getValue());
					  array.add(pair);
				  }
				  
				  if(array.size() > 0){
					  post.setEntity(new UrlEncodedFormEntity(array));
				  }
				  
				  HttpResponse response = client.execute(post);

				  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				  
				  String line = "";
				  while ((line = rd.readLine()) != null) {
					  respContext.append(line);
				  }
				  
			  }else {
				 code = StatusCodeForFrame.POST_REST_ERROR;
			  }
			  
		  	} catch (Exception e) {
		  		code = StatusCodeForFrame.POST_REST_ERROR;
		  		logger.debug(String.format(code.getDescription(), e.getMessage()));
		  	}
		  
		  return code ;
	  }
	  
	  
	
	public static int isConnect(String urlStr) {  
	   if (Strings.isNullOrEmpty(urlStr)) {                         
		   return -1;                   
	   }else {
		   try {  
			     URL url = new URL(urlStr);  
			     HttpURLConnection con = (HttpURLConnection) url.openConnection();  
			     int state = con.getResponseCode();  
			     
			     if (state == 200) {  
			        return 0 ;
			     }else {
					return state;
				}
			    }catch (Exception ex) {  
			    
			    	return -1 ;
			    }  
	   }  

	}  
}
