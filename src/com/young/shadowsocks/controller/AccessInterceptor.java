/**
 * 
 */
package com.young.shadowsocks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 *
 */
public class AccessInterceptor implements HandlerInterceptor {

	// 请求结束时调用
		@Override
		public void afterCompletion(HttpServletRequest arg0,
				HttpServletResponse arg1, Object arg2, Exception arg3)
				throws Exception {
			try {
			} catch (Exception e) {
				// TODO: handle exception
			}
		

		}

		// 在执行完Controller方法后调用
		@Override
		public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
				Object arg2, ModelAndView arg3) throws Exception {
			
		}

		// 执行Controller方法前调用
		@Override
		public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object obj) throws Exception {
			System.out.println(request.getRequestURI());
			return true;
		}

}
