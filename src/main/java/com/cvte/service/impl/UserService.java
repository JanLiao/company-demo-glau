package com.cvte.service.impl;

import com.cvte.entity.User;

/** 
* @author: jan 
* @date: 2018年4月23日 下午3:38:13 
*/
public interface UserService {

	void saveUser(User user);

	boolean checkByAccount(String account);

	boolean checkByPhone(String phone);

	User queryByAccountAndPwd(String account, String password);

}
