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


@Entity
@Table(name="image")
public class Image implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3503732101186203127L;

	private String imgId;
	
	private String imgName;
	
	private String tid;  //终端
	
	private String uid;
	
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

	@Column(name = "tid")
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@Column(name = "uid")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "rec_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Image() {
		super();
	}	
	

	public Image(String imgName, String tid, String uid, String imgPath, Date receiveTime) {
		super();
		this.imgName = imgName;
		this.tid = tid;
		this.uid = uid;
		this.imgPath = imgPath;
		this.receiveTime = receiveTime;
	}

	public Image(String imgName, String tid, String imgPath, Date receiveTime) {
		super();
		this.imgName = imgName;
		this.tid = tid;
		this.imgPath = imgPath;
		this.receiveTime = receiveTime;
	}

	@Override
	public String toString() {
		return "Image [imgId=" + imgId + ", imgName=" + imgName + ", tid=" + tid + ", imgPath=" + imgPath
				+ "]";
	}

}
