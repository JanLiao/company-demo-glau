package com.cvte.test;

import java.text.DecimalFormat;
import java.util.Random;

public class TestFloat {
	
	private static String transferPercent(String percent) {
		DecimalFormat   df = new DecimalFormat("#.00");
		float f = Float.parseFloat(percent);
		String ff = df.format(f);
		float fff = Float.parseFloat(ff);
		int ffff = (int) (fff*100);
		return "" + ffff + "%";
	}

	public static void main(String[] args) {
		String s = "0.00621";
		String t = "0.2188";
		float tmp = 0.01f;
		float a = Float.parseFloat(s) + tmp;
		float b = Float.parseFloat(t) + tmp;
		DecimalFormat   df = new DecimalFormat("#.00");
		System.out.println("a=" + a + ",b=" + b);
		System.out.println("a=" + df.format(a) + ",b=" + df.format(b));
		float c = Float.parseFloat(df.format(a));
		float d = Float.parseFloat(df.format(b));
		int e = (int) (c*100);
		int f = (int) (d*100);
		System.out.println(e + "=" + f);
		System.out.println(transferPercent("0.22222"));
		System.out.println(transferPercent("0.22622"));
		
		for(int i = 0; i < 15; i++) {
			System.out.println(new Random().nextInt(2));
		}
	}
	
}
