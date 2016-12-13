/**
 * 
 */
package com.young.shadowsocks.controller;

import java.util.HashMap;
import java.util.Map;

import com.young.shadowsocks.util.JSONUtilNew;

/**
 * @author Administrator
 *
 */
public class BaseControllerSupport {
	public final static String SUCCESS="success";
	public final static String FAIL="fail";
	public String setMap(String statue,String message,Object data){
		Map<String, Object> map=new HashMap<String, Object>();
		if(data=="[]" || "[]".equals(data)){
			map.put("state", FAIL);
			map.put("message", "Ê§°Ü");
			map.put("data", null);
		}else{
			map.put("state", statue);
			map.put("message", message);
			map.put("data", data);
		}
		String str=JSONUtilNew.mapToJson(map);
		return str;
		
	}
}
