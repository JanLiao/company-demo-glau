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
* @data 2018年8月16日 上午12:56:35
*/
@Entity
@Table(name="appoint")
public class Appoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1985472234049589517L;

	private int id;
	
	private int accountId;
	
	private int hospitalId;
	
	private String appointNum;
	
	private int glaucoma;
	
	private int amd;
	
	private int dr;
	
	private int pm;
	
	private Date saveTime;
	
	private int deleted;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="account_id")
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Column(name="hospital_id")
	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Column(name="appoint_num")
	public String getAppointNum() {
		return appointNum;
	}

	public void setAppointNum(String appointNum) {
		this.appointNum = appointNum;
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

	@Column(name="save_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
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
		return "Appoint [id=" + id + ", accountId=" + accountId + ", glaucoma=" + glaucoma + ", amd=" + amd + ", dr="
				+ dr + ", pm=" + pm + ", saveTime=" + saveTime + ", deleted=" + deleted + "]";
	}
}
