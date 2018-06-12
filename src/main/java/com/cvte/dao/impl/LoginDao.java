package com.cvte.dao.impl;

import java.util.List;

import com.cvte.entity.Resource;
import com.cvte.entity.Role;
import com.cvte.entity.User;

/** 
* @author: jan 
* @date: 2018年5月6日 下午3:14:57 
*/
public interface LoginDao {

	User queryByAcc(String account, String password);

	List<Resource> getResourceByRId(String id);

	Role queryByRid(int rid);

	void saveVisit(User user);

}
