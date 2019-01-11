package com.cvte.netty;

import java.io.File;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cvte.cons.Constant;
import com.cvte.dao.impl.ImageDao;
import com.cvte.dao.impl.TerminalDao;
import com.cvte.entity.Terminal;
import com.cvte.handheld.HandlerUtil;
import com.cvte.netty.msg.BaseMsg;
import com.cvte.netty.msg.DataKey;
import com.cvte.netty.msg.LoggerInfo;
import com.cvte.netty.msg.MsgType;
import com.cvte.netty.msg.PDFRecMsg;
import com.cvte.netty.msg.ResultMsg;
import com.cvte.netty.msg.TransferMsg;
import com.cvte.netty.msg.ValidateMsg;
import com.cvte.util.ProcessImage;
import com.cvte.util.SaveToCSV;
import com.cvte.util.SpringContextHelper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;


public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {
	
	//private ChannelHandlerContext ctx = null;
	private ImageDao imgDao = (ImageDao) SpringContextHelper.getBean("imgDao");
	private TerminalDao terminalDao = (TerminalDao)SpringContextHelper.getBean("terminalDao");
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
		Logger logger = Logger.getLogger(NettyServerHandler.class);
		switch (msg.getMsgType()) {
			case Heartbeat: // 是心跳包就忽略掉
				SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.print(form.format(new java.util.Date()));
				System.out.println("  server println Heartbeat");
				InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
						.remoteAddress();
				String clientIP = insocket.getAddress().getHostAddress();
				logger.info(clientIP + "  =  " + form.format(new java.util.Date()) + "=server println Heartbeat");
				break;
				
			case PDFRec: //收到PC端已接收到PDF文件  传输日志保存信息
				PDFRecMsg pdfMsg = (PDFRecMsg) msg;
				String[] pdfname = pdfMsg.getPdfname().split(",");
				
				Date day=new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				

				LoggerInfo log = new LoggerInfo(pdfname[1], pdfname[0], "true", df.format(day));
				System.out.println("start transfer logger=" + log);
				logger.info("start transfer logger=" + log);
				//传输日志处理信息到PC端
				ResultMsg resultMsg1 = new ResultMsg(true, MsgType.ImgMessage);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(DataKey.ImgMessage, log);
				resultMsg1.setData(map);
				ctx.writeAndFlush(resultMsg1);
				
				//日志保存到服务器
				File file = new File(Constant.csvLog);
				if(!file.exists()) {
					file.mkdirs();
				}
				
				SaveToCSV.writeCSV(log);
				
				logger.info("logger transfer is over");
				break;
	
			case Validate: // 客户端请求连接服务器
				System.out.println("msg = " + msg);
				ValidateMsg validateMsg = (ValidateMsg) msg;
				String account = validateMsg.getAccount();
				String psw = validateMsg.getPassword();
				logger.info("account=" + account + "=" + psw);
				Terminal terminal = terminalDao.queryByAccount(account, psw);
				if (terminal != null) {
					System.out.println("连接验证成功!!!!!");
					logger.info("验证成功");
					NettyChannelMap.add(account, (SocketChannel)ctx.channel());
					logger.info(account + " 添加成功");
					//通知客户端, 验证成功
					ResultMsg resultMsg = new ResultMsg(true, MsgType.Validate);
					ctx.writeAndFlush(resultMsg);	
				} else {
					ctx.writeAndFlush(new ResultMsg(MsgType.Validate, "未经允许客户端连接失败, 请检查账号和密码是否正确"));					
				}
				break;
			
			case ImgTransfer:
				TransferMsg transferMsg = (TransferMsg)msg;
				System.out.println("我收到了图片=" + transferMsg.getAttachment());
				logger.info("我收到图片=" +  transferMsg.getFileName() +  transferMsg.getAttachment());
				SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
				String path = formate.format(new Date());
				String[] num = transferMsg.getFileName().split(",");
				File fi= new File(Constant.ImgServerPath + "/" + num[0] + "/" + path);
				if(!fi.exists()) {
					fi.mkdirs();
				}
				logger.info("创建目录成功");
				TransferFileHandler.saveImage(transferMsg, path);
				logger.info("图片保存成功");
				
				//图片信息保存到数据库
				String[] str = num[1].split("_");
				if(str.length == 1) {
					HandlerUtil.processHandheld(ctx, transferMsg.getFileName(), path);
				}
				else {
					String imgId = imgDao.saveImage(transferMsg.getFileName(), path);
					//处理接收到的图片
					logger.info("图片imgId=" + imgId);
					ProcessImage.processNew(ctx, transferMsg.getFileName(), path, imgId);
				}
				break;
				
			default:
				break;
		}
	}

    @Override  
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {  
        NettyChannelMap.remove((SocketChannel)ctx.channel());  
    }  
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		NettyChannelMap.remove((SocketChannel)ctx.channel());
		ctx.close();
	}

}
