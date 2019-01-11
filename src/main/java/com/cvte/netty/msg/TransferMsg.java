package com.cvte.netty.msg;

import java.util.Arrays;


public class TransferMsg extends ResultMsg { //

	/**
	 * 
	 */
	private static final long serialVersionUID = 7356027563164672455L;
	private String fileName;
	private byte[] attachment;
	
	public TransferMsg() {
		super();
		setReplyType(MsgType.Download);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	

}
