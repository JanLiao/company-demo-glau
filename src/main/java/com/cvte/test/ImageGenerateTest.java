package com.cvte.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.cvte.cons.Constant;
import com.cvte.entity.EyeInfo;

public class ImageGenerateTest {

	public static List<EyeInfo> getData() {
		Logger logger = Logger.getLogger(ImageGenerateTest.class);
		logger.info("start getData====");
		List<EyeInfo> list = new ArrayList<EyeInfo>();
		List<String> ls = new ArrayList<String>();
		System.out.println("ssss===");
		while(true) {
			String imL = Constant.imgL;
			String imR = Constant.imgR;
			String[] s1 = imL.split("_");
			String[] s2 = imR.split("_");
			if(!"".equals(Constant.imgL) && !"".equals(Constant.imgR) && (s1[0] + s1[1]).equals(s2[0] + s2[1])) {
				logger.info("enter++++L");
				System.out.println("enter++++L");
				Constant.ResultList = ls;
				EyeInfo info1;
				try {
					info1 = getInfo(Constant.imgL, 0);
					list.add(info1);
					logger.info("L-info=" + info1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				logger.info("enter R");
				System.out.println("enter++++R");
				Constant.ResultList = new ArrayList<String>();
				EyeInfo info2;
				try {
					info2 = getInfo(Constant.imgR, 1);
					list.add(info2);
					logger.info("R-info=" + info2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				//Constant.imgL = "";
				//Constant.imgR = "";
				break;
			}
		}
		return list;
	}

	private static EyeInfo getInfo(String imgPath, int flag) throws InterruptedException {
		Logger logger = Logger.getLogger(ImageGenerateTest.class);
		logger.info("start====");
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Constant.SocketList.size() == 3) {
				int len = Constant.SocketList.size();
//				System.out.println("size=" + len);
				for(int i = 0; i < len; i++) {
					System.out.println("curr socket=" + Constant.SocketList.get(i));
					sendMessage(Constant.SocketList.get(i), imgPath);
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
				System.out.println("result3=" + Constant.ResultList);
				break;
			}
		}
		
		EyeInfo info = setData(imgPath, flag);
		System.out.println("info=" + info);
		return info;
	}
	
	private static EyeInfo setData(String imgPath, int flag) {
		EyeInfo info = new EyeInfo();
		String[] str = imgPath.split("_");
		info.setFlag(flag);
		System.out.println("uid=" + str[0]);
		info.setEid(str[0]);
		info.setE_url("img/" + imgPath);
		List<String> list = Constant.ResultList;
		for(String s : list) {
			String[] datastr = s.split(" ");
			if("Outputs:".equals(datastr[0])) {
				info.setCdr(datastr[3]);
				String[] str1 = datastr[1].split("/");
				String[] str2 = datastr[2].split("/");
				info.setE_result1("img/" + str1[str1.length - 1]);
				info.setE_result2("img/" + str2[str2.length - 1]);
				//copyFile(datastr[1], datastr[2]);
				info.setCupconf(datastr[5]);
				info.setFullconf(datastr[4]);
			}else if("qulity".equals(datastr[0])) {
				System.out.println("----------" + datastr[1]);
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
		return info;
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
		File destFile = new File(Constant.ImgServerPath + "/" + str1[str1.length - 1]);
		File srcFile1 = new File(url2);
		File destFile1 = new File(Constant.ImgServerPath + "/" + str2[str2.length - 1]);
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

	@SuppressWarnings("unused")
	private static String generatePercent() {
		int percent1 = new Random().nextInt(99);
		int percent2 = new Random().nextInt(99 - percent1);
		int percent3 = new Random().nextInt(99 - percent1 - percent2);
		int percent4 = 100 -  percent1- percent2 - percent3;
		return "" + percent1 + "," + percent2 + "," + percent3 + "," + percent4;
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
		pw.println(Constant.DEST + "/"  + imgName);
		pw.flush();
		System.out.println("send end!");
	}
	
	public static void recesiveMessage() {
		while(true) {
			try {
				for(int i = 0; i < Constant.SocketList.size(); i++) {
					InputStream in = Constant.SocketList.get(i).getInputStream();  
		            byte[] buf = new byte[1024];  
		            //注意：read会产生阻塞  
		            int len = in.read(buf);
		            String message = new String(buf, 0, len);
		            System.out.println("percent=" + new String(buf,0,len)); 
		            Constant.ResultList.add(message);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//测试
	public static void main(String[] args) {
		
		//开三个线程执行
		new Thread(new Runnable() {
			public void run() {
				new TCPQulityTest().startServer();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				new TCPPercentTest().startServer();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				new TCPCDRTest().startServer();
			}
		}).start();
		System.out.println("length = " + Constant.SocketList.size());
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Constant.SocketList.size() == 3) {
				int len = Constant.SocketList.size();
				System.out.println("size=" + len);
				for(int i = 0; i < len; i++) {
					System.out.println("curr socket=" + Constant.SocketList.get(i));
					sendMessage(Constant.SocketList.get(i),"u13_p13_2018_L_CON_1.jpg");
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i = 0; i < len; i++) {
					System.out.println("curr socket=" + Constant.SocketList.get(i));
					sendMessage(Constant.SocketList.get(i),"u13_p13_2018_R_CON_1.jpg");
				}
				break;
			}			
		}
		
//		new Thread(new Runnable() {
//			public void run() {
//				recesiveMessage();
//			}
//		}).start();
		
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Constant.ResultList.size() == 1) {
				System.out.println("result1=" + Constant.ResultList);
			}
			
			if(Constant.ResultList.size() == 2) {
				System.out.println("result2=" + Constant.ResultList);
			}
			
			if(Constant.ResultList.size() == 6) {
				System.out.println("result6=" + Constant.ResultList);
				break;
			}
		}
	}

}
