package com.cvte.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* @author jan
* @data 2018年8月14日 下午10:57:33
*/
@Entity
@Table(name="report")
public class Report {
	
	private int id;
	
	private int lImgId;
	
	private int rImgId;
	
	private int accountId;
	
	private String appointNum;
	
	private int reportType;
	
	private Date reportTime;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "l_img_id")
	public int getlImgId() {
		return lImgId;
	}

	public void setlImgId(int lImgId) {
		this.lImgId = lImgId;
	}

	@Column(name = "r_img_id")
	public int getrImgId() {
		return rImgId;
	}

	public void setrImgId(int rImgId) {
		this.rImgId = rImgId;
	}

	@Column(name = "account_id")
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Column(name="appoint_num")
	public String getAppointNum() {
		return appointNum;
	}

	public void setAppointNum(String appointNum) {
		this.appointNum = appointNum;
	}

	@Column(name="report_type")
	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	@Column(name="report_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Report(int id, int lImgId, int rImgId, int accountId) {
		super();
		this.id = id;
		this.lImgId = lImgId;
		this.rImgId = rImgId;
		this.accountId = accountId;
	}

	public Report() {
		super();
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", lImgId=" + lImgId + ", rImgId=" + rImgId + ", accountId=" + accountId
				+ ", appointNum=" + appointNum + ", reportType=" + reportType + ", reportTime=" + reportTime + "]";
	}

}
