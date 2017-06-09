/**
 * 
 */
package com.young.shadowsocks.util;

import java.security.MessageDigest;

/**
 * @author Administrator
 *
 */
public class MyEncryptionUtil {
	private static String Encode(String code,String message){  
	    MessageDigest md;  
	    String encode = null;  
	    try {  
	        md = MessageDigest.getInstance(code);  
	        encode = byteArrayToHexString(md.digest(message  
	                .getBytes("utf-8")));  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return encode;  
	}  
	public static String md5Encode(String message){  
        return Encode("MD5",message);  
    }  
    /** 
     * 将摘要信息转换成SHA编码 
     * @param message 摘要信息 
     * @return SHA编码之后的字符串 
     */  
    public static String shaEncode(String message){  
        return Encode("SHA",message);  
    }  
    /** 
     * 将摘要信息转换成SHA-256编码 
     * @param message 摘要信息 
     * @return SHA-256编码之后的字符串 
     */  
    public String sha256Encode(String message){  
        return Encode("SHA-256",message);  
    }  
    /** 
     * 将摘要信息转换成SHA-512编码 
     * @param message 摘要信息 
     * @return SHA-512编码之后的字符串 
     */  
    public static String sha512Encode(String message){  
        return Encode("SHA-512",message);  
    } 
    public static String byteArrayToHexString(byte[] bytes){
    	StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16) { 
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
