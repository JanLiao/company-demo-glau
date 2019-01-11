package com.cvte.netty.msg;


public class HeartBeatMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6391302208669871157L;

	public HeartBeatMsg() {
      setMsgType(MsgType.Heartbeat);
	}
	

}
