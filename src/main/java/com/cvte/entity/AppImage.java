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
* @data 2018年8月14日 下午9:25:05
*/

@Entity
@Table(name="app_image")
public class AppImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 470276057670781681L;

	private String imgId;
	
	private String imgName;
		
	private String accountId;
	
	private int isL;
	
	private int isR;
	
	private String imgPath;
		
	private Date receiveTime;

	@Id
	@Column(name = "img_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	@Column(name = "img_name")
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	@Column(name = "account_id")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "is_l")
	public int getIsL() {
		return isL;
	}

	public void setIsL(int isL) {
		this.isL = isL;
	}

	@Column(name = "is_r")
	public int getIsR() {
		return isR;
	}

	public void setIsR(int isR) {
		this.isR = isR;
	}

	@Column(name = "img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "upload_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
}
