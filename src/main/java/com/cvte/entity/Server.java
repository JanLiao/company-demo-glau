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
* @data 2018年8月7日 上午1:08:36
*/
@Entity
@Table(name="server")
public class Server implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6230474024864910974L;
	private int id;
	private int hospitalId;
	private int accountId;
	private int glaucoma;
	private int amd;
	private int dr;
	private int pm;
	private Date serverData;
	private int deleted;
	public Server(int id, int hospitalId, int accountId, int glaucoma, int amd, int dr, int pm, int deleted) {
		super();
		this.id = id;
		this.hospitalId = hospitalId;
		this.accountId = accountId;
		this.glaucoma = glaucoma;
		this.amd = amd;
		this.dr = dr;
		this.pm = pm;
		this.deleted = deleted;
	}
	public Server() {
		super();
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
	
	@Column(name="hospital_id")
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Column(name="account_id")
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	@Column(name="glaucoma")
	public int getGlaucoma() {
		return glaucoma;
	}
	public void setGlaucoma(int glaucoma) {
		this.glaucoma = glaucoma;
	}
	
	@Column(name="amd")
	public int getAmd() {
		return amd;
	}
	public void setAmd(int amd) {
		this.amd = amd;
	}
	
	@Column(name="dr")
	public int getDr() {
		return dr;
	}
	public void setDr(int dr) {
		this.dr = dr;
	}
	
	@Column(name="pm")
	public int getPm() {
		return pm;
	}
	public void setPm(int pm) {
		this.pm = pm;
	}
	
	@Column(name="server_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getServerData() {
		return serverData;
	}
	public void setServerData(Date serverData) {
		this.serverData = serverData;
	}
	@Column(name="deleted")
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "Server [id=" + id + ", hospitalId=" + hospitalId + ", accountId=" + accountId + ", glaucoma=" + glaucoma
				+ ", amd=" + amd + ", dr=" + dr + ", pm=" + pm + ", deleted=" + deleted + "]";
	}
}
