package com.cvte.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class TestName {

	public static void main(String[] args) {
		String path = "C:/Users/CVTE/Desktop/test";
		String destPath = "C:/Users/CVTE/Desktop/src";
		File file = new File("C:/Users/CVTE/Desktop/test");
		SimpleDateFormat mat = new SimpleDateFormat("yyyymmdd_HHmmss");
		File[] files = file.listFiles();
		System.out.println("length=" + files.length);
		int forhead = -1;
		for(int i = 0; i < files.length; i++) {
			int tmp = i / 2;
			if(tmp != forhead) {
				files[i].renameTo(new File(path + "/" + "jan_" + tmp + "_" + mat.format(new Date()) + "_Color_L_45_000001.jpg"));
				forhead = tmp;
			}else {
				files[i].renameTo(new File(path + "/" + "jan_" + tmp + "_" + mat.format(new Date()) + "_Color_R_45_000001.jpg"));
				forhead = tmp;
			}
		}
		
		File[] filess = file.listFiles();
		System.out.println(filess[50]);
		for(File f : filess) {
			if(!f.isDirectory()) {
				try {
					FileUtils.copyFile(f, new File(destPath + "/" + f.getName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
