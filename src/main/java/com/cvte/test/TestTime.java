package com.cvte.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {

	
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format0 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		System.out.println(format.format(date));
		System.out.println(format0.format(date));
	}
}
