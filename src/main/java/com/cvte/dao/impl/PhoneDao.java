package com.cvte.dao.impl;

import java.util.List;

import com.cvte.entity.Account;
import com.cvte.entity.AppImage;
import com.cvte.entity.AppImgResult;
import com.cvte.entity.Appoint;
import com.cvte.entity.HospitalDescBean;
import com.cvte.entity.Message;
import com.cvte.entity.Person;
import com.cvte.entity.Report;
import com.cvte.entity.Terminal;

/**
* @author jan
* @data 2018年8月4日 上午11:43:13
*/
public interface PhoneDao {

	List<HospitalDescBean> queryHospital();

	Account queryAccount(String account, String password);

	int saveAppImage(String imgPath, String imgName);

	void saveAppImgResult(AppImgResult info);

	Account queryByAccount(String account);

	void saveReport(Report report);

	List<Appoint> getAllAppoint(String account);

	boolean saveAppoint(String serverTime, String serverType, String account);

	List<Report> getAllReport(String account);

	Report getReport(String reportId);

	AppImage getAppImage(int getlImgId);

	AppImgResult getAppImgResult(int getlImgId);

	void saveMessage(Message msg);

	List<Message> getAllMessage(String account);

	Terminal getTerminal(int terminalId);

	Account queryOnlyAccount(String account);

	Account queryAccountByPhone(String phone);

	void registerAccount(String account, String password, String phone, int terminalId);

	int saveTerminal();

	void updateAccount(String account, String sex, String age);

	Person getPerson(String appointNum, int getlImgId, int getrImgId);

	AppImgResult getImgResult(int imgId);

	void saveReportUpload(Report report);

}
