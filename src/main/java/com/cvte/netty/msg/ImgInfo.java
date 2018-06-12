package com.cvte.netty.msg;

import java.io.Serializable;

public class ImgInfo implements Serializable {

	/**
	 *  author : jan
	 */
	private static final long serialVersionUID = 3283314541399123605L;

	private String uid;
	
	private String pid;
	
	private String lr;
	
	private String originalImg;
	
	private String segmentedImg;
	
	private String od;
	
	private String oc;
	
	private String cdr;
	
	private String zoomImg;
	
	private String qulity;
	
	private String amd;
	
	private String dr;
	
	private String glaucoma;
	
	private String myopia;

	public String getLr() {
		return lr;
	}

	public void setLr(String lr) {
		this.lr = lr;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOriginalImg() {
		return originalImg;
	}

	public void setOriginalImg(String originalImg) {
		this.originalImg = originalImg;
	}

	public String getSegmentedImg() {
		return segmentedImg;
	}

	public void setSegmentedImg(String segmentedImg) {
		this.segmentedImg = segmentedImg;
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

	public String getCdr() {
		return cdr;
	}

	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	public String getZoomImg() {
		return zoomImg;
	}

	public void setZoomImg(String zoomImg) {
		this.zoomImg = zoomImg;
	}

	public String getQulity() {
		return qulity;
	}

	public void setQulity(String qulity) {
		this.qulity = qulity;
	}

	public String getAmd() {
		return amd;
	}

	public void setAmd(String amd) {
		this.amd = amd;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

	public String getGlaucoma() {
		return glaucoma;
	}

	public void setGlaucoma(String glaucoma) {
		this.glaucoma = glaucoma;
	}

	public String getMyopia() {
		return myopia;
	}

	public void setMyopia(String myopia) {
		this.myopia = myopia;
	}

	public ImgInfo(String uid, String pid, String lr, String originalImg, String segmentedImg, String od, String oc, String cdr,
			String zoomImg, String qulity, String amd, String dr, String glaucoma, String myopia) {
		super();
		this.lr = lr;
		this.uid = uid;
		this.pid = pid;
		this.originalImg = originalImg;
		this.segmentedImg = segmentedImg;
		this.od = od;
		this.oc = oc;
		this.cdr = cdr;
		this.zoomImg = zoomImg;
		this.qulity = qulity;
		this.amd = amd;
		this.dr = dr;
		this.glaucoma = glaucoma;
		this.myopia = myopia;
	}

	@Override
	public String toString() {
		return "ImgInfo [uid=" + uid + ", pid=" + pid + ", originalImg=" + originalImg + ", segmentedImg="
				+ segmentedImg + ", od=" + od + ", oc=" + oc + ", cdr=" + cdr + ", zoomImg=" + zoomImg + ", qulity="
				+ qulity + ", amd=" + amd + ", dr=" + dr + ", glaucoma=" + glaucoma + ", myopia=" + myopia + "]";
	}
	
}
