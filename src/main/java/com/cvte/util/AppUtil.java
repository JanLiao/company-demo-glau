package com.cvte.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.io.FileUtils;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;
import com.cvte.entity.AppImgResult;

/**
* @author jan
* @data 2018年8月15日 下午2:23:21
*/
public class AppUtil {
	private static Logger logger = Logger.getLogger(AppUtil.class);

	public static AppImgResult getData(String imgPath, String imgName, int imgId) {
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
				AppTask task = new AppTask();
				task.setSocket(Constant.SocketList.get(i));
				task.setType(i);
				task.setImgPath(imgPath);
				task.setImgName(imgName);
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
		logger.info("APP result is all receive");
		
		AppImgResult info = setData(resultList, imgName, imgId, imgPath);
		return info;
	}
	
	public static AppImgResult setData(List<String> list, String imgName,
			int imgId, String imgPath) {
		AppImgResult info = new AppImgResult();
		info.setImgId("" + imgId);
		info.setImgName(imgName);
		String path = imgPath.substring(imgPath.indexOf("appImg"));
		info.setImgUrl(path);

		String rootPath = imgPath.substring(0, imgPath.indexOf("appImg")) + "appImgResult/";
		logger.info("list size=" + list.size());
		for(String s : list) {
			logger.info("string=" + s);
			String[] datastr = s.split(" ");
			if("Outputs:".equals(datastr[0])) {
				info.setCdr(Double.parseDouble(datastr[3]));
				String[] str1 = datastr[1].split("/");
				String[] str2 = datastr[2].split("/");
				System.out.println("full path = " + str1[str1.length - 1]);
				System.out.println("cup path = " + str2[str2.length - 1]);
				logger.info("full path = " + str1[str1.length - 1]);
				logger.info("full path = " + str2[str2.length - 1]);
				info.setFullPath("appImgResult/" + str1[str1.length - 1]);
				info.setCupPath("appImgResult/" + str2[str2.length - 1]);
				//transferImg(datastr[1]);  //要copyFIle to PC
				//transferImg(datastr[2]);  //要copyFIle to PC
				copyFile(datastr[1], datastr[2], rootPath + str1[str1.length - 1], rootPath + str2[str2.length - 1]);  //要copyFIle to 服务器内部
				info.setOd(datastr[4]);
				info.setOc(datastr[5]);
			}else if("qulity".equals(datastr[0])) {
				info.setQulity(datastr[1]);
			}else {
				String[] st = datastr[0].split(":");
				if("AMD".equals(st[0])) {
					int percent1 = transferPercent(datastr[2].split(":")[1]);
					info.setGlaucomaRisk(percent1);
					int percent2 = transferPercent(st[1]);
					info.setAmdRisk(percent2);
					int percent3 = transferPercent(datastr[1].split(":")[1]);
					info.setDrRisk(percent3);
					int percent4 = transferPercent(datastr[3].split(":")[1]);
					info.setPmRisk(percent4);
				}
			}
			char[]  c=s.toCharArray();
			if('q' == c[0]) {
				info.setQulity("" + (c[c.length - 1] - '0'));
			}
		}
		logger.info("cur info=" + info);
		return info;
	}
	
	private static void copyFile(String srcPath1, String srcPath2, String destPath1, String destPath2) {
		File srcFile1 = new File(srcPath1);
		File srcFile2 = new File(srcPath2);
		File destFile1 = new File(destPath1);
		File destFile2 = new File(destPath2);
		try {
			FileUtils.copyFile(srcFile1, destFile1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileUtils.copyFile(srcFile2, destFile2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int transferPercent(String percent) {
		DecimalFormat   df = new DecimalFormat("#.00");
		float f = Float.parseFloat(percent);
		String ff = df.format(f);
		float fff = Float.parseFloat(ff);
		int ffff = (int) (fff*100);
		return ffff;
	}
}
