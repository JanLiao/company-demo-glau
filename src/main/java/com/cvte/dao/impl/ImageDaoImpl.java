package com.cvte.dao.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cvte.cons.Constant;
import com.cvte.dto.QulityDto;
import com.cvte.entity.Image;
import com.cvte.entity.ImageResult;
import com.cvte.entity.Person;
import com.cvte.entity.RiskDto;
import com.cvte.msg.Result;
import com.cvte.netty.msg.ImgInfo;

/** 
* @author: jan 
* @date: 2018年4月9日 下午6:36:58 
*/
@Repository("imgDao")
public class ImageDaoImpl implements ImageDao {
	
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
	public String saveImage(String fileName, String path) {   //保存image
		logger.info("保存mysql");
		String[] num = fileName.split(",");
		String imgPath = "img/" + num[0] + "/" + path + "/" + num[1];
		String[] str = num[1].split("_");
		if(str.length == 1) {  //手持机     num[1] - uid  未确定  18-07-05 UID已确定
			Image img = new Image(num[1], num[0], num[2], imgPath, 1, new Timestamp(new Date().getTime()));
			logger.info("current session=" + openSession());
			logger.info("save img=" + img);
			Session session =openSession();
			session.save(img);
			closeSession(session);
			logger.info("save success!!!");
			logger.info("save after img=" + img);
			return img.getImgId();
		}else {  //SY机
			//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Image img = new Image(num[1], num[0], num[1].split("_")[1], imgPath, 0, new Timestamp(new Date().getTime()));
			logger.info("current session=" + openSession());
			logger.info("save img=" + img);
			Session session =openSession();
			session.save(img);
			closeSession(session);
			logger.info("save success!!!");
			logger.info("save after img=" + img);
			return img.getImgId();
		}
	}

