package com.cvte.netty.msg;


public class HeartBeatMsg extends BaseMsg {

	public HeartBeatMsg() {
      setMsgType(MsgType.Heartbeat);
	}
	

}
