package com.cvte.entity;

import java.io.Serializable;

/**
* @author jan
* @data 2018年8月16日 下午7:53:28
*/
public class ReportResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6996798959456677214L;
	private String lPath;
	private String rPath;
	private AppImgResult lResult;
	private AppImgResult rResult;
	private String pdfPath;
	public String getlPath() {
		return lPath;
	}
	public void setlPath(String lPath) {
		this.lPath = lPath;
	}
	public String getrPath() {
		return rPath;
	}
	public void setrPath(String rPath) {
		this.rPath = rPath;
	}
	public AppImgResult getlResult() {
		return lResult;
	}
	public void setlResult(AppImgResult lResult) {
		this.lResult = lResult;
	}
	public AppImgResult getrResult() {
		return rResult;
	}
	public void setrResult(AppImgResult rResult) {
		this.rResult = rResult;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	@Override
	public String toString() {
		return "ReportResult [lPath=" + lPath + ", rPath=" + rPath + ", lResult=" + lResult + ", rResult=" + rResult
				+ "]";
	}

}