	@Override
	public void saveResult(ImgInfo img, String imgId, String imgName) {
		int glau = Integer.parseInt(img.getGlaucoma().split("%")[0]);
		int dr = Integer.parseInt(img.getDr().split("%")[0]);
		int myopia = Integer.parseInt(img.getMyopia().split("%")[0]);
		int amd = Integer.parseInt(img.getAmd().split("%")[0]);
		double cdr = Double.parseDouble(img.getCdr());
		SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = forma.parse(forma.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ImageResult imgResult = new ImageResult(imgId, img.getPid(), imgName, img.getOriginalImg(), img.getQulity()
				, glau, dr, myopia, amd, cdr, img.getOd()
				, img.getOc(), img.getZoomImg(), img.getSegmentedImg(), date);
		Session session = openSession();
		session.save(imgResult);
		closeSession(session);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void savePerson(Person person,String lr, String pdfPath, int flag) {
		
		Session session = openSession();
		String hql = "from Person where tid=? and uid=? and pdfTime=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, person.getTid());
		query.setParameter(1, person.getUid());
		query.setParameter(2, person.getPdfTime());
		logger.info("ready to save person=" + person);
		logger.info("lr===pdfPath===flag==" + lr + ",=" + pdfPath + ",=" + flag);
		if(flag == 1) {
			person.setLrpdf(pdfPath);
			session.save(person);
		}else if(flag == 2) {
			Person p = (Person) query.uniqueResult();
			logger.info("出现同数据person=" + p);
			if("L".equals(lr)) {
				p.setLid(person.getLid());
				Transaction tx = session.beginTransaction(); 
				session.update(p);
				tx.commit(); 
			}else {
				p.setRid(person.getRid());
				Transaction tx = session.beginTransaction(); 
				session.update(p);
				tx.commit(); 
			}
		}else {
			Person p = (Person) query.uniqueResult();
			logger.info("当前个人数据齐全person=" + p);
			if("L".equals(lr)) {
				logger.info("左眼");
				p.setLid(person.getLid());
				p.setLrpdf(pdfPath);
				logger.info("update person=" + p);
				Transaction tx = session.beginTransaction(); 
				session.update(p);
				tx.commit(); 
			}else {
				logger.info("右眼");
				p.setRid(person.getRid());
				p.setLrpdf(pdfPath);
				logger.info("update person=" + p);
				Transaction tx = session.beginTransaction(); 
				session.update(p);
				tx.commit(); 
			}
		}
		
		
		closeSession(session);
	}

	@Override
	public void testDao() {
		System.out.println("nothing to do");
		Image img = new Image("1.jpg", "1100", "11", "home/", new Date());
		this.getCurrentSession().save(img);
		System.out.println("success");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Person> listAllPerson() {
		String hql = "from Person";
		Query query = this.getCurrentSession().createQuery(hql);
		List<Person> list = query.list();
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ImageResult> getAllImageResult(String hql, int page, int limit) {
		Query query = this.getCurrentSession().createQuery(hql);
		List<ImageResult> list = query.list();
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Person queryByTidUid(String tidU) {
		String[] str = tidU.split(",");
		String hql = "from Person where tid=? and uid=?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, str[0]);
		query.setParameter(1, str[1]);
		Person p = (Person) query.uniqueResult();
		return p;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ImageResult queryById(String rid) {
		if("".equals(rid) || rid == null) {
			ImageResult img = new ImageResult();
			return img;
		}else {
			String hql = "from ImageResult where imgId=?";
			Query query = this.getCurrentSession().createQuery(hql);
			query.setParameter(0, rid);
			ImageResult result = (ImageResult) query.uniqueResult();
			return result;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ImageResult> getAllImageResult(String hql, String dateTime) {
		String[] str = dateTime.split(" ");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		try {
			start = format.parse(str[0].trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date end = null;
		try {
			end = format.parse(str[2].trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//方式一
		hql = hql + " and saveDate  between ? and ?";
		java.sql.Date startDate = new java.sql.Date(start.getTime());
		java.sql.Date endDate = new java.sql.Date(end.getTime());
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, startDate);
		query.setParameter(1, endDate);
		
		//方式二   该种方式已被弃用
//		java.sql.Date startDate = new java.sql.Date(start.getTime());
//		java.sql.Date endDate = new java.sql.Date(end.getTime());
//		hql = hql + " and saveDate >=:start and saveDate <=:end ";
//		Query query = this.getCurrentSession().createQuery(hql);
//		query.setDate("start", startDate);
//		query.setDate("end", endDate);
		List<ImageResult> list = query.list();
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Person queryByPid(String pid) {
		String hql = "from Person where pid=?";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter(0, pid);
		Person p = (Person) query.uniqueResult();
		return p;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Result<ImageResult> getPageResult(String hql, String dateTime, int page, int limit) {
		if("".equals(dateTime)) {
			System.out.println("hql=" + hql);
			String hqlNew = "select count(1) " + hql;
			Query query = this.getCurrentSession().createQuery(hqlNew);
			Long tmp = (Long) query.uniqueResult();
			int total = Integer.parseInt("" + tmp);
//			Query query = this.getCurrentSession().createQuery(hql);
//			List<ImageResult> list = query.list();
//			int totalRecord = list.size();
			
			Query query1 = this.getCurrentSession().createQuery(hql);
			query1.setFirstResult((page - 1) * limit);
			query1.setMaxResults(limit);
			List<ImageResult> list = query1.list();
			Result<ImageResult> result = new Result<ImageResult>();
			result.setCount(total);
			result.setFail(0);
			result.setList(list);
			return result;
		}else {
			String[] str = dateTime.split(" ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = null;
			try {
				start = format.parse(str[0].trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date end = null;
			try {
				end = format.parse(str[2].trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//方式一
			hql = hql + " and saveDate  between ? and ?";
			java.sql.Date startDate = new java.sql.Date(start.getTime());
			java.sql.Date endDate = new java.sql.Date(end.getTime());
			String hqlNew = "select count(1) " + hql;
			Query query = this.getCurrentSession().createQuery(hqlNew);
			query.setParameter(0, startDate);
			query.setParameter(1, endDate);
			int total = (int) query.uniqueResult();
			
			Query query1 = this.getCurrentSession().createQuery(hql);
			query1.setParameter(0, startDate);
			query1.setParameter(1, endDate);
			query1.setFirstResult((page - 1)*limit);
			query1.setMaxResults(limit);
			List<ImageResult> list = query1.list();
			Result<ImageResult> result = new Result<ImageResult>();
			result.setCount(total);
			result.setFail(0);
			result.setList(list);
			return result;
		}
	}

	@Override
	public RiskDto queryRisk(int flag, String sex) {
		RiskDto dto = null;
		if(Integer.parseInt(sex) == Constant.NoSex) {
			dto = getRiskDto(flag);
		}else if(Integer.parseInt(sex) == Constant.Man) {
			dto = getRiskDto(flag, "男");
		}else if(Integer.parseInt(sex) == Constant.Woman) {
			dto = getRiskDto(flag, "女");
		}
		return dto;
	}

	private RiskDto getRiskDto(int flag, String sex) {
		RiskDto dto = new RiskDto();
		List<String> list = new ArrayList<String>();
		String sql = "select count(1) from imgresult ir left join person p on "
				  + " ir.img_id = p.lid or ir.img_id = p.rid " + " where sex = " + "'" + sex + "'";
		if(flag == 0) {
			Session session = openSession();
			for(int i = 0; i < 10; i++) {
				String tmp = sql + " and glaucoma_risk >= " + i*10
						+ " and glaucoma_risk < " + (i + 1)*10;
				int total =  Integer.parseInt("" + session.createNativeQuery(tmp).uniqueResult());
				System.out.println(i + "=" + total);
				list.add("" + total);
			}
			dto.setName("青光眼风险");
			closeSession(session);
		}else if(flag == 1) {
			Session session = openSession();
			for(int i = 0; i < 10; i++) {
				String tmp = sql + " and amd_risk >= " + i*10
						+ " and amd_risk < " + (i + 1)*10;
				int total =  Integer.parseInt("" + session.createNativeQuery(tmp).uniqueResult());
				System.out.println(i + "=" + total);
				list.add("" + total);
			}
			dto.setName("黄斑病风险");
			closeSession(session);
		}else if(flag == 2) {
			Session session = openSession();
			for(int i = 0; i < 10; i++) {
				String tmp = sql + " and dr_risk >= " + i*10
						+ " and dr_risk < " + (i + 1)*10;
				int total =  Integer.parseInt("" + session.createNativeQuery(tmp).uniqueResult());
				System.out.println(i + "=" + total);
				list.add("" + total);
			}
			dto.setName("糖网病风险");
			closeSession(session);
		}else if(flag == 3) {
			Session session = openSession();
			for(int i = 0; i < 10; i++) {
				String tmp = sql + " and pm_risk >= " + i*10
						+ " and pm_risk < " + (i + 1)*10;
				int total =  Integer.parseInt("" + session.createNativeQuery(tmp).uniqueResult());
				System.out.println(i + "=" + total);
				list.add("" + total);
			}
			dto.setName("病理性近视");
			closeSession(session);
		}
		List<String> xAxis = new ArrayList<String>();
		for(int i = 0; i < 10; i++) {
			xAxis.add((i*10) + "-" + ((i + 1)*10) + "%");
		}
		dto.setxAxis(xAxis);
		dto.setSeries(list);
		logger.info("RiskDto=" + dto);
		return dto;
	}

	private RiskDto getRiskDto(int flag) {
		RiskDto dto = new RiskDto();
		List<String> list = new ArrayList<String>();
		if(flag == 0) {
			for(int i = 0; i < 10; i++) {
				String hql = "select count(1) from ImageResult where glaucomaRisk >= " + i*10
						+ " and glaucomaRisk < " + (i + 1)*10;
				int total =  Integer.parseInt("" + this.getCurrentSession().createQuery(hql).uniqueResult());
				list.add("" + total);
			}
			dto.setName("青光眼风险");
		}else if(flag == 1) {
			for(int i = 0; i < 10; i++) {
				String hql = "select count(1) from ImageResult where amdRisk >= " + i*10
						+ " and amdRisk < " + (i + 1)*10;
				int total = Integer.parseInt("" + this.getCurrentSession().createQuery(hql).uniqueResult());
				list.add("" + total);
			}
			dto.setName("黄斑病风险");
		}else if(flag == 2) {
			for(int i = 0; i < 10; i++) {
				String hql = "select count(1) from ImageResult where drRisk >= " + i*10
						+ " and drRisk < " + (i + 1)*10;
				int total = Integer.parseInt("" + this.getCurrentSession().createQuery(hql).uniqueResult());
				list.add("" + total);
			}
			dto.setName("糖网病风险");
		}else if(flag == 3) {
			for(int i = 0; i < 10; i++) {
				String hql = "select count(1) from ImageResult where pmRisk >= " + i*10
						+ " and pmRisk < " + (i + 1)*10;
				int total = Integer.parseInt("" + this.getCurrentSession().createQuery(hql).uniqueResult());
				list.add("" + total);
			}
			dto.setName("病理性近视");
		}
		List<String> xAxis = new ArrayList<String>();
		for(int i = 0; i < 10; i++) {
			xAxis.add((i*10) + "-" + ((i + 1)*10) + "%");
		}
		dto.setxAxis(xAxis);
		dto.setSeries(list);
		logger.info("RiskDto=" + dto);
		return dto;
	}

	@Override
	public QulityDto getQulityDto(int qulity, String sex) {
		if(Integer.parseInt(sex) == Constant.NoSex) {
			QulityDto dto = getQulityDtoBySex(qulity);
			return dto;
		}else if(Integer.parseInt(sex) == Constant.Man) {
			QulityDto dto = getQulityDtoBySex(qulity, "男");
			return dto;
		}else if(Integer.parseInt(sex) == Constant.Woman) {
			QulityDto dto = getQulityDtoBySex(qulity, "女");
			return dto;
		}
		return null;
	}

	private QulityDto getQulityDtoBySex(int qulity, String sex) {
		String sql = "select count(1) from imgresult ir left join person p on "
				+ " ir.img_id = p.lid or ir.img_id = p.rid where ir.qulity = "
				+ qulity + " and p.sex = " + "'" + sex + "'";
		QulityDto dto = new QulityDto();
		Session session = openSession();
		int total = Integer.parseInt("" + session.createNativeQuery(sql).uniqueResult());
		dto.setName("qulity-" + qulity);
		dto.setValue("" + total);
		closeSession(session);
		return dto;
	}

	private QulityDto getQulityDtoBySex(int qulity) {
		String hql = "select count(1) from ImageResult where qulity = " + qulity;
		int total = Integer.parseInt("" + this.getCurrentSession().createQuery(hql).uniqueResult());
		QulityDto dto = new QulityDto();
		dto.setName("qulity-" + qulity);
		dto.setValue("" + total);
		return dto;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getUidByImageId(String imgid) {
		String hql = "from Image where imgId = ?";
		Session session = openSession();
		Query query = session.createQuery(hql)
				.setParameter(0, imgid);
		Image img = (Image) query.uniqueResult();
		if(session != null) {
			closeSession(session);
		}
		return img.getUid();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String queryByTidName(String tid, String imgName) {
		String hql = "from Image where imgName = ? and tid = ?";
		Session session = openSession();
		Query query = session.createQuery(hql)
				.setParameter(0, imgName)
				.setParameter(1, tid);
		Image img = (Image) query.uniqueResult();
		if(session != null) {
			closeSession(session);
		}
		return img.getUid();
	}
}
