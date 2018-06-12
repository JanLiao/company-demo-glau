package com.cvte.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;

public class StartClient {

	public static void startClient() {
		Logger logger = Logger.getLogger(StartClient.class);
		List<String> processList = new ArrayList<String>();
		try {
			Process process = Runtime.getRuntime().exec(Constant.StartClient);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("全部启动客户端完成!!!!");
		logger.info("启动tcp完成!");
	}

	public static void main(String[] args) {
		StartClient sc = new StartClient();
		sc.startClient();
	}
}
