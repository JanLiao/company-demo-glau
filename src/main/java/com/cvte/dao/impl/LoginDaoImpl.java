package com.cvte.dao.impl;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cvte.entity.Resource;
import com.cvte.entity.Role;
import com.cvte.entity.User;

/** 
* @author: jan 
* @date: 2018年5月6日 下午3:15:26 
*/
@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

private Logger logger = Logger.getLogger(UserDaoImpl.class);
	
    @Autowired 
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public Session openSession() {
		return this.sessionFactory.openSession();
	}
	
	public void closeSession(Session session) {
		if(session != null) {
			session.close();
		}
	}

	@Override
	public User queryByAcc(String account, String password) {
		String hql = "from User where account = ? and password = ? and deleted = 0";
		@SuppressWarnings("rawtypes")
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		query.setParameter(1, password);
		User user = (User) query.uniqueResult();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getResourceByRId(String id) {
		String sql = "select r.* from role_resource rr left join resource r on"
				+ " rr.resource_id = r.id where rr.role_id = " + Integer.parseInt(id)
				+ " and r.deleted = 0";
		Session session = openSession();
		List<Resource> list = session.createNativeQuery(sql, Resource.class).getResultList();
//		List<Resource> resourceList = new ArrayList<Resource>();
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println("curr " + i + "=" + (Resource)list.get(i));
//			resourceList.add((Resource)list.get(i));
//		}
		closeSession(session);
		return list;
	}

	@Override
	public Role queryByRid(int rid) {
		String hql = "from Role where id = ? and deleted = 0";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, "" + rid);
		return (Role)query.uniqueResult();
	}

	@Override
	public void saveVisit(User user) {
		Session session = openSession();
		session.save(user);
		closeSession(session);
	}
	
}
