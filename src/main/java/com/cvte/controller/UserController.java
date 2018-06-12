package com.cvte.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cvte.entity.User;
import com.cvte.msg.UserResultMsg;
import com.cvte.service.impl.UserService;

/** 
* @author: jan 
* @date: 2018年4月23日 下午3:35:32 
*/
@Controller
public class UserController {
	
	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String account, String password, String versionCode){
		System.out.println(account + "=" + password + "=" + versionCode);
		logger.info("用户登录----" + account + "=" + password + "=" + versionCode);
		//UserResultMsg<String> result = new UserResultMsg<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!"".equals(account) && !"".equals(password)) {
			User user = userService.queryByAccountAndPwd(account, password);
			if(null != user) {
				if(user.getDeleted() == 1) {
					map.put("code", 1);
					map.put("msg", "该账户已被禁用");
					return map;
				}else {
					map.put("code", 0);
					map.put("msg", "登录成功");
					return map;
				}
			}else {
				map.put("code", 1);
				map.put("msg", "账号或密码错误,重新输入");
				return map;
			}
			//return result;
		}else {
			System.out.println("用户名或密码为空");
			logger.info("用户名或密码为空");
			map.put("code", 1);
			map.put("msg", "用户名或密码为空");
			return map;
		}
	}
	
	@RequestMapping("register")
	@ResponseBody
	public UserResultMsg<String> register(String account, String password, String phone,
			String sex, String email, String versionCode) {
		UserResultMsg<String> result = new UserResultMsg<String>();
		System.out.println(account + "=" + password + "=" + phone + "=" + email);
		logger.info("用户注册=" + account + "=" + password + "=" + phone + "=" + email);
		if("".equals(account) || "".equals(password)) {
			result.setCode(0);
			result.setMsg("账号或密码为空");
			return result;
		}else {
			//先check该账号或手机是否被注册
			if(userService.checkByAccount(account)) {
				result.setCode(0);
				result.setMsg("该账号已被注册,重新输入账号");
				return result;
			}else {
				if(userService.checkByPhone(phone)) {
					result.setCode(0);
					result.setMsg("该手机号已被注册,重新输入手机号");
					return result;
				}else {
					User user = new User(account, password, sex, phone, email, 0);
					List<String> list = new ArrayList<String>();
					logger.info("APP注册账号");
					System.out.println("app 注册");
					userService.saveUser(user);
					result.setCode(1);
					result.setMsg("注册成功");
					list.add(account);
					result.setData(list);
					return result;
				}
			}
		}
	}
	
}
