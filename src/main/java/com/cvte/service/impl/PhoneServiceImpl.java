package com.cvte.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvte.cons.Constant;
import com.cvte.dao.impl.PhoneDao;
import com.cvte.entity.Account;
import com.cvte.entity.AppImage;
import com.cvte.entity.AppImgResult;
import com.cvte.entity.Appoint;
import com.cvte.entity.HospitalDescBean;
import com.cvte.entity.Message;
import com.cvte.entity.Person;
import com.cvte.entity.Report;
import com.cvte.entity.ReportResult;
import com.cvte.entity.Terminal;
import com.cvte.util.AppUtil;

/**
* @author jan
* @data 2018年8月4日 上午11:40:59
*/
@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {
	@Autowired
	private PhoneDao phoneDao;
	private Logger logger = Logger.getLogger(PhoneServiceImpl.class);

	@Override
	public List<HospitalDescBean> queryHospital() {
		List<HospitalDescBean> list = phoneDao.queryHospital();
		return list;
	}

	@Override
	public Account queryAccount(String account, String password) {
		Account act = phoneDao.queryAccount(account, password);
		return act;
	}

	@Override
	public int saveImgData(String imgName, String imgPath) {
		// save appImage to mysql
		int imgId = phoneDao.saveAppImage(imgPath, imgName);
		AppImgResult info = AppUtil.getData(imgPath, imgName, imgId);
		phoneDao.saveAppImgResult(info);
		return imgId;
	}

	@Override
	public Account queryByAccount(String account) {
		Account act = phoneDao.queryByAccount(account);
		return act;
	}

	@Override
	public void saveReport(int lId, int rId, int id) {
		Report report = new Report();
		report.setAccountId(id);
		report.setlImgId(lId);
		report.setrImgId(rId);
		report.setReportType(Constant.REPORT_TYPE_APP);
		report.setReportTime(new Date());
		//phoneDao.saveReport(report);
		phoneDao.saveReportUpload(report);
	}

	@Override
	public List<Appoint> getAllAppoint(String account) {
		List<Appoint> list = phoneDao.getAllAppoint(account);
		return list;
	}
	
	@Override
	public List<Report> getAllReport(String account) {
		List<Report> list = phoneDao.getAllReport(account);
		return list;
	}

	@Override
	public boolean saveAppointService(String serverTime, String serverType, String account) {
		boolean flag = phoneDao.saveAppoint(serverTime, serverType, account);
		return flag;
	}

	@Override
	public ReportResult getReportDetail(String reportId) {
		Report report = phoneDao.getReport(reportId);
		//AppImage lImg = phoneDao.getAppImage(report.getlImgId());
		//AppImage rImg = phoneDao.getAppImage(report.getrImgId());
		System.out.println("report = " + report);
		System.out.println(report.getlImgId() + "=" + report.getrImgId());
		ReportResult result = new ReportResult();
		if(report.getReportType() == 0) {
			AppImgResult lResult = null;
			AppImgResult rResult = null;
			if(report.getlImgId() == 0) {
				logger.info("该报告没有左眼图");
			}else {
				lResult = phoneDao.getAppImgResult(report.getlImgId());
				result.setlPath(lResult.getImgUrl());
				result.setlResult(lResult);
			}
			if(report.getrImgId() == 0) {
				logger.info("该报告没有右眼图");
			}else {
				rResult = phoneDao.getAppImgResult(report.getrImgId());
				result.setrPath(rResult.getImgUrl());
				result.setrResult(rResult);
			}
		}else {
			AppImgResult lResult = null;
			AppImgResult rResult = null;
			if(report.getlImgId() == 0) {
				logger.info("该报告没有左眼图");
			}else {
				lResult = phoneDao.getImgResult(report.getlImgId());
				result.setlPath(lResult.getImgUrl());
				result.setlResult(lResult);
			}
			if(report.getrImgId() == 0) {
				logger.info("该报告没有右眼图");
			}else {
				rResult = phoneDao.getImgResult(report.getrImgId());
				result.setrPath(rResult.getImgUrl());
				result.setrResult(rResult);
			}
			Person person = phoneDao.getPerson(report.getAppointNum(), report.getlImgId(),
					report.getrImgId());
			result.setPdfPath(person.getLrpdf());
		}
		return result;
	}
	
	@Override
	public void saveMessage(Message msg) {
		phoneDao.saveMessage(msg);
	}
	
	@Override
	public List<Message> getAllMessage(String account){
		return phoneDao.getAllMessage(account);
	}

	@Override
	public Terminal getTerminal(int terminalId) {
		return phoneDao.getTerminal(terminalId);
	}

	@Override
	public void registerAccount(String account, String password, String phone, int terminalId) {
		// 注册新用户
		phoneDao.registerAccount(account, password, phone, terminalId);
	}

	@Override
	public Account queryAccountByPhone(String phone) {
		if("".equals(phone)) {
			return null;
		}else {
			return phoneDao.queryAccountByPhone(phone);
		}
	}

	@Override
	public Account queryOnlyAccount(String account) {
		if("".equals(account)) {
			return null;
		}else {
			return phoneDao.queryOnlyAccount(account);
		}
	}

	@Override
	public int saveTerminal() {
		return phoneDao.saveTerminal();
	}

	@Override
	public void updateAccount(String account, String sex, String age) {
		phoneDao.updateAccount(account, sex, age);
	}
}
