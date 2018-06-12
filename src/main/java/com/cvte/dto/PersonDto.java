package com.cvte.dto;
/** 
* @author: jan 
* @date: 2018年4月13日 上午9:13:01 
*/
public class PersonDto {
	
	private String pid;

	private String tid;
	
	private String uid;
	
	private String sex;
	
	private String qulity;
	
	private String glaucomaRisk;
	
	private String amdRisk;
	
	private String drRisk;
	
	private String pmRisk;
	
	private String cdr;
	
	private String od;
	
	private String oc;
	
	private String pdtTime;   //此处字母有误 pdtTime  ---> pdfTime
	
	private String pdfUrl;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQulity() {
		return qulity;
	}

	public void setQulity(String qulity) {
		this.qulity = qulity;
	}

	public String getGlaucomaRisk() {
		return glaucomaRisk;
	}

	public void setGlaucomaRisk(String glaucomaRisk) {
		this.glaucomaRisk = glaucomaRisk;
	}

	public String getAmdRisk() {
		return amdRisk;
	}

	public void setAmdRisk(String amdRisk) {
		this.amdRisk = amdRisk;
	}

	public String getDrRisk() {
		return drRisk;
	}

	public void setDrRisk(String drRisk) {
		this.drRisk = drRisk;
	}

	public String getPmRisk() {
		return pmRisk;
	}

	public void setPmRisk(String pmRisk) {
		this.pmRisk = pmRisk;
	}

	public String getCdr() {
		return cdr;
	}

	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	public String getOd() {
		return od;
	}

	public void setOd(String od) {
		this.od = od;
	}

	public String getOc() {
		return oc;
	}

	public void setOc(String oc) {
		this.oc = oc;
	}

	public String getPdtTime() {
		return pdtTime;
	}

	public void setPdtTime(String pdtTime) {
		this.pdtTime = pdtTime;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public PersonDto() {
		super();
	}

	public PersonDto(String pid, String tid, String uid, String sex, String qulity, String glaucomaRisk, String amdRisk, String drRisk,
			String pmRisk, String cdr, String od, String oc, String pdtTime) {
		super();
		this.pid = pid;
		this.tid = tid;
		this.uid = uid;
		this.sex = sex;
		this.qulity = qulity;
		this.glaucomaRisk = glaucomaRisk;
		this.amdRisk = amdRisk;
		this.drRisk = drRisk;
		this.pmRisk = pmRisk;
		this.cdr = cdr;
		this.od = od;
		this.oc = oc;
		this.pdtTime = pdtTime;
	}

	@Override
	public String toString() {
		return "PersonDto [tid=" + tid + ", uid=" + uid + ",sex=" + sex + ", qulity=" + qulity + ", glaucomaRisk=" + glaucomaRisk
				+ ", amdRisk=" + amdRisk + ", drRisk=" + drRisk + ", pmRisk=" + pmRisk + ", cdr=" + cdr + ", od=" + od
				+ ", oc=" + oc + ", pdtTime=" + pdtTime + "]";
	}
	
}
