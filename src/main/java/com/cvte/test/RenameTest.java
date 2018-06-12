package com.cvte.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RenameTest {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+1;
		int date = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		File file = new File("C:\\Users\\CVTE\\Desktop\\test");
		File[] f = file.listFiles();
		SimpleDateFormat mat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		System.out.println(f.length);
		int flag = 0;
		for(int i = 0; i < f.length; i++) {
			if(!f[i].isDirectory()) {
				//System.out.println(ff.getName());
				if(flag < 2) {
					if(i % 2 == 0) {
						f[i].renameTo(new File("C:/Users/CVTE/Desktop/test" + "/" + "jan_" + i + "_" + mat.format(new Date()) + "_Color" + "_L_45_" + "00001.jpg"));
						flag += 1;
					}else {
						f[i].renameTo(new File("C:/Users/CVTE/Desktop/test" + "/" + "jan_" + i + "_" + mat.format(new Date()) + "_Color" + "_R_45_00002.jpg"));
						flag = flag + 1;
					}
				}else {
					flag = 0;
					if(i % 2 == 0) {
						f[i].renameTo(new File("jan_" + i + "_" + mat.format(new Date()) + "_Color" + "_L_45_" + "00001.jpg"));
						flag += 1;
					}else {
						f[i].renameTo(new File("jan_" + i + "_" + mat.format(new Date()) + "_Color" + "_R_45_00002.jpg"));
						flag = flag + 1;
					}
				}
				
			}
		}
	}
	
}
