package com.cvte.util;

import java.io.*;

import com.cvte.cons.Constant;

public class GenerateImg {
	
	public void generateDate(File[] file) {
		
		for(File f1 : file) {
			//System.out.println(f1.isDirectory() + "," +  f1.getName() + "," + f1.getPath());
			if(f1.isDirectory()) {
				File[] f = new File(f1.getPath()).listFiles();
				generateDate(f);
			}else {
				Constant.Data_List.add(f1.getPath().toString());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		File[] file = new File(Constant.PATH).listFiles();
		
		GenerateImg img = new GenerateImg();
		img.generateDate(file);
		
		System.out.println(Constant.Data_List.size() + "," + Constant.Data_List.get(0));
		
		CopyToSrc.copyToSrc();
	}
	
}
