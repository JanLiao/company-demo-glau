package com.cvte.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cvte.cons.Constant;

public class ShellPercent {

	public static void getUtlByName() {

		Process process = null;

		List<String> processList = new ArrayList<String>();
		try {
			// process = Runtime.getRuntime().exec(path1 + " " + filePath + " " + path2);
			process = Runtime.getRuntime().exec(Constant.StartQulity);
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

	public static void main(String[] args) {
		ShellPercent.getUtlByName();
	}
}
