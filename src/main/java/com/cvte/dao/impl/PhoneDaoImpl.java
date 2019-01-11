package com.cvte.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cvte.cons.Constant;
import com.cvte.entity.Account;
import com.cvte.entity.AppImage;
import com.cvte.entity.AppImgResult;
import com.cvte.entity.Appoint;
import com.cvte.entity.HospitalDescBean;
import com.cvte.entity.ImageResult;
import com.cvte.entity.Message;
import com.cvte.entity.Person;
import com.cvte.entity.Report;
import com.cvte.entity.Terminal;

/**
* @author jan
* @data 2018年8月4日 上午11:43:38
*/
@Repository("phoneDao")
public class PhoneDaoImpl implements PhoneDao {

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HospitalDescBean> queryHospital() {
		String hql = "from HospitalDescBean where deleted = 0";
		Query query = this.getCurrentSession().createQuery(hql);
		List<HospitalDescBean> list = query.list();
		return list;
	}

	@Override
	public Account queryAccount(String account, String password) {
		String hql = "from Account where account = ? and password = ? and deleted = 0";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		query.setParameter(1, password);
		Account act = (Account) query.uniqueResult();
		
		if(act == null) {
			String hql1 = "from Account where phone = ? and password = ? and deleted = 0";
			Query query1 = this.getCurrentSession().createQuery(hql1);
			query.setParameter(0, account);
			query.setParameter(1, password);
			act = (Account) query.uniqueResult();
		}
		if(act != null) {
			String token = "" + System.currentTimeMillis();
			act.setToken(token);
			updateAccount(act);
		}
		return act;
	}
	
	@Override
	public Account queryByAccount(String account) {
		String hql = "from Account where account = ? and deleted = 0";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		Account act = (Account) query.uniqueResult();
		return act;
	}
	
	public void updateAccount(Account act) {
//		String hql = "update Account set token = ? where id = ?";
//		Query query = this.getCurrentSession().createQuery(hql);
//		query.setParameter(0, act.getToken());
//		query.setParameter(1, act.getId());
//		query.executeUpdate();
		Session session = openSession();
		session.beginTransaction();
		session.update(act);
		session.getTransaction().commit();
		if(session != null) {
			closeSession(session);
		}
	}

	@Override
	public int saveAppImage(String imgPath, String imgName) {
		AppImage img = new AppImage();
		String[] str = imgName.split("[.]")[0].split("_");
		if("L".equals(str[1])) {
			img.setIsL(1);
			img.setIsR(0);
		}else if("R".equals(str[1])) {
			img.setIsL(0);
			img.setIsR(1);
		}else {
			img.setIsL(0);
			img.setIsR(0);
		}
		
		img.setImgName(imgName);
		Account act = queryByAccount(str[0]);
		img.setAccountId("" + act.getId());
		String path = imgPath.substring(imgPath.indexOf("appImg"));
		img.setImgPath(path);
		img.setReceiveTime(new Date());
		
		Session session = openSession();
		session.save(img);
		if(session != null) {
			closeSession(session);
		}
		return Integer.parseInt(img.getImgId());
	}
	
	@Override
	public void saveAppImgResult(AppImgResult result) {
		Session session = openSession();
		session.save(result);
		if(session != null) {
			closeSession(session);
		}
	}
	
	@Override
	public void saveReportUpload(Report report) {
		Session session = openSession();
		session.save(report);
		if(session != null) {
			closeSession(session);
		}
	}
	
	@Override
	public void saveReport(Report report) {
		Appoint tmp = getAppoint();
		String appointNum = "";
		if(tmp == null) {
			appointNum = "" + Constant.APPOINT_NUM;
		}else {
			appointNum = "" + (Integer.parseInt(tmp.getAppointNum()) + 1);
		}
		report.setAppointNum(appointNum);
		Session session = openSession();
		session.save(report);
		if(session != null) {
			closeSession(session);
		}
	}

	@Override
	public List<Appoint> getAllAppoint(String account) {
		Session session = openSession();
        try {
        	String sql = "select a.* from appoint a left join account act on a.account_id = act.id where a.deleted = 0 and act.account = :account";
        	return session.createNativeQuery(sql, Appoint.class).setParameter("account", account).getResultList();
        } finally {
            closeSession(session);
        }
	}
	
	@Override
	public List<Report> getAllReport(String account) {
		Session session = openSession();
        try {
        	String sql = "select a.* from report a left join account act on a.account_id = act.id where act.account = :account";
        	return session.createNativeQuery(sql, Report.class).setParameter("account", account).getResultList();
        } finally {
            closeSession(session);
        }
	}
	
	private Appoint getAppoint() {
		Session session = openSession();
		try {
			String sql = "SELECT * FROM appoint ORDER BY id DESC LIMIT 0,1";
			Appoint appoint = session.createNativeQuery(sql, Appoint.class).uniqueResult();
			return appoint;
		}finally {
			if(session != null) {
				closeSession(session);
			}
		}
		
	}

