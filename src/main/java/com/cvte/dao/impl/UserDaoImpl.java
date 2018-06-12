package com.cvte.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cvte.entity.User;

/** 
* @author: jan 
* @date: 2018年4月23日 下午3:40:22 
*/
@Repository("userDao")
public class UserDaoImpl implements UserDao {

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
	public void save(User user) {
		logger.info("user=" + user);
		System.out.println("user=" + user);
		this.getCurrentSession().save(user);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkAccount(String account) {
		String hql = "from User where account = ? ";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		User user = (User) query.uniqueResult();
		if(user == null) {
			return false;
		}else {
			logger.info("checkAccount user = " + user);
			System.out.println("checkAccount user = " + user);
			return true;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkPhone(String phone) {
		String hql = "from User where phone = ?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, phone);
		User user = (User) query.uniqueResult();
		if(user == null) {
			return false;
		}else {
			logger.info("checkPhone user = " + user);
			System.out.println("checkPhone user = " + user);
			return true;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public User queryByAccountAndPwd(String account, String password) {
		String hql1 = "from User where account = ? and password = ?";
		Query query = this.getCurrentSession().createQuery(hql1);
		query.setParameter(0, account);
		query.setParameter(1, password);
		User user1 = (User)query.uniqueResult();
		String hql2 = "from User where phone = ? and password = ?";
		Query query2 = this.getCurrentSession().createQuery(hql2);
		query2.setParameter(0, account);
		query2.setParameter(1, password);
		User user2 = (User)query2.uniqueResult();
		if(null != user1) {
			return user1;
		}
		if(null != user2) {
			return user2;
		}
		return null;
	}
	
}
