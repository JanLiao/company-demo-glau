package com.cvte.netty.msg;

import java.util.Map;


public class ResultMsg extends BaseMsg { // 服务端返回的信息

	/**
	 *  jan
	 */
	private static final long serialVersionUID = 582318096801545708L;
	private boolean success;
	private String msg; // 如果失败的话, 返回失败的原因
	private Map<String, Object> data; // 从服务器返回的额外数据
	private MsgType replyType; // 客户端发送什么类型的请求, 服务器就回应

	public ResultMsg() {
		this.success = false;
		this.replyType = MsgType.UnKnow;
		setMsgType(MsgType.Result);
	}

	public ResultMsg(MsgType replyType, String msg) {
		this.msg = msg;
		this.replyType = replyType;
	}
	

	public ResultMsg(boolean success, MsgType replyType) {
		this.success = success;
		this.replyType = replyType;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public MsgType getReplyType() {
		return replyType;
	}

	public void setReplyType(MsgType replyType) {
		this.replyType = replyType;
	}

	@Override
	public String toString() {
		return "ResultMsg [success=" + success + ", msg=" + msg + ", data=" + data + ", replyType=" + replyType + "]";
	}

}
