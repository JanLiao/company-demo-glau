package com.cvte.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void processImg(String filePath) throws InterruptedException {
		Process process = null;
		List<String> processList = new ArrayList<String>();
		try {
			process = Runtime.getRuntime().exec("/home/sunxu/Works/deploy/glau-cdr/experiments/glau/demo_glau_tcp.sh");
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			process.waitFor();
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			Start.processImg("");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
