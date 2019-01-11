package com.cvte.service.impl;

import java.util.List;

import com.cvte.entity.Account;
import com.cvte.entity.Appoint;
import com.cvte.entity.HospitalDescBean;
import com.cvte.entity.Message;
import com.cvte.entity.Report;
import com.cvte.entity.ReportResult;
import com.cvte.entity.Terminal;

/**
* @author jan
* @data 2018年8月4日 上午11:40:12
*/
public interface PhoneService {

	List<HospitalDescBean> queryHospital();

	Account queryAccount(String account, String password);

	int saveImgData(String originalFilename, String string);

	Account queryByAccount(String description);

	void saveReport(int lId, int rId, int id);

	List<Appoint> getAllAppoint(String account);

	boolean saveAppointService(String serverTime, String serverType, String account);

	List<Report> getAllReport(String account);

	ReportResult getReportDetail(String reportId);

	void saveMessage(Message message);

	List<Message> getAllMessage(String account);

	Terminal getTerminal(int terminalId);

	void registerAccount(String account, String password, String phone, int terminalId);

	Account queryAccountByPhone(String phone);

	Account queryOnlyAccount(String account);

	int saveTerminal();

	void updateAccount(String sex, String age, String age2);

}
