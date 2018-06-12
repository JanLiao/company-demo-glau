package com.cvte.entity;

public class EyeInfo {
	
	private int flag;  //左据眼?右眼

	private String eid;  //用户
	
	private String e_url;  //图片url
	
	private int qulity;
		
	private String percent1;   //青光眼
	
	private String percent2;   //黄斑病
	
	private String percent3;  //糖网病
	
	private String percent4;  //病理性近视
	
	private String e_result1;
	
	private String e_result2;
	
	private String cdr;
	
	private String fullconf;
	
	private String cupconf;

	public String getFullconf() {
		return fullconf;
	}

	public void setFullconf(String fullconf) {
		this.fullconf = fullconf;
	}

	public String getCupconf() {
		return cupconf;
	}

	public void setCupconf(String cupconf) {
		this.cupconf = cupconf;
	}

	public int getQulity() {
		return qulity;
	}

	public void setQulity(int qulity) {
		this.qulity = qulity;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getE_url() {
		return e_url;
	}

	public void setE_url(String e_url) {
		this.e_url = e_url;
	}

	public String getPercent1() {
		return percent1;
	}

	public void setPercent1(String percent1) {
		this.percent1 = percent1;
	}

	public String getPercent2() {
		return percent2;
	}

	public void setPercent2(String percent2) {
		this.percent2 = percent2;
	}

	public String getPercent3() {
		return percent3;
	}

	public void setPercent3(String percent3) {
		this.percent3 = percent3;
	}

	public String getPercent4() {
		return percent4;
	}

	public void setPercent4(String percent4) {
		this.percent4 = percent4;
	}

	public String getE_result1() {
		return e_result1;
	}

	public void setE_result1(String e_result1) {
		this.e_result1 = e_result1;
	}

	public String getE_result2() {
		return e_result2;
	}

	public void setE_result2(String e_result2) {
		this.e_result2 = e_result2;
	}

	public String getCdr() {
		return cdr;
	}

	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	@Override
	public String toString() {
		return "EyeInfo [eid=" + eid + ", e_url=" + e_url + ", percent1=" + percent1 + ", percent2=" + percent2
				+ ", percent3=" + percent3 + ", percent4=" + percent4 + ", e_result1=" + e_result1 + ", e_result2="
				+ e_result2 + ", cdr=" + cdr + "]";
	}
	
	
}
