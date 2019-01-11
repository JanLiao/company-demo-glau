package com.cvte.netty.msg;

import java.util.Map;


public class RequestMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2214413449280908732L;
	private String terminalId; // 终端的数据库主键
	private Map<String, Object> param; // 从请求的参数

	public RequestMsg(String terminalId) {
		this.terminalId = terminalId;
		setMsgType(MsgType.Request);
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "RequestMsg [terminalId=" + terminalId + ", param=" + param + "]";
	}

}
