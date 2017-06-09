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
     * ��ժҪ��Ϣת����SHA���� 
     * @param message ժҪ��Ϣ 
     * @return SHA����֮����ַ��� 
     */  
    public static String shaEncode(String message){  
        return Encode("SHA",message);  
    }  
    /** 
     * ��ժҪ��Ϣת����SHA-256���� 
     * @param message ժҪ��Ϣ 
     * @return SHA-256����֮����ַ��� 
     */  
    public String sha256Encode(String message){  
        return Encode("SHA-256",message);  
    }  
    /** 
     * ��ժҪ��Ϣת����SHA-512���� 
     * @param message ժҪ��Ϣ 
     * @return SHA-512����֮����ַ��� 
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
