package com.cvte.util;

import java.io.File;
import java.math.BigInteger;

public class MDCheck {

	public static int checkMd5(String md5, String size, String path) {
		
		//BigInteger md = FileUtil.getMD5(path);
		
		File file = new File(path);
		
		String fileSize = "" + file.length();
		
		if(!fileSize.equals(size)) return 1;
		
		//if(!md5.equals(md.toString())) return 2;
		
		return 0;
	}
}
