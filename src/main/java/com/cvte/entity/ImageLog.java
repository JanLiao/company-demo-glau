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
@Table(name="imglog")
public class ImageLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8107940988895851984L;

	private String logid;
	
	private String tid;
	
	private String imgId;
	
	private String pdfPath;
	
	private String isProcessed;
	
	private Date processTime;

	@Id
	@Column(name = "logid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	@Column(name = "tid")
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@Column(name = "img_id")
	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	@Column(name = "pdf_path")
	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	@Column(name = "is_processed")
	public String isProcessed() {
		return isProcessed;
	}

	public void setProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}

	@Column(name = "process_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public ImageLog() {
		super();
	}

	public ImageLog(String logid, String tid, String imgId, String pdfPath, String isProcessed,
			Date processTime) {
		super();
		this.logid = logid;
		this.tid = tid;
		this.imgId = imgId;
		this.pdfPath = pdfPath;
		this.isProcessed = isProcessed;
		this.processTime = processTime;
	}

	@Override
	public String toString() {
		return "ImageLog [logid=" + logid + ", tid=" + tid + ", imgId=" + imgId + ", pdfPath=" + pdfPath
				+ ", isProcessed=" + isProcessed + ", processTime=" + processTime + "]";
	}

}
