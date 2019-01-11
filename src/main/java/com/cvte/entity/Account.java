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
* @author jan
* @data 2018年8月7日 上午1:18:42
*/
@Entity
@Table(name="account")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 503729181361380914L;
	private int id;
	private String account;
	private String phone;
	private String sex;
	private String ageRange;
	private String password;
	private String token;
	private int terminalId;
	private Date registerTime;
	private int deleted;
	public Account() {
		super();
	}
	public Account(int id, String account, String password, 
			String token, Date registerTime, int deleted) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.token = token;
		this.registerTime = registerTime;
		this.deleted = deleted;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "account")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name="age_range")
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	@Column(name="token")
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name="terminal_id")
	public int getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(int terminalId) {
		this.terminalId = terminalId;
	}
	@Column(name="register")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	@Column(name = "deleted")
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", account=" + account + ", password=" + password + ", registerTime="
				+ registerTime + ", deleted=" + deleted + "]";
	}
}
