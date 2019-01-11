package com.cvte.netty.msg;

public class PDFRecMsg  extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4065926301140200756L;
	private String pdfname;

	public PDFRecMsg(String pdfname) {
		this.pdfname = pdfname;
		setMsgType(MsgType.PDFRec);
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	@Override
	public String toString() {
		return "PDFRecMsg [pdfname=" + pdfname + "]";
	}
	
}

