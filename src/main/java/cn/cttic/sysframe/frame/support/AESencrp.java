package cn.cttic.sysframe.frame.support;
import java.security.Key;  
import java.util.Date;

import javax.crypto.Cipher;  
import javax.crypto.spec.SecretKeySpec;  

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
  
/** 
 * 用来进行AES的加密和解密程序 
 *  
 * @author Steven 
 *  
 */  
@SuppressWarnings("restriction")
public class AESencrp {
	 // 加密算法  
    private String ALGO;  
  
    // 加密密钥  
    // private static final byte[] keyValue = new byte[] { 'T', 'h', 'e',  
    // 'B','e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };  
    // 16位的加密密钥  
    private byte[] keyValue;  
  
    /** 
     * 用来进行加密的操作 
     *  
     * @param Data 
     * @return 
     * @throws Exception 
     */  
    public String encrypt(String Data) throws Exception {  
        Key key = generateKey();  
        Cipher c = Cipher.getInstance(ALGO);  
        c.init(Cipher.ENCRYPT_MODE, key);  
        byte[] encVal = c.doFinal(Data.getBytes());  
        String encryptedValue = new BASE64Encoder().encode(encVal);  
        return encryptedValue;  
    }  
  
    /** 
     * 用来进行解密的操作 
     *  
     * @param encryptedData 
     * @return 
     * @throws Exception 
     */  
    public String decrypt(String encryptedData) throws Exception {  
        Key key = generateKey();  
        Cipher c = Cipher.getInstance(ALGO);  
        c.init(Cipher.DECRYPT_MODE, key);  
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);  
        byte[] decValue = c.doFinal(decordedValue);  
        String decryptedValue = new String(decValue);  
        return decryptedValue;  
    }  
  
    /** 
     * 根据密钥和算法生成Key 
     *  
     * @return 
     * @throws Exception 
     */  
    private Key generateKey() throws Exception {  
        Key key = new SecretKeySpec(keyValue, ALGO);  
        return key;  
    }  
  
    public String getALGO() {  
        return ALGO;  
    }  
  
    public void setALGO(String aLGO) {  
        ALGO = aLGO;  
    }  
  
    public byte[] getKeyValue() {  
        return keyValue;  
    }  
  
    public void setKeyValue(byte[] keyValue) {  
        this.keyValue = keyValue;  
    }  
    
    public static  String Encrypt(String inPut) throws Exception{
    	 AESencrp aes = new AESencrp();
    	 aes.setALGO("AES"); 
    	 aes.setKeyValue("GGSJPTLHCXCHANGH".getBytes());
    	 String output =  aes.encrypt(inPut);
    	 Base64 base64Encoder = new Base64(true);
    	 return  base64Encoder.encodeBase64URLSafeString(output.getBytes());
    }
    
    public static  String Decrypt(String inPut) throws Exception{
   	 AESencrp aes = new AESencrp();
   	 Base64 base64Encoder = new Base64(true);
   	 String output = new String(base64Encoder.decodeBase64(inPut));
   	 aes.setALGO("AES"); 
   	 aes.setKeyValue("GGSJPTLHCXCHANGH".getBytes());
   	 return aes.decrypt(output);
   }
   
    public static void main(String[]args){
    	String nowStr = new Date().getTime() + "";
    	String encpt;
		try {
			encpt = AESencrp.Encrypt(nowStr);
			String dcrpt = AESencrp.Decrypt(encpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
}
