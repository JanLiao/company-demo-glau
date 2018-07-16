package com.cvte.handheld;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.cvte.cons.Constant;
import com.cvte.dao.impl.ImageDao;
import com.cvte.entity.EyeInfo;
import com.cvte.netty.msg.ImgInfo;
import com.cvte.util.ProcessImage;
import com.cvte.util.SaveToCSV;
import com.cvte.util.SpringContextHelper;

import io.netty.channel.ChannelHandlerContext;

/** 
* @author: jan 
* @date: 2018年6月18日 下午3:24:06 
*/
public class HandlerUtil {
	private static Logger logger = Logger.getLogger(HandlerUtil.class);
	private static ImageDao imgDao = (ImageDao) SpringContextHelper.getBean("imgDao");

	public static void processHandheld(ChannelHandlerContext ctx, String fileName, 
			String datepath) {
		EyeInfo info = getData(fileName, datepath);
		if(info == null) {
			System.out.println("该图片 is not 眼底图");
			logger.info("该图片 is not 眼底图");
		}
		else {
			//保存图片
			String imgId = imgDao.saveImage(fileName, datepath);
			logger.info("info to imginfo" + info);
			String[] num = fileName.split(",");
			
			//生成结果图
			Constant.ResultList = new ArrayList<String>();
			//String[] str = num[1].split("_");
			String st = "";
			if(num[1].substring(0, 2).equals("OD")) {
				st = "R";
			}
			else {  //"OS"
				st = "L";
			}   //uid未确定  num[1]   18-07-03 UID已经确定num[2]
			ImgInfo img = new ImgInfo(num[2], num[0], st, info.getE_url(), 
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
	
	private static EyeInfo getData(String filename, String datepath) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		
		if(Constant.SocketList.size() == 3) {
			int len = Constant.SocketList.size();
			logger.info("start to send message");
//			System.out.println("size=" + len);
			for(int i = 0; i < len; i++) {
				System.out.println("curr socket=" + Constant.SocketList.get(i));
				sendMessage(Constant.SocketList.get(i), filename, datepath);
			}
			logger.info("message is all send!!!");
		}
		
		//接收到三socket处理的消息
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
		
		logger.info("result is over===============");
		EyeInfo info = setData(filename, datepath);
		logger.info("info=" + info);
		return info;
	}
	
	private static EyeInfo setData(String filename, String datepath) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		EyeInfo info = new EyeInfo();
		String[] num = filename.split(",");
		String[] str = num[1].split("_");
		logger.info("UID=" + num[2]);
		//info.setFlag(flag);
		System.out.println("uid=" + num[2]);
		info.setEid(num[2]);
		info.setE_url("img/" + num[0] + "/" + datepath + "/" + num[1]);
		List<String> list = Constant.ResultList;
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
				if(Double.parseDouble(datastr[5]) == 0 && Double.parseDouble(datastr[4]) == 0) {
					info = null;
					break;
				}
				else {					
					copyFile(datastr[1], datastr[2], num[0], datepath);  //要copyFIle to 服务器内部
					info.setCupconf(datastr[5]);
					info.setFullconf(datastr[4]);
				}
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
	
	public static String transferPercent(String percent) {
		DecimalFormat   df = new DecimalFormat("#.00");
		float f = Float.parseFloat(percent);
		String ff = df.format(f);
		float fff = Float.parseFloat(ff);
		int ffff = (int) (fff*100);
		return "" + ffff + "%";
	}
	
}
