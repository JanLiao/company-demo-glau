package com.cvte.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
* @author: jan 
* @date: 2018年5月6日 下午9:31:52 
*/
public class AllInterceptor implements HandlerInterceptor {
	
	private Logger logger = Logger.getLogger(AllInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String urlStr = request.getRequestURI();
		System.out.println("拦截URL=" + urlStr);
		String[] urlDo = urlStr.split("/");
		String url = urlDo[urlDo.length - 1].split("[.]")[0];
		System.out.println("action = " + url);
		if(("loginIn").equals(url) || "index".equals(url) || "noAccount".equals(url)) {
			return true;
		}
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		if(userName == null && "".equals(userName)) {
			System.out.println("当前session为空");
			logger.info("当前session为空");
			request.getRequestDispatcher("/loginOut.do").forward(request, response);
	        //response.sendRedirect("/Glaucoma/loginOut.do");
			return false;
		}else {
			System.out.println("不为空  不拦截");
			logger.info("不为空  不拦截");
		}
		return true;
	}

}
