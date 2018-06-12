package com.cvte.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ShellCDRTest {

	public static void getUtlByName() {

		Process process = null;

		List<String> processList = new ArrayList<String>();
		try {
			process = Runtime.getRuntime().exec("python /home/intern1/jan/tcp_cdr.py");
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
