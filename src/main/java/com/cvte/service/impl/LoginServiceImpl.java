package com.cvte.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvte.dao.impl.LoginDao;
import com.cvte.entity.Resource;
import com.cvte.entity.Role;
import com.cvte.entity.Terminal;
import com.cvte.entity.User;

/** 
* @author: jan 
* @date: 2018年5月6日 下午3:14:13 
*/
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public User queryByAcc(String account, String password) {
		return loginDao.queryByAcc(account, password);
	}

	@Override
	public Map<String, List<Resource>> getUserResource(String id) {
		List<Resource> list = loginDao.getResourceByRId(id);
		System.out.println("resource list size = " + list.size());
		Set<String> set = new HashSet<String>();
		for(Resource r : list) {
			set.add(r.getParentName());
		}
		Map<String, List<Resource>> map = new HashMap<String, List<Resource>>();
		for(String s : set) {
			List<Resource> resourceList = new ArrayList<Resource>();
			for(Resource r : list) {
				if(s.equals(r.getParentName())) {
					resourceList.add(r);
				}
			}
			map.put(s, resourceList);
		}
		System.out.println("resource map = " + map);
		return map;
	}

	@Override
	public Role queryById(int rid) {
		return loginDao.queryByRid(rid);
	}

	@Override
	public void saveVisit(User user) {
		loginDao.saveVisit(user);
	}

	@Override
	public Terminal queryByAccountPassword(String account, String password) {
		return loginDao.queryByAccountPassword(account, password);
	}

}
