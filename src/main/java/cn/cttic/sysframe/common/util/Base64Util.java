package cn.cttic.sysframe.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	/**
	 * 
	 * 将二进制数据编码为BASE64字符串
	 * 
	 * @param binaryData
	 * 
	 * @return
	 */

	public static String encode(byte[] binaryData) {

		try {

			return new String(Base64.encodeBase64(binaryData), "UTF-8");

		} catch (UnsupportedEncodingException e) {

			return null;

		}

	}

	/**
	 * 
	 * 将BASE64字符串恢复为二进制数据
	 * 
	 * @param base64String
	 * 
	 * @return
	 */

	public static byte[] decode(String base64String) {

		try {

			return Base64.decodeBase64(base64String.getBytes("UTF-8"));

		} catch (UnsupportedEncodingException e) {

			return null;

		}

	}
	
	public String getFileByteString(File file) throws Exception{  
	    Base64 b64 = new Base64();  
	    FileInputStream fis = new FileInputStream(file);  
	    System.out.print(file.length());  
	    byte[] buffer = new byte[(int)file.length()];  
	    System.out.print(buffer.length);  
	    fis.read(buffer);  
	    fis.close();  
	              
	    return b64.encodeToString(buffer);  
	}  
	  
	public void getFileByString(String string, String target) throws Exception{  
	    Base64 b64 = new Base64();  
	    byte[] buffer = b64.decode(string);  
	    FileOutputStream fos = new FileOutputStream(target);  
	    fos.write(buffer);  
	    fos.close();  
	}  
	
	public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new Base64().encodeToString(b);
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(s);
            s = m.replaceAll("");
        }  
        return s;  
    }  
  
    // 解密  
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
        	Base64 decoder = new Base64();  
            try {  
                b = decoder.decode(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  

}