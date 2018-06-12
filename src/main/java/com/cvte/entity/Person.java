package com.cvte.entity;

import java.util.Date;
//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="person")
public class Person implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3355649111763516806L;

	private String pid;
	
	private String tid;
	
	private String uid;   //客户id
	
	private String sex; //性别
	
	private String lid;  //左眼id 只保存qulity最好的imgId
	
	private String rid;  //右眼id
	
	private String lpdf;   //左眼PDF路径
	
	private String rpdf;  //右眼PDF
	
	private String lrpdf;  //左右眼PDF
	
	private Date pdfTime;

	@Id
	@Column(name = "pid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "lid")
	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	@Column(name = "rid")
	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	@Column(name = "lpdf")
	public String getLpdf() {
		return lpdf;
	}

	public void setLpdf(String lpdf) {
		this.lpdf = lpdf;
	}

	@Column(name = "rpdf")
	public String getRpdf() {
		return rpdf;
	}

	public void setRpdf(String rpdf) {
		this.rpdf = rpdf;
	}

	@Column(name = "lrpdf")
	public String getLrpdf() {
		return lrpdf;
	}

	public void setLrpdf(String lrpdf) {
		this.lrpdf = lrpdf;
	}

	@Column(name = "pdf_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPdfTime() {
		return pdfTime;
	}

	public void setPdfTime(Date pdfTime) {
		this.pdfTime = pdfTime;
	}

	public Person() {
		super();
	}

	public Person(String pid, String tid, String uid, String sex, String lid, String rid, String lpdf, String rpdf,
			String lrpdf, Date pdfTime) {
		super();
		this.pid = pid;
		this.tid = tid;
		this.uid = uid;
		this.sex = sex;
		this.lid = lid;
		this.rid = rid;
		this.lpdf = lpdf;
		this.rpdf = rpdf;
		this.lrpdf = lrpdf;
		this.pdfTime = pdfTime;
	}

	@Override
	public String toString() {
		return "Person [pid=" + pid + ", tid=" + tid + ", uid=" + uid + ", sex=" + sex + ", lid=" + lid
				+ ", rid=" + rid + ", lpdf=" + lpdf + ", rpdf=" + rpdf + ", lrpdf=" + lrpdf + ", pdfTime=" + pdfTime + "]";
	}

}
