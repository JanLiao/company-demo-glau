package com.cvte.netty.msg;

import java.io.Serializable;

public class LoggerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1712599602313557527L;
	
	private String fileName;
	
	private String reportName;
	
	private String isProcessed;
	
	private String processTime;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	public LoggerInfo(String fileName, String reportName, String isProcessed, String processTime) {
		super();
		this.fileName = fileName;
		this.reportName = reportName;
		this.isProcessed = isProcessed;
		this.processTime = processTime;
	}

	@Override
	public String toString() {
		return "LoggerInfo [fileName=" + fileName + ", reportName=" + reportName + ", isProcessed=" + isProcessed
				+ ", processTime=" + processTime + "]";
	}
	
	
}
