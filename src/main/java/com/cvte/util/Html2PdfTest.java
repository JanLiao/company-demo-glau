package com.cvte.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

/**
 * @author jan
 * @date 2018年3月20日 上午8:19:28
 * @version V1.0 
 */
public class Html2PdfTest {

	public void testHtml2Pdf() throws Exception{
        //指定PDF的存放路径
        String outputFile = "C:/Users/CVTE/Desktop/test.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        //指定字体。为了支持中文字体
        fontResolver.addFont("C:/Users/CVTE/Desktop/chiness/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        StringBuffer html = new StringBuffer();

        html.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"></meta>\n" +
                "    <title>Issue Payment Receipt</title>\n" +
                "    <style type=\"text/css\">\n" +
                "    body {\n" +
                "        font-family: Arial Unicode MS;\n" +
                "    }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <img src=\"img_data_AMD/82012.jpg\" style=\"width:160px;height:80px;\"></img>\n" +
                "    <br/>\n" +
                "    <br/> 建设银行\n" +
                "    <br/> 12345678901\n" +
                "    <br/> 1000RMB\n" +
                "    <br/> 姓名:李四\n" +
                "    <br/> 单号:123456\n" +
                "    <br/>\n" +
                "</body>\n" +
                "</html>");
        renderer.setDocumentFromString(html.toString());
        // 解决图片的相对路径问题
        renderer.getSharedContext().setBaseURL("file:C:/Users/CVTE/Desktop/dataset/iSee_multi_dataset/img_data_AMD");
        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();
        renderer = null;
        os.close();
    }
	
	public static void main(String[] args) throws Exception {  
		Html2PdfTest pdf = new Html2PdfTest();
		pdf.testHtml2Pdf();
		
	    String inputFile = "F:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GlaucomaCVTE/WEB-INF/views/test2.jsp";  
	    String url = new File(inputFile).toURI().toURL().toString();  
	    String outputFile = "C:/Users/CVTE/Desktop/test1.pdf";  
	    System.out.println(url);  
	    OutputStream os = new FileOutputStream(outputFile);  
	    ITextRenderer renderer = new ITextRenderer();  
	    renderer.setDocument(url);  
	    // 解决中文支持问题  
	    ITextFontResolver fontResolver = renderer.getFontResolver();  
	    fontResolver.addFont("C:/Users/CVTE/Desktop/chiness/simsun.ttc",  
	        BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
	    // 解决图片的相对路径问题  
	    renderer.getSharedContext().setBaseURL("file:/F:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GlaucomaCVTE/");  
	    renderer.layout();  
	    renderer.createPDF(os);  
	    os.close();  
	  } 
}

