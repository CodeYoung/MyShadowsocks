/**
 * 
 */
package com.young.shadowsocks.controller.business;




import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

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
	 * 通过编码获取用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/finduserbycode.do")
	//@ResponseBody
	public ModelAndView findUserByCode(String code) {
//		UserInfo info=new UserInfo();
		ModelAndView modelAndView=new ModelAndView();
		try {
			System.out.println("test");
			modelAndView.setViewName("register");
		
//			 info = baseInfoService.getUserInfo(code);
//			return setMap(SUCCESS, "成功", info);
		} catch (Exception e) {
			e.printStackTrace();

//			return setMap(FAIL, "失败", null);
		}
		return modelAndView;
	}
	
	/**
	 * 新增用户信息
	 */
	@RequestMapping("/insertuser.do")
	@ResponseBody
	public String insertUser(String code,String name,String pwd,String phone,String email) {
		UserInfo info=new UserInfo();
		try {
			info.setCode(code);
			info.setName(name);
			info.setPwd(pwd);
			info.setPhone(phone);
			info.setEmail(email);
			info.setCreatetime(new Date());
			info.setServerip("150.95.136.191");
			info.setServerpwd("liyang19901120");
			 baseInfoService.insertUserInfo(info);
			return setMap(SUCCESS, "新增成功", info);
		} catch (Exception e) {
			e.printStackTrace();
			return setMap(FAIL, "新增失败", null);
		}
	}
	
	
	@RequestMapping("/deleteuser.do")
	@ResponseBody
	public String deleteUser(String code) {
		UserInfo info=new UserInfo();
		try {
			
			info= baseInfoService.getUserInfo(code);
			baseInfoService.deleteUserInfo(info);
			return setMap(SUCCESS, "删除成功", info);
		} catch (Exception e) {
			e.printStackTrace();
			return setMap(FAIL, "删除失败", null);
		}
	}
	
	@RequestMapping("/checkWX.do")
	@ResponseBody
	public String CheckWX(String signature,String timestamp,String nonce,String echostr) {
//		UserInfo info=new UserInfo();
//		try {
//			
//			info= baseInfoService.getUserInfo(code);
//			baseInfoService.deleteUserInfo(info);
//			return setMap(SUCCESS, "删除成功", info);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return setMap(FAIL, "删除失败", null);
//		}
		return echostr;
	}
	

}
