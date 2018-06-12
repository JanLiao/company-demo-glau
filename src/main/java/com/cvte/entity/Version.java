package com.cvte.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author: jan 
* @date: 2018年4月25日 下午5:19:26 
*/
@Entity
@Table(name="version")
public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4083587415390376977L;

	private int vid;
	
	private String versionCode;
	
	private String versionInfo;
	
	private Date uploadTime;

	@Id
	@Column(name="vid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	@Column(name="version_code")
	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name="version_info")
	public String getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	@Column(name="upload_time")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Version(int vid, String versionCode, String versionInfo, Date uploadTime) {
		super();
		this.vid = vid;
		this.versionCode = versionCode;
		this.versionInfo = versionInfo;
		this.uploadTime = uploadTime;
	}

	public Version() {
		super();
	}

	@Override
	public String toString() {
		return "Version [vid=" + vid + ", versionCode=" + versionCode + ", versionInfo=" + versionInfo + ", uploadTime="
				+ uploadTime + "]";
	}

}
