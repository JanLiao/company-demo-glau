package com.cvte.netty;


import java.io.File;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;
import com.cvte.netty.msg.TransferMsg;
import com.cvte.util.FileUtil;

public class TransferFileHandler {

	public static boolean saveImage(TransferMsg transferMsg, String path) {
		System.out.println("length=" + transferMsg.getAttachment().length);
		String[] num = transferMsg.getFileName().split(",");

		// 解压
		byte[] unGZipBytes = FileUtil.unGZip(transferMsg.getAttachment());
		boolean success = FileUtil.bytesToFile(unGZipBytes, Constant.ImgServerPath + "/" + num[0] + "/" + path, num[1]);
		return success;
	}

}
