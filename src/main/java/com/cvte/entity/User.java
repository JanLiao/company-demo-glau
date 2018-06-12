package com.cvte.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** 
* @author: jan 
* @date: 2018年4月23日 下午4:03:53 
*/
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -2308254140110207283L;

	private int id;
	
	private String account;
	
	private String password;
	
	private String tid;
	
	private String uid;
	
	private String sex;
	
	private String phone;
	
	private String email;
	
	private Date registerTime;
	
	private Date loginTime;
	
	private int deleted;
	
	private Role role;

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
	
	@Column(name = "pwd")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="tid")
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@Column(name="uid")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="register_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name="login_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "deleted")
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@OneToOne  
    @JoinColumn(name = "rid")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(String account, String password, String tid, String uid, String sex, String phone, String email, int deleted) {
		super();
		this.account = account;
		this.password = password;
		this.tid = tid;
		this.uid = uid;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.deleted = deleted;
	}
	
	public User(String account, String password, String sex, String phone, String email, int deleted) {
		super();
		this.account = account;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.deleted = deleted;
	}

	public User(int id, String account, String password, String phone, String email) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", phone=" + phone + ", email="
				+ email + ",role=" + role + "]";
	}

}
