/**
 * 
 */
package com.young.shadowsocks.business.service;

import com.young.shadowsocks.entity.UserInfo;

/**
 * @author Administrator
 *
 */
public interface BaseInfoService {

	public UserInfo getUserInfo(String code);
	
	 

	public void insertUserInfo(UserInfo info);



	public void deleteUserInfo(UserInfo info);

}
