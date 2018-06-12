package com.cvte.service.impl;

import java.util.List;
import java.util.Map;

import com.cvte.entity.Resource;
import com.cvte.entity.Role;
import com.cvte.entity.User;

/** 
* @author: jan 
* @date: 2018年5月6日 下午3:13:49 
*/
public interface LoginService {

	User queryByAcc(String account, String password);

	Map<String, List<Resource>> getUserResource(String id);

	Role queryById(int i);

	void saveVisit(User user);

}
