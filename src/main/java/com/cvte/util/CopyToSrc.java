package com.cvte.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.cvte.cons.Constant;

public class CopyToSrc {

	public static void copyToSrc() throws IOException {
		int num = 0;
		int flag = 0;
		for(String path : Constant.Data_List) {
			File srcFile = new File( path);
			String[] str = path.split("/");
			String filename = "";
			if(str.length == 1) {
				String[] s = path.split("\\\\");
				filename = s[s.length - 1];
			}else {
				filename = str[str.length - 2] + str[str.length - 1];
				System.out.println(filename);
			}
			//md5
			BigInteger md5 = FileUtil.getMD5(path);
			long size = srcFile.length();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(flag < 1) {
				flag += 1;
				String name = "u" + num + "_" + "p" + num + "_" + sdf.format(date) + "_L_CON_0_" + md5.toString() + "_" + 
				size + "_"  + filename;
				File destFile = new File(Constant.SRC + "/" + name);
				FileUtils.copyFile(srcFile, destFile);
				//num += 1;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				flag = 0;
				String name = "u" + num + "_" + "p" + num + "_" + sdf.format(date) + "_R_CON_0_" + md5.toString() + "_" + 
				size + "_"  + filename;
				File destFile = new File(Constant.SRC + "/" + name);
				FileUtils.copyFile(srcFile, destFile);
				num += 1;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
