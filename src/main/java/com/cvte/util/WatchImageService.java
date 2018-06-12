package com.cvte.util;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author jan
 * @date 2018年3月19日 上午1:48:40
 * @version V1.0 
 */
public class WatchImageService implements ServletContextListener{
	
	private Thread myThread;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		myThread = new Thread(new Runnable() {    //使用另一个线程来执行该方法，会避免占用Tomcat的启动时间 

			@Override
			public void run() {
				startWatchServer();    //服务器启动文件夹监视
			}
			
		});
		
		myThread.start();
	}

	protected void startWatchServer() {		
		ImgProcess.process();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {  //关闭时回收资源
		
		try {
			myThread.interrupt();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		WatchImageService watch = new WatchImageService();
		watch.startWatchServer();
	}

}
