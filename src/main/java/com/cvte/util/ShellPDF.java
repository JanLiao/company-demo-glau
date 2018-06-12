package com.cvte.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;

public class ShellPDF {

	public static void generatePDF(String csvpath) {
		
		Logger logger = Logger.getLogger(ShellPDF.class);

		Process process = null;

		List<String> processList = new ArrayList<String>();
		try {
			logger.info("start Shell====" + Constant.StartPDF + "  " + csvpath);
			process = Runtime.getRuntime().exec(Constant.StartPDF + "  " + csvpath);
			System.out.println("end =====");
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		logger.info("is end ");
		
		//返回PDF路径
	}
	
	public static void main(String[] args) {
		ShellPDF.generatePDF("");
	}
	
}
