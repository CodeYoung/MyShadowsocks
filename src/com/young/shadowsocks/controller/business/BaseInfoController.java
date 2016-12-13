/**
 * 
 */
package com.young.shadowsocks.controller.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.shadowsocks.business.service.BaseInfoService;
import com.young.shadowsocks.controller.BaseControllerSupport;
import com.young.shadowsocks.entity.UserInfo;

/**
 * @author Administrator
 *
 */

@RequestMapping("/baseinfo")
@Controller
public class BaseInfoController extends BaseControllerSupport {
	
	@Autowired
	private BaseInfoService baseInfoService;
	
	/**
	 * ͨ�������ȡ�û���Ϣ
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/finduserbycode.do")
	@ResponseBody
	public String findUserByCode(String userCode) {
		UserInfo info = null;
		try {
			info = baseInfoService.getUserInfo(userCode);
			return setMap(SUCCESS, "�ɹ�", info);
		} catch (Exception e) {
			e.printStackTrace();
			return setMap(FAIL, "ʧ��", null);
		}
	}

}
