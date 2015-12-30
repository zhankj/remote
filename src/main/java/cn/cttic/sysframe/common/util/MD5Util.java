package cn.cttic.sysframe.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.cttic.sysframe.common.exception.SystemException;
import cn.cttic.sysframe.common.exception.code.StatusCodeForFrame;

/**
 *
 * Title: sysframe <br>
 * Description: MD5加密算法 <br>
 * Date: 2014-4-10 <br>
 * Copyright (c) 2014 CTTIC <br>
 * 
 */
public class MD5Util {

	/**
	 * 输入字符串，生成32位MD5码
	 *
	 * @param inStr
	 * @return 
	 * @author zhangzechen
	 * @date 2015年6月30日 下午5:12:28
	 */
	public static String generateMD5(String inStr) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(inStr.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = "0" + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	public static String encode(String input) {
		byte[] digesta = null;
		try {
			MessageDigest alga = MessageDigest.getInstance("MD5");
			try {
				alga.update(input.getBytes("utf-8"));
			} catch (UnsupportedEncodingException ex) {
				throw new SystemException(StatusCodeForFrame.USER_DEFINDES_ERROR, ex);
			}
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return byteToHexString(digesta);
	}

    public static void main(String[] args) {
        try {
            System.out.println("-----test-----");
            String test = "111111";
            System.out.println("MD5加密" + test);
            String jiami = MD5Util.encode(test);
            System.out.println(jiami);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
