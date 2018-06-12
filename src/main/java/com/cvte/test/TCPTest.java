package com.cvte.test;

import com.cvte.util.TCPServer;

/** 
* @author: jan 
* @date: 2018年4月11日 上午9:49:48 
*/
public class TCPTest {

	public static void main(String[] args) {
		//开启线程启动tcp服务
		TCPServer tcp = new TCPServer();
		Thread serverThread = new Thread(new Runnable() {

			@Override
			public void run() {
				
				tcp.startServer();
			}
			
		});
		
		serverThread.start();
		
		System.out.println("thread is started");
		//sleep 10s后interrupt线程
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("thread interrupt");
		
		//tcp.sendMessage(1);
		serverThread.interrupt();
		//serverThread.stop();
	}
	
}
