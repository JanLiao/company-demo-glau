package com.cvte.interceptor;

import java.util.Enumeration;

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
		printRequest(request);
		String[] urlDo = urlStr.split("/");
		String url = urlDo[urlDo.length - 1].split("[.]")[0];
		System.out.println("action = " + url);
		if(("loginIn").equals(url) || "index".equals(url) || "noAccount".equals(url)) {
			return true;
		}
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		if(userName == null && "".equals(userName)) {   // 该处还未处理
			System.out.println("当前session为空");
			logger.info("当前session为空");
			request.getRequestDispatcher("/loginOut.do").forward(request, response);
	        //response.sendRedirect("/Glaucoma/loginOut.do");
			return false;
		}else {
			System.out.println("userName = " + userName);
			System.out.println("不为空  不拦截");
			logger.info("不为空  不拦截");
		}
		return true;
	}

	private void printRequest(HttpServletRequest req) {
		//获取请求方式
        System.out.println(req.getMethod());
        //获取项目名称
        System.out.println(req.getContextPath());
        //获取完整请求路径
        System.out.println(req.getRequestURL());
        //获取除了域名外的请求数据
        System.out.println(req.getRequestURI());
        //获取请求参数
        System.out.println(req.getQueryString());
        System.out.println("----------------------------------------------------------");
        //获取请求头
        String header = req.getHeader("user-Agent");
        System.out.println(header);
        header = header.toLowerCase();
        //根据请求头数据判断浏览器类型
        if(header.contains("chrome")){
            System.out.println("您的访问浏览器为谷歌浏览器");
        }else if(header.contains("msie")){
            System.out.println("您的访问浏览器为IE浏览器");
        }else if(header.contains("firefox")){
            System.out.println("您的访问浏览器为火狐浏览器");
        }else{
            System.out.println("您的访问浏览器为其它浏览器");
        }
        System.out.println("----------------------------------------------------------");
        //获取所有的消息头名称
        Enumeration<String> headerNames = req.getHeaderNames();
        //获取获取的消息头名称，获取对应的值，并输出
        while(headerNames.hasMoreElements()){
            String nextElement = headerNames.nextElement();
            System.out.println(nextElement+":"+req.getHeader(nextElement));
        }
        System.out.println("----------------------------------------------------------");
        //根据名称获取此重名的所有数据
        Enumeration<String> headers = req.getHeaders("accept");
        while (headers.hasMoreElements()) {
            String string = (String) headers.nextElement();
            System.out.println(string);
        }
	}

}
