package com.cvte.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cvte.entity.Terminal;

/** 
* @author: jan 
* @date: 2018年6月14日 上午11:16:44 
*/
@Repository("terminalDao")
public class TerminalDaoImpl implements TerminalDao {
	private Logger logger = Logger.getLogger(ImageDaoImpl.class);

	@Autowired  
    private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    }
	
	private Session openSession() {  
        return this.sessionFactory.openSession();  
    } 
	
	//关闭Session
    private void closeSession(Session session){
        if(null != session){
            session.close();
        }
    }

	@SuppressWarnings("rawtypes")
	@Override
	public Terminal queryByAccount(String account, String psw) {
		String hql = "from Terminal where tid = ? and password = ? and deleted = 0";
		Session session = openSession();
		Query query = session.createQuery(hql)
				.setParameter(0, account)
				.setParameter(1, psw);
		Terminal terminal = (Terminal) query.uniqueResult();
		if(session != null) {
			closeSession(session);
		}
		return terminal;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Terminal queryByAccountPassword(String account, String psw) {
		String hql = "from Terminal where tid = ? and password = ? and deleted = 0";
		Session session = openSession();
		Query query = openSession().createQuery(hql)
				.setParameter(0, account)
				.setParameter(1, psw);
		Terminal terminal = (Terminal) query.uniqueResult();
		if(session != null) {
			closeSession(session);
		}
		return terminal;
	}
}
