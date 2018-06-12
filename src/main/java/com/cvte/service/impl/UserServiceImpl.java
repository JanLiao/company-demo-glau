package com.cvte.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvte.dao.impl.UserDao;
import com.cvte.entity.User;

/** 
* @author: jan 
* @date: 2018年4月23日 下午3:38:26 
*/
@Service("userService")
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		logger.info("save service");
		userDao.save(user);
	}

	@Override
	public boolean checkByAccount(String account) {
		logger.info("check account");
		return userDao.checkAccount(account);
	}

	@Override
	public boolean checkByPhone(String phone) {
		logger.info("check phone");
		return userDao.checkPhone(phone);
	}

	@Override
	public User queryByAccountAndPwd(String account, String password) {
		logger.info(account + "=" + password);
		return userDao.queryByAccountAndPwd(account, password);
	}
	
}
