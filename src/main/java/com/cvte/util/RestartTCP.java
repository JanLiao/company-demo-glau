package com.cvte.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;

/** 
* @author: jan 
* @date: 2018年5月5日 上午11:00:19 
*/
public class RestartTCP {
	
	private static Logger logger = Logger.getLogger(RestartTCP.class);

	public static void closeAllTCP() {
		int size = Constant.SocketList.size();
		if(size != 0) {
			for(int i = 0 ;i < size; i++) {
				try {
					Constant.SocketList.get(i).close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			logger.info("socket is all closed====");
		}else {
			logger.info("no no ^ - ^ ");
		}
		
		restartAllSocket();
	}

	private static void restartAllSocket() {
		
	}
	
}
