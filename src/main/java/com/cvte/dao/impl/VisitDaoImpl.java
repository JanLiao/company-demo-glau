package com.cvte.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cvte.dto.CDRDto;
import com.cvte.dto.VisitDto;
import com.cvte.entity.ImageResult;

/** 
* @author: jan 
* @date: 2018年4月20日 下午4:54:43 
*/
@Repository("visitDao")
public class VisitDaoImpl implements VisitDao {

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
	public VisitDto getVisitDto(String tid, String date, String sex) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		VisitDto dto = new VisitDto();
		List<String> dateList = new ArrayList<String>();
		List<Integer> data = new ArrayList<Integer>();
		if("".equals(date) || date == null) {
			logger.info("日期为空,返回前一个月数据");
			for(int i = 0; i < 30; i++) {
				c.setTime(new Date());
				c.add(Calendar.DATE, (i - 30));
				String d = sdf.format(c.getTime());
				dateList.add(d);
				Date utilDate = null;
				try {
					utilDate = sdf.parse(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				if(Integer.parseInt(sex) == 2) {
					//System.out.println("tid = " + tid);
					String hql = "select count(1) from Person where tid = ?"
							 + " and pdfTime = ? ";
					Query query = this.getCurrentSession().createQuery(hql);
					query.setParameter(0, tid);
					query.setParameter(1, utilDate);
					//System.out.println("num = " + query.uniqueResult());
					data.add(Integer.parseInt("" + query.uniqueResult()));
//					String totalHql = "select count(1) from Person where tid = " + tid;
//					total = Integer.parseInt("" + this.getCurrentSession().createQuery(totalHql).uniqueResult());
				}else if(Integer.parseInt(sex) == 1) {
					String hql = "select count(1) from Person where tid = '" + tid
							 + "' and sex = " + "'女'" + " and pdfTime = ? ";
					Query query = this.getCurrentSession().createQuery(hql);
					query.setParameter(0, sqlDate);
					data.add(Integer.parseInt("" + query.uniqueResult()));
//					String totalHql = "select count(1) from Person where tid = " + tid
//							+ " and sex = '女' ";
//					total = Integer.parseInt("" + this.getCurrentSession().createQuery(totalHql).uniqueResult());
					
				}else if(Integer.parseInt(sex) == 0) {
					String hql = "select count(1) from Person where tid = '" + tid
							 + "' and sex = " + "'男'" + " and pdfTime = ? ";
					Query query = this.getCurrentSession().createQuery(hql);
					query.setParameter(0, sqlDate);
					data.add(Integer.parseInt("" + query.uniqueResult()));
//					String totalHql = "select count(1) from Person where tid = " + tid
//							+ " and sex = '男' ";
//					total = Integer.parseInt("" + this.getCurrentSession().createQuery(totalHql).uniqueResult());
					
				}
			}
		}else {
			boolean check = checkDate(date);
			if(check) {
				int offset = getOffsetByDate(date);
				for(int i = 0; i < offset; i++) {
					String[] str = date.split(" ");
					try {
						c.setTime(sdf.parse(str[0]));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					c.add(Calendar.DATE, i);
					String d = sdf.format(c.getTime());
					dateList.add(d);
					Date utilDate = null;
					try {
						utilDate = sdf.parse(d);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					if(Integer.parseInt(sex) == 2) {
						String hql = "select count(1) from Person where tid = '" + tid
								 + "' and pdfTime = ? ";
						Query query = this.getCurrentSession().createQuery(hql);
						query.setParameter(0, sqlDate);
						data.add(Integer.parseInt("" + query.uniqueResult()));
//						String totalHql = "select count(1) from Person where tid = " + tid;
//						total = Integer.parseInt("" + this.getCurrentSession().createQuery(totalHql).uniqueResult());
					}else if(Integer.parseInt(sex) == 1) {
						String hql = "select count(1) from Person where tid = '" + tid
								 + "' and sex = " + "'女'" + " and pdfTime = ? ";
						Query query = this.getCurrentSession().createQuery(hql);
						query.setParameter(0, sqlDate);
						data.add(Integer.parseInt("" + query.uniqueResult()));
//						String totalHql = "select count(1) from Person where tid = " + tid
//								+ " and sex = '女' ";
//						total = Integer.parseInt("" + this.getCurrentSession().createQuery(totalHql).uniqueResult());
						
					}else if(Integer.parseInt(sex) == 0) {
						String hql = "select count(1) from Person where tid = '" + tid
								 + "' and sex = " + "'男'" + " and pdfTime = ? ";
						Query query = this.getCurrentSession().createQuery(hql);
						query.setParameter(0, sqlDate);
						data.add(Integer.parseInt("" + query.uniqueResult()));
//						String totalHql = "select count(1) from Person where tid = " + tid
//								+ " and sex = '男' ";
//						total = Integer.parseInt("" + this.getCurrentSession().createQuery(totalHql).uniqueResult());
						
					}
				}
			}else {
				logger.info("日期偏移量为负数");
				System.out.println("日期偏移为负数");
			}
			
		}
		String totalHql = "select count(1) from Person where tid = '" + tid + "'";
		int total = Integer.parseInt("" + this.getCurrentSession().createQuery(totalHql).uniqueResult());
		dto.setName(tid);
		dto.setData(data);
		dto.setDate(dateList);
		dto.setTotal(total);
		return dto;
	}

	private boolean checkDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] str = date.split(" ");
		try {
			if(sdf.parse(str[0]).after(sdf.parse(str[2]))) {
				return false;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Date d = new Date();
		try {
			if(d.after(sdf.parse(str[0]))) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	private int getOffsetByDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] str = date.split(" ");
		int sub = 0;
		try {
			if(sdf.parse(str[2]).after(new Date())) {
				sub = dayBetween(str[0], sdf.format(new Date()));
			}else {
				sub = dayBetween(str[0], str[2]);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (sub + 1);
	}

	private int dayBetween(String start, String end) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        try {
			cal.setTime(sdf.parse(start));
		} catch (ParseException e) {
			e.printStackTrace();
		}    
        long time1 = cal.getTimeInMillis();                 
        try {
			cal.setTime(sdf.parse(end));
		} catch (ParseException e) {
			e.printStackTrace();
		}    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(between_days));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CDRDto> getAllCDR() {
		List<CDRDto> dtoList = new ArrayList<CDRDto>();
		CDRDto man = new CDRDto();
		List<String> manData = new ArrayList<String>();
		String sql = "select ir.* from imgresult ir left join person p on "
				+ " ir.img_id = p.lid or ir.img_id = p.rid  ";
		Session session = openSession();
		List<ImageResult> manList = session.createNativeQuery(sql + "where p.sex = '男'", ImageResult.class).list();
		for(ImageResult img : manList) {
			manData.add("" + img.getCdr());
		}
		man.setName("男性");
		man.setData(manData);
		dtoList.add(man);
		
		CDRDto woman = new CDRDto();
		List<String> womanData = new ArrayList<String>();
		List<ImageResult> womanList = session.createNativeQuery(sql + "where p.sex = '女'", ImageResult.class).list();
		for(ImageResult img : womanList) {
			womanData.add("" + img.getCdr());
		}
		woman.setName("女性");
		woman.setData(womanData);
		dtoList.add(woman);
		closeSession(session);
		return dtoList;
	}
	
}
