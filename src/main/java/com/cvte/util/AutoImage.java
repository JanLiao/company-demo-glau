package com.cvte.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.cvte.cons.Constant;
import com.cvte.entity.EyeInfo;

public class AutoImage {

	public static List<EyeInfo> getData() {
		List<EyeInfo> list = new ArrayList<EyeInfo>();
		LinkedList<String> link = Constant.L_Queue;
		if(Constant.L_Queue.size() == 0) {
			return list;
		}else {
			String filename = link.pop();
			String flag = checkR(filename);
			if(!"".equals(flag)) {             //该策略要改(不能丢弃，放入二级队列)
				EyeInfo infoL = getMessageInfo(filename, "L");
				EyeInfo infoR = getMessageInfo(flag, "R");
				list.add(infoL);
				list.add(infoR);
				return list;
			}else {
				return list;
			}
		}
	}

	private static EyeInfo getMessageInfo(String filename, String flag) {
		EyeInfo info = new EyeInfo();
		if("L".equals(flag)) {
			info.setFlag(0);
		}else if("R".equals(flag)) {
			info.setFlag(1);
		}
		String percent = generatePercent();
		String[] str = percent.split(",");
		info.setEid(str[0]);
		info.setPercent1(str[0]);
		info.setPercent2(str[1]);
		info.setPercent3(str[2]);
		info.setPercent4(str[3]);
		
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().startsWith("win")){
			info.setQulity(new Random().nextInt(3));
			info.setCdr("" + (new Random().nextInt(100))/100);
			info.setE_result1(filename);
			info.setE_result2(filename);
		}else {
			info.setQulity(ShellProcess.processImg(Constant.ImgServerPath + "/" + filename));
			String url = ShellProcess.getUtlByName(Constant.ImgServerPath + "/" + filename);
			String[] re = url.split(",");
			info.setE_result1(re[0]);
			info.setE_result2(re[1]);
			info.setCdr(re[2]);
		}
		
		return info;		
	}

	private static String generatePercent() {
		int percent1 = new Random().nextInt(99);
		int percent2 = new Random().nextInt(99 - percent1);
		int percent3 = new Random().nextInt(99 - percent1 - percent2);
		int percent4 = 100 -  percent1- percent2 - percent3;
		return "" + percent1 + "," + percent2 + "," + percent3 + "," + percent4;
	}

	private static String checkR(String filename) {
		List<String> list = Constant.R_List;
		String[] str = filename.split("_");
		String src = str[0] + str[1];
		String dest = "";
		int len = 0;
	    String flag = "";
		for(String s : list) {
			String[] st = s.split("_");
			dest = st[0] + st[1];
			if(src.equals(dest)) {
				flag = s;
				break;
			}
			len++;
		}
		Constant.R_List.remove(len);
		return flag;
	}

	public static void main(String[] args) {
		for(int i = 0; i < 15; i++) {
			System.out.println(new Random().nextInt(4));
		}
	}
}
