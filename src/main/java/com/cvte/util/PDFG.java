package com.cvte.util;

import java.awt.Insets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;

import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

public class PDFG {

	public static void main(String[] args) throws Exception {
		File pdfFile = new File("C:/Users/CVTE/Desktop/pdffile.pdf");
		StringBuffer  sb = new StringBuffer();
		sb.append("html代码拼接位置");
		StringReader sr = new StringReader(sb.toString());
		//第一种方法
		//htmltopdf(pdfFile, sr);
		//第二种方法
		htmltopdf1(new File("C:/Users/CVTE/Desktop/pdf.pdf"), "C:/Users/CVTE/Desktop/test1.jsp");
	}
	 /*
	  * 第一种方法：html代码转pdf文件
	  */
	private static void htmltopdf(File outputPDFFile, StringReader strReader) throws Exception {
		FileOutputStream fos = new FileOutputStream(outputPDFFile);
		PD4ML pd4ml = new PD4ML();
		pd4ml.setPageInsets(new Insets(20,10,10,10));
		pd4ml.setHtmlWidth(950);
		pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
		pd4ml.useTTF("java:fonts", true);
		pd4ml.setDefaultTTFs("KaiTi_GB2312", "KaiTi_GB2312", "KaiTi_GB2312");
		pd4ml.enableDebugInfo();
		pd4ml.render(strReader, fos);
	}
	
	/*
	  * 第二种方法：html文件代码转pdf文件
	  */
	private static void htmltopdf1(File outputPDFFile, String inputHTMLFileName) throws Exception {
		FileOutputStream fos = new FileOutputStream(outputPDFFile);
		PD4ML pd4ml = new PD4ML();
		pd4ml.setPageInsets(new Insets(20,10,10,10));
		pd4ml.setHtmlWidth(950);
		pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
		pd4ml.useTTF("java:fonts", true);
		pd4ml.setDefaultTTFs("KaiTi_GB2312", "KaiTi_GB2312", "KaiTi_GB2312");
		pd4ml.enableDebugInfo();
		pd4ml.render("file:" + inputHTMLFileName,fos);
	}
}
