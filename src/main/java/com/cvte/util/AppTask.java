package com.cvte.util;
/**
* @author jan
* @data 2018年8月15日 下午2:26:42
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class AppTask implements Callable<String> {
	private Socket socket;
	private int type;
	private String imgName;
	private String imgPath;

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public String call() throws Exception {
		OutputStream os = null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(os);
		pw.println(imgPath + "g");
		pw.flush();
		
		InputStream input = socket.getInputStream();
		byte[] buf = new byte[1024*256];
        //注意：read会产生阻塞  
        int len = input.read(buf);
        String message = new String(buf, 0, len);
        if("".equals(message)) {
        	if(type == 0) {
        		System.out.println("接收到quality的消息为 = " + message);
        		return message;
        	}else if(type == 1) {
        		System.out.println("接收到percent的消息为 = " + message);
        		return message;
        	}else if(type == 2) {
        		System.out.println("接收到cdr的消息为 = " + message);
        		return message;
        	}
        }
        
        return message;
	}

}
