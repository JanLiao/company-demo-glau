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
* @data 2018年8月14日 下午10:31:11
*/
@Entity
@Table(name="app_imgresult")
public class AppImgResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 727485759145427450L;

	private String id;

	private String imgId;
		
	private String imgName;
	
	private String imgUrl;
	
	private String qulity;
	
	private int glaucomaRisk;
	
	private int drRisk;
	
	private int pmRisk;   //病理性近视
	
	private int amdRisk;
	
	private double cdr;  //杯盘比CDR
	
	private String od;  //视盘置信度
	
	private String oc;  //视杯置信度
	
	private String fullPath;
	
	private String cupPath;
	
	private Date saveDate;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "app_img_id")
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

	@Column(name = "img_url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "qulity")
	public String getQulity() {
		return qulity;
	}

	public void setQulity(String qulity) {
		this.qulity = qulity;
	}

	@Column(name = "glaucoma_risk")
	public int getGlaucomaRisk() {
		return glaucomaRisk;
	}

	public void setGlaucomaRisk(int glaucomaRisk) {
		this.glaucomaRisk = glaucomaRisk;
	}

	@Column(name = "dr_risk")
	public int getDrRisk() {
		return drRisk;
	}

	public void setDrRisk(int drRisk) {
		this.drRisk = drRisk;
	}

	@Column(name = "pm_risk")
	public int getPmRisk() {
		return pmRisk;
	}

	public void setPmRisk(int pmRisk) {
		this.pmRisk = pmRisk;
	}

	@Column(name = "amd_risk")
	public int getAmdRisk() {
		return amdRisk;
	}

	public void setAmdRisk(int amdRisk) {
		this.amdRisk = amdRisk;
	}

	@Column(name = "cdr")
	public double getCdr() {
		return cdr;
	}

	public void setCdr(double cdr) {
		this.cdr = cdr;
	}

	@Column(name = "od")
	public String getOd() {
		return od;
	}

	public void setOd(String od) {
		this.od = od;
	}

	@Column(name = "oc")
	public String getOc() {
		return oc;
	}

	public void setOc(String oc) {
		this.oc = oc;
	}

	@Column(name = "full_path")
	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	@Column(name = "cup_path")
	public String getCupPath() {
		return cupPath;
	}

	public void setCupPath(String cupPath) {
		this.cupPath = cupPath;
	}
	
	@Column(name = "save_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSaveDate() {
		return saveDate;
	}
	
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	public AppImgResult() {
		super();
	}

	public AppImgResult(String imgId, String imgName, String imgUrl, String qulity, int glaucomaRisk, int drRisk, int pmRisk, int amdRisk,
			double cdr, String od, String oc, String fullPath, String cupPath, Date saveDate) {
		super();
		this.imgId = imgId;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
		this.qulity = qulity;
		this.glaucomaRisk = glaucomaRisk;
		this.drRisk = drRisk;
		this.pmRisk = pmRisk;
		this.amdRisk = amdRisk;
		this.cdr = cdr;
		this.od = od;
		this.oc = oc;
		this.fullPath = fullPath;
		this.cupPath = cupPath;
		this.saveDate = saveDate;
	}
}
