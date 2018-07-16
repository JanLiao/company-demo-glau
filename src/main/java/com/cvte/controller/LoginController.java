package com.cvte.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cvte.dao.impl.TerminalDao;
import com.cvte.entity.Resource;
import com.cvte.entity.Role;
import com.cvte.entity.Terminal;
import com.cvte.entity.User;
import com.cvte.msg.Result;
import com.cvte.service.impl.LoginService;

/** 
* @author: jan 
* @date: 2018年5月6日 下午3:11:41 
*/
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="terminalVal")
	public void terminalValidate(String account, String password, 
			HttpServletResponse response) {
		String msg = "";
		if("".equals(account) || "".equals(password)) {
			msg = "0,账号或密码不能为空";
		}
		else {
			Terminal terminal = loginService.queryByAccountPassword(account, password);
			if(terminal != null) {
				msg = "1,验证成功";
			}
			else {
				msg = "0,账号不存在或密码错误";
			}
		}
		
		// 清空response
        response.reset();
        response.setContentType("application/octet-stream");
        try {
			response.setHeader("Content-Disposition", "attachment;msg=" + new String(msg.getBytes("UTF-8"),"ISO-8859-1"));
        	//response.setHeader("Content-Disposition", "attachment;msg=" + new String(msg.getBytes("UTF-8"),"UTF-8"));
        	//response.setHeader("Content-Disposition", "attachment;msg=" + new String(msg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="loginIn")
	@ResponseBody
	public Result<String> loginIn(String account, String password, HttpSession session){
		System.out.println(account + "=" + password);
		Result<String> result = new Result<String>();
		if("".equals(account) || "".equals(password)) {
			result.setFail(1);
			result.setMsg("账号或密码为空");
			return result;
		}else {
			User user = loginService.queryByAcc(account, password);
			if(user == null) {
				result.setFail(1);
				result.setMsg("账号或密码错误,请重新输入");
				return result;
			}else {
				System.out.println("user=" + user);
				Map<String, List<Resource>> map = loginService.getUserResource(user.getRole().getId());
				if(session.getAttribute("resourceMap") != null) {
					session.removeAttribute("resourceMap");
				}
				if(session.getAttribute("userName") != null) {
					session.removeAttribute("userName");
				}
				session.setAttribute("resourceMap", map);
				session.setAttribute("userName", account);
				result.setFail(0);
				result.setMsg("登录成功");
				System.out.println("result = " + result);
				return result;
			}
		}
	}
	
	@RequestMapping(value="noAccount")
	public String noAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		User user = new User();
		String timeStr = "" + System.currentTimeMillis();
		user.setAccount("访客" + timeStr);
		user.setDeleted(0);
		user.setLoginTime(new Date());
		user.setRegisterTime(new Date());
		user.setPassword("1");
		Role role = loginService.queryById(2);
		user.setRole(role);
		loginService.saveVisit(user);
		Map<String, List<Resource>> map = loginService.getUserResource(role.getId());
		if(session.getAttribute("resourceMap") != null) {
			session.removeAttribute("resourceMap");
		}
		if(session.getAttribute("userName") != null) {
			session.removeAttribute("userName");
		}
		session.setAttribute("resourceMap", map);
		session.setAttribute("userName", "访客" + timeStr);
		return "index";
	}
	
	@RequestMapping(value = "/loginOut")
	@ResponseBody
	public String loginOut(HttpSession session) {
		session.invalidate(); // 是让当前浏览器的session销毁,也就是一个session被销毁，比如用户登录后注销就用这个，因为注销意味着该用户session中的所有属性均失效。
		return "true";
	}
	
	@RequestMapping(value="/loginCheck")
	public void loginCheck(HttpServletRequest request, HttpServletResponse response
			, HttpSession session) throws IOException {
		String userName = (String)session.getAttribute("userName");
		PrintWriter pw = response.getWriter();
		if(userName == null || "".equals(userName)) {
			pw.print("false");
			pw.flush();
			pw.close();
		}else {
			pw.print("true");
			pw.flush();
			pw.close();
		}
	}
	
}
