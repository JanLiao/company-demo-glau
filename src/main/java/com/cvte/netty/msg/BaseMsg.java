package com.cvte.netty.msg;

import java.io.Serializable;


public class BaseMsg implements Serializable { //netty传输消息的类

	private static final long serialVersionUID = -6745239903567533584L;
	
	private MsgType msgType; // 消息的类型

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "BaseMsg [msgType=" + msgType + "]";
	}
}
