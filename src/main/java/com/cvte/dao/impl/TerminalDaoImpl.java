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

	@Override
	public Terminal queryByAccount(String account, String psw) {
		String hql = "from Terminal where tid = ? and password = ? and deleted = 0";
		Query query = this.getCurrentSession().createQuery(hql)
				.setParameter(0, account)
				.setParameter(1, psw);
		Terminal terminal = (Terminal) query.uniqueResult();
		return terminal;
	}
    
    
}
