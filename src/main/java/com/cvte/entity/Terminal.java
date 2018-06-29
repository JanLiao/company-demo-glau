package com.cvte.entity;

import java.io.Serializable;
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
* @author: jan 
* @date: 2018年6月14日 上午11:06:44 
*/
@Entity
@Table(name="terminal")
public class Terminal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int terminalId;
	
	private String tid;
	
	private String password;
	
	private Date registTime;
	
	private int deleted;

	@Id
	@Column(name = "terminal_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(int terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "tid")
	public String getTid() {  //account == tid
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="register_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	@Column(name = "deleted")
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Terminal() {
		super();
	}

	public Terminal(int terminalId, String tid, String password, Date registTime, int deleted) {
		super();
		this.terminalId = terminalId;
		this.tid = tid;
		this.password = password;
		this.registTime = registTime;
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Terminal [terminalId=" + terminalId + ", tid=" + tid + ", password=" + password + ", registTime="
				+ registTime + ", deleted=" + deleted + "]";
	}
}
