package com.cvte.util;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;
import com.cvte.netty.NettyChannelMap;
import com.cvte.netty.msg.BaseMsg;
import com.cvte.netty.msg.MsgType;
import com.cvte.netty.msg.ResultMsg;

/**
* @author jan
* @data 2018年8月15日 下午3:20:32
*/
public class SendMessageUtil {
	private static Logger logger = Logger.getLogger(SendMessageUtil.class);

	public static void sendMessage(String terminal) {
		if(NettyChannelMap.get(terminal) != null) {
			ResultMsg msg = new ResultMsg(true, MsgType.ReportMessage);
			NettyChannelMap.get(terminal).writeAndFlush(msg);
			logger.info("通知成功");
		}else {
			logger.info("通知失败");
		}
	}
	
	public static void sendAppointMessage(String terminal) {
		if(NettyChannelMap.get(terminal) != null) {
			ResultMsg msg = new ResultMsg(true, MsgType.AppointMessage);
			NettyChannelMap.get(terminal).writeAndFlush(msg);
			logger.info("预约通知成功");
		}else {
			logger.info("预约通知失败");
		}
	}

	public static void sendReportMsgToApp(String tid) {
		if(NettyChannelMap.get(tid) != null) {
			ResultMsg msg = new ResultMsg(true, MsgType.AppointMessage);
			NettyChannelMap.get(tid).writeAndFlush(msg);
			logger.info("预约通知成功");
		}else {
			logger.info("预约通知失败");
		}
	}
}
