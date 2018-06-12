package com.cvte.dao.impl;

import com.cvte.entity.User;

/** 
* @author: jan 
* @date: 2018年4月23日 下午3:39:58 
*/
public interface UserDao {

	void save(User user);

	boolean checkAccount(String account);

	boolean checkPhone(String phone);

	User queryByAccountAndPwd(String account, String password);

}