	@Override
	public boolean saveAppoint(String serverTime, String serverType, String account) {
		Account act = queryByAccount(account);
		Appoint tmp = getAppoint();
		String appointNum = "";
		if(tmp == null) {
			appointNum = "" + Constant.APPOINT_NUM;
		}else {
			appointNum = "" + (Integer.parseInt(tmp.getAppointNum()) + 1);
		}
		Appoint appoint = new Appoint();
		String[] str = serverType.split(",");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		appoint.setAccountId(act.getId());
		appoint.setAppointNum(appointNum);
		appoint.setGlaucoma(Integer.parseInt(str[0]));
		appoint.setAmd(Integer.parseInt(str[1]));
		appoint.setDr(Integer.parseInt(str[2]));
		appoint.setPm(Integer.parseInt(str[3]));
		appoint.setHospitalId(Integer.parseInt(str[4]));
		appoint.setDeleted(0);
		try {
			appoint.setSaveTime(format.parse(serverTime));
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		Session session = openSession();
		session.save(appoint);
		if(session != null) {
			closeSession(session);
		}
		return true;
	}

	@Override
	public Report getReport(String reportId) {
		String hql = "from Report where id = ?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, Integer.parseInt(reportId));
		Report report = (Report) query.uniqueResult();
		return report;
	}

	@Override
	public AppImage getAppImage(int id) {
		String hql = "from AppImage where imgId = " + id;
		Query query = this.getCurrentSession().createQuery(hql);
		AppImage img = (AppImage) query.uniqueResult();
		return img;
	}

	@Override
	public AppImgResult getAppImgResult(int getlImgId) {
		Session session = openSession();
        try {
        	String sql = "select a.* from app_imgresult a left join app_image act on a.app_img_id = act.img_id where act.img_id = " + getlImgId;
        	return session.createNativeQuery(sql, AppImgResult.class).uniqueResult();
        } finally {
            closeSession(session);
        }
	}
	
	@Override
	public void saveMessage(Message message) {
		Session session = openSession();
		session.save(message);
		if(session != null) {
			closeSession(session);
		}
	}
	
	@Override
	public List<Message> getAllMessage(String account){
		Session session = openSession();
        try {
        	String sql = "select a.* from message a left join account act on a.account_id = act.id where act.account = :account";
        	return session.createNativeQuery(sql, Message.class).setParameter("account", account).getResultList();
        } finally {
            closeSession(session);
        }
	}
	
	@Override
	public Terminal getTerminal(int terminalId) {
		String hql = "from Terminal where deleted = 0 and terminalId = " + terminalId;
		Query query = this.getCurrentSession().createQuery(hql);
		return (Terminal) query.uniqueResult();
	}

	@Override
	public Account queryOnlyAccount(String account) {
		String hql = "from Account where account = ?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		return (Account) query.uniqueResult();
	}

	@Override
	public Account queryAccountByPhone(String phone) {
		String hql = "from Account where phone = ?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, phone);
		return (Account) query.uniqueResult();
	}

	@Override
	public void registerAccount(String account, String password, String phone, int terminalId) {
		Session session = openSession();
		Account act = new Account();
		if(!"".equals(account)) {
			act.setAccount(account);
		}
		act.setDeleted(0);
		act.setTerminalId(terminalId);
		act.setRegisterTime(new Date());
		act.setPassword(password);
		if(!"".equals(phone)) {
			act.setPhone(phone);
		}
		session.save(act);
		if(session != null) {
			closeSession(session);
		}
	}

	@Override
	public int saveTerminal() {
		Session session = openSession();
		Terminal t = new Terminal();
		try {			
			String sql = "SELECT * FROM terminal ORDER BY terminal_id DESC LIMIT 0,1";
			Terminal terminal = session.createNativeQuery(sql, Terminal.class).uniqueResult();
			String tid = terminal.getTid();
			
			t.setDeleted(0);
			t.setRegistTime(new Date());
			t.setTid("" + (Integer.parseInt(tid) + 1));
			String password = generatePsw();
			t.setPassword(password);
			session.save(t);
		}finally {
            closeSession(session);
        }
		return t.getTerminalId();
	}
	
	public String generatePsw() {
		StringBuffer buffer = new StringBuffer("");
		Random random = new Random();
		for(int i = 0; i < 6; i++) {
			int tmp = random.nextInt(36);
			if(tmp <10) {
				buffer.append(tmp);
			}else {
				char c = (char) (tmp + 87);
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

	@Override
	public void updateAccount(String account, String sex, String age) {
		String hql = "from Account where account = ?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		Account act = (Account) query.uniqueResult();
		act.setSex(sex);
		act.setAgeRange(age);
		
		updateAccount(act);
	}

	@Override
	public Person getPerson(String appointNum, int getlImgId, int getrImgId) {
		String hql = "";
		Query query = null;
		if(getlImgId == 0) {
			hql = "from Person where uid = ?  and rid = ?";
			query = this.getCurrentSession().createQuery(hql);
			query.setParameter(0, appointNum);
			query.setParameter(1, "" + getrImgId);
		}else {
			if(getrImgId == 0) {
				hql = "from Person where uid = ? and lid = ?";
				query = this.getCurrentSession().createQuery(hql);
				query.setParameter(0, appointNum);
				query.setParameter(1, "" + getlImgId);
			}else {
				hql = "from Person where uid = ? and lid = ? and rid = ?";
				query = this.getCurrentSession().createQuery(hql);
				query.setParameter(0, appointNum);
				query.setParameter(1, "" + getlImgId);
				query.setParameter(2, "" + getrImgId);
			}
		}
		System.out.println("hql = " + hql);
		Person person = (Person) query.uniqueResult();
		
		return person;
	}

	@Override
	public AppImgResult getImgResult(int imgId) {
		String hql = "from ImageResult where imgId = " + imgId;
		ImageResult img = (ImageResult) this.getCurrentSession().createQuery(hql).uniqueResult();
		AppImgResult result = new AppImgResult();
		result.setAmdRisk(img.getAmdRisk());
		result.setCdr(img.getCdr());
		result.setCupPath(img.getCupPath());
		result.setDrRisk(img.getDrRisk());
		result.setFullPath(img.getFullPath());
		result.setGlaucomaRisk(img.getGlaucomaRisk());
		result.setPmRisk(img.getPmRisk());
		result.setImgUrl(img.getImgUrl());
		result.setImgName(img.getImgName());
		result.setQulity(img.getQulity());
		result.setOd(img.getOd());
		result.setOc(img.getOc());
		return result;
	}
}
