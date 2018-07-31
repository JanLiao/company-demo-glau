package com.cvte.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.cvte.cons.Constant;
import com.cvte.dao.impl.ImageDao;
import com.cvte.entity.EyeInfo;
import com.cvte.netty.NettyChannelMap;
import com.cvte.netty.msg.DataKey;
import com.cvte.netty.msg.ImgInfo;
import com.cvte.netty.msg.MsgType;
import com.cvte.netty.msg.ResultMsg;
import com.cvte.netty.msg.TransferMsg;

import io.netty.channel.ChannelHandlerContext;

public class ProcessImage {
	
	private static ImageDao imgDao = (ImageDao) SpringContextHelper.getBean("imgDao");
	
	public static void process() {
		
		Logger logger = Logger.getLogger(ProcessImage.class);
		logger.info("检测list是否有文件");
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("cur size=" + Constant.ImgAll.size());
			//logger.info("cur img  size size=" + Constant.ImgAll.size());
			
			if(Constant.ImgAll.size() > 0) {
				String filename = Constant.ImgAll.get(0);
				logger.info("filename=" + Constant.ImgAll.get(0));
				Constant.ImgAll.remove(0);
				Constant.ResultList = new ArrayList<String>();
				EyeInfo info = getData(filename);
				logger.info("info to imginfo" + info);
				String[] str = filename.split("_");
				ImgInfo img = new ImgInfo(str[1], str[0], str[5], info.getE_url(), 
						info.getE_result1(), info.getFullconf(), 
						info.getCupconf(), info.getCdr(), info.getE_result2(), 
						"" + info.getQulity(), info.getPercent2(), info.getPercent3(),
						info.getPercent1(), info.getPercent4());
				
				logger.info("img=" + img);
				logger.info("生成单张图片csv");
				SaveToCSV.save(img);
				//生成report
				SaveToCSV.report(img);
				
				//传输img信息到PC端   已作废
//				ResultMsg resultMsg = new ResultMsg(true, MsgType.ImgMessage);
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put(DataKey.ImgMessage, img);
//				resultMsg.setData(map);
//				NettyChannelMap.get(DataKey.Account).writeAndFlush(resultMsg);
				
//				if(Constant.infoL.equals(str[3])) {
//					putEyeInfo(info, 0);
//				}else if (Constant.infoR.equals(str[3])) {
//					putEyeInfo(info, 1);
//				}
			}
			
		}
		
	}

	private static void putEyeInfo(EyeInfo info, int flag) {
		if(flag == 0) {
			int size = Constant.ListL.size();
			List<EyeInfo> list = Constant.ListL;
			for(int i = 0; i < size; i++) {
				if(info.getQulity() > list.get(i).getQulity()) {
					Constant.ListL.remove(i);
					Constant.ListL.add(info);
				}
			}
		}else if(flag == 1) {
			int size = Constant.ListR.size();
			List<EyeInfo> list = Constant.ListR;
			for(int i = 0; i < size; i++) {
				if(info.getQulity() > list.get(i).getQulity()) {
					Constant.ListR.remove(i);
					Constant.ListR.add(info);
				}
			}
		}
		
	}
	
	private static EyeInfo getData(String filename, String datepath) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		List<String> resultList = new ArrayList<String>();
		if(Constant.SocketList.size() == 3) {
			int len = Constant.SocketList.size();
			logger.info("start to send message");
//			System.out.println("size=" + len);
			
			// 开启线程池进行数据交换
			ExecutorService executor = Executors.newFixedThreadPool(3);
			Future<String>[] futures = new Future[3];
			for(int i = 0; i < len; i++) {
				System.out.println("curr socket=" + Constant.SocketList.get(i));
				ResultTask task = new ResultTask();
				task.setSocket(Constant.SocketList.get(i));
				task.setType(i);
				task.setDatepath(datepath);
				task.setImgName(filename);
				futures[i] = executor.submit(task);
				//sendMessage(Constant.SocketList.get(i), filename, datepath);
			}
			logger.info("message is all send!!!");
			
			for(int i = 0; i < len; i++) {
				String tmp = "";
				try {
					tmp = futures[i].get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				resultList.add(tmp);
			}
			executor.shutdown();
		}
		
		//接收到三socket处理的消息
//		while(true) {
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			if(Constant.ResultList.size() == 3) {
//				logger.info("result3=" + Constant.ResultList);
//				System.out.println("result3=" + Constant.ResultList);
//				break;
//			}
//		}
		
		logger.info("result is over===============");
		EyeInfo info = setData(resultList, filename, datepath);
		logger.info("info=" + info);
		return info;
	}
	
	private static EyeInfo setData(List<String> list, String filename, String datepath) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		EyeInfo info = new EyeInfo();
		String[] num = filename.split(",");
		String[] str = num[1].split("_");
		logger.info("UID=" + str[1]);
		//info.setFlag(flag);
		System.out.println("uid=" + str[1]);
		info.setEid(str[1]);
		info.setE_url("img/" + num[0] + "/" + datepath + "/" + num[1]);
		//List<String> list = Constant.ResultList;
		logger.info("list size=" + list.size());
		for(String s : list) {
			logger.info("string=" + s);
			String[] datastr = s.split(" ");
			if("Outputs:".equals(datastr[0])) {
				info.setCdr(datastr[3]);
				String[] str1 = datastr[1].split("/");
				String[] str2 = datastr[2].split("/");
				info.setE_result1("imgResult/" + num[0] + "/" + datepath + "/" + str1[str1.length - 1]);
				info.setE_result2("imgResult/" + num[0] + "/" + datepath + "/" + str2[str2.length - 1]);
				//transferImg(datastr[1]);  //要copyFIle to PC
				//transferImg(datastr[2]);  //要copyFIle to PC
				copyFile(datastr[1], datastr[2], num[0], datepath);  //要copyFIle to 服务器内部
				info.setCupconf(datastr[5]);
				info.setFullconf(datastr[4]);
			}else if("qulity".equals(datastr[0])) {
				info.setQulity(Integer.parseInt(datastr[1]));
			}else {
				String[] st = datastr[0].split(":");
				if("AMD".equals(st[0])) {
					String percent1 = transferPercent(datastr[2].split(":")[1]);
					info.setPercent1(percent1);
					String percent2 = transferPercent(st[1]);
					info.setPercent2(percent2);
					String percent3 = transferPercent(datastr[1].split(":")[1]);
					info.setPercent3(percent3);
					String percent4 = transferPercent(datastr[3].split(":")[1]);
					info.setPercent4(percent4);
				}
			}
			char[]  c=s.toCharArray();
			if('q' == c[0]) {
				info.setQulity(c[c.length - 1] - '0');
			}
		}
		logger.info("cur info=" + info);
		return info;
	}
	
	private static void copyFile(String url1, String url2, String pcnum, String datepath) {
		String[] str1 = url1.split("/");
		String[] str2 = url2.split("/");
		File srcFile = new File(url1);
		File f1 = new File(Constant.ImgResultPath + "/" + pcnum + "/" + datepath);
		if(!f1.exists()) {
			f1.mkdirs();
		}
		File destFile = new File(Constant.ImgResultPath + "/" + pcnum + "/" + 
		datepath + "/" + str1[str1.length - 1]);
		File srcFile1 = new File(url2);
		File destFile1 = new File(Constant.ImgResultPath + "/" + pcnum + "/" + 
		datepath + "/" + str2[str2.length - 1]);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			FileUtils.copyFile(srcFile1, destFile1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMessage(Socket socket, String imgName, String datepath) {
		System.out.println("start send message!");
    	OutputStream os = null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(os);
		String[] num = imgName.split(",");
		pw.println(Constant.ImgServerPath + "/" + num[0] + "/" + datepath + "/"  + num[1] + "g");
		pw.flush();
		System.out.println("send end!");
	}

	private static EyeInfo getData(String filename) {
		
		Logger logger = Logger.getLogger(ProcessImage.class);
		
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Constant.SocketList.size() == 3) {
				int len = Constant.SocketList.size();
				logger.info("start to send message");
//				System.out.println("size=" + len);
				for(int i = 0; i < len; i++) {
					System.out.println("curr socket=" + Constant.SocketList.get(i));
					sendMessage(Constant.SocketList.get(i), filename + "g");
				}
				logger.info("message is all send!!!");
				break;
			}
		}
		
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Constant.ResultList.size() == 3) {
				logger.info("result3=" + Constant.ResultList);
				System.out.println("result3=" + Constant.ResultList);
				break;
			}
		}
		logger.info("result is over");
		EyeInfo info = setData(filename);
		logger.info("info=" + info);
		return info;
	}
	
	private static EyeInfo setData(String filename) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		EyeInfo info = new EyeInfo();
		String[] str = filename.split("_");
		logger.info("UID=" + str[1]);
		//info.setFlag(flag);
		System.out.println("uid=" + str[1]);
		info.setEid(str[1]);
		info.setE_url("img/" + filename);
		List<String> list = Constant.ResultList;
		logger.info("list size=" + list.size());
		for(String s : list) {
			logger.info("string=" + s);
			String[] datastr = s.split(" ");
			if("Outputs:".equals(datastr[0])) {
				info.setCdr(datastr[3]);
				String[] str1 = datastr[1].split("/");
				String[] str2 = datastr[2].split("/");
				info.setE_result1("img/" + str1[str1.length - 1]);
				info.setE_result2("img/" + str2[str2.length - 1]);
				//transferImg(datastr[1]);  //要copyFIle to PC
				//transferImg(datastr[2]);  //要copyFIle to PC
				copyFile(datastr[1], datastr[2]);  //要copyFIle to 服务器内部
				info.setCupconf(datastr[5]);
				info.setFullconf(datastr[4]);
			}else if("qulity".equals(datastr[0])) {
				info.setQulity(Integer.parseInt(datastr[1]));
			}else {
				String[] st = datastr[0].split(":");
				if("AMD".equals(st[0])) {
					String percent1 = transferPercent(datastr[2].split(":")[1]);
					info.setPercent1(percent1);
					String percent2 = transferPercent(st[1]);
					info.setPercent2(percent2);
					String percent3 = transferPercent(datastr[1].split(":")[1]);
					info.setPercent3(percent3);
					String percent4 = transferPercent(datastr[3].split(":")[1]);
					info.setPercent4(percent4);
				}
			}
			char[]  c=s.toCharArray();
			if('q' == c[0]) {
				info.setQulity(c[c.length - 1] - '0');
			}
		}
		logger.info("cur info=" + info);
		return info;
	}
	
	private static void transferImg(String filePath) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		byte[] attachment = FileUtil.fileToBytes(filePath);
		System.out.println("attach=" + attachment.length);
		logger.info("attachment=" + attachment);
		
		TransferMsg transferMsg = new TransferMsg();
		transferMsg.setMsgType(MsgType.ImgTransfer);
		transferMsg.setAttachment(FileUtil.gZip(attachment));
		String[] str = filePath.split("/");
		transferMsg.setFileName(str[str.length - 1]);
		
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setReplyType(MsgType.ImgTransfer);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imgdata", transferMsg);
		resultMsg.setData(map);
		NettyChannelMap.get(DataKey.Account).writeAndFlush(resultMsg);
		logger.info("send img final");
	}

	private static String transferPercent(String percent) {
		DecimalFormat   df = new DecimalFormat("#.00");
		float f = Float.parseFloat(percent);
		String ff = df.format(f);
		float fff = Float.parseFloat(ff);
		int ffff = (int) (fff*100);
		return "" + ffff + "%";
	}
	
	private static void copyFile(String url1, String url2) {
		String[] str1 = url1.split("/");
		String[] str2 = url2.split("/");
		File srcFile = new File(url1);
		File destFile = new File(Constant.ImgResultPath + "/" + str1[str1.length - 1]);
		File srcFile1 = new File(url2);
		File destFile1 = new File(Constant.ImgResultPath + "/" + str2[str2.length - 1]);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			FileUtils.copyFile(srcFile1, destFile1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendMessage(Socket socket, String imgName) {
		System.out.println("start send message!");
    	OutputStream os = null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(os);
		pw.println(Constant.ImgServerPath + "/"  + imgName);
		pw.flush();
		System.out.println("send end!");
	}

	public static List<EyeInfo> getListInfo() {
		List<EyeInfo> list = new ArrayList<EyeInfo>();
		List<EyeInfo> listL = Constant.ListL;
		List<EyeInfo> listR = Constant.ListR;
		int len1 = listL.size();
		int len2 = listR.size();
		int m = 0;
		int n = 0;
		
		if(len1 == 0 || len2 == 0) {
			return list;
		}else {
			outerloop:
			for(int i = len1 - 1; i >= 0; i--) {
				for(int j = len2 - 1; j >= 0; j--) {
					if(listL.get(i).getEid().equals(listR.get(j).getEid())) {
						m = i;
						n = j;
						list.add(listL.get(m));
						list.add(listR.get(n));
						break outerloop;
					}
				}
			}
		}
		
		return list;
	}

	public static void processNew(ChannelHandlerContext ctx, String fileName, String datepath) {	
		Logger logger = Logger.getLogger(ProcessImage.class);
		String[] num = fileName.split(",");
		
		//生成结果图
		Constant.ResultList = new ArrayList<String>();
		EyeInfo info = getData(fileName, datepath);
		logger.info("info to imginfo" + info);
		String[] str = num[1].split("_");
		ImgInfo img = new ImgInfo(str[1], str[0], str[5], info.getE_url(), 
				info.getE_result1(), info.getFullconf(), 
				info.getCupconf(), info.getCdr(), info.getE_result2(), 
				"" + info.getQulity(), info.getPercent2(), info.getPercent3(),
				info.getPercent1(), info.getPercent4());
		
		logger.info("img=" + img);
		logger.info("生成单张图片csv");
		SaveToCSV.save(img, num[0], datepath);
		//生成report
		//SaveToCSV.report(img, num[0], datepath, ctx);  //已被替换
	}
	
	public static void processNew(ChannelHandlerContext ctx, String fileName, 
			String datepath, String imgId) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		String[] num = fileName.split(",");
		
		//生成结果图
		Constant.ResultList = new ArrayList<String>();
		EyeInfo info = getData(fileName, datepath);
		logger.info("info to imginfo" + info);
		String[] str = num[1].split("_");
		ImgInfo img = new ImgInfo(str[1], num[0], str[5], info.getE_url(), 
				info.getE_result1(), info.getFullconf(), 
				info.getCupconf(), info.getCdr(), info.getE_result2(), 
				"" + info.getQulity(), info.getPercent2(), info.getPercent3(),
				info.getPercent1(), info.getPercent4());
		
		
		logger.info("img=" + img);
		//保存imageResult
		imgDao.saveResult(img, imgId, num[1]);
		
		logger.info("生成单张图片csv");
		SaveToCSV.save(img, num[0], datepath);
		//生成report
		SaveToCSV.report(img, num[0], datepath, ctx, imgId);
	}
	
}
