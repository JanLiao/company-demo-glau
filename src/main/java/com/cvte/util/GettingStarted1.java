package com.cvte.util;

import java.awt.Insets;  
import java.io.File;  
import java.io.IOException;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.security.InvalidParameterException;  
  
import org.zefer.pd4ml.PD4Constants;  
import org.zefer.pd4ml.PD4ML;  
  
public class GettingStarted1 {  
    protected int topValue = 10;  
    protected int leftValue = 20;  
    protected int rightValue = 10;  
    protected int bottomValue = 10;  
    protected int userSpaceWidth = 1300;  
  
    public static void main(String[] args) {  
        try {  
            GettingStarted1 jt = new GettingStarted1();  
            jt.doConversion("http://pd4ml.com/sample.htm", "C:/Users/CVTE/Desktop/pdf.pdf");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void doConversion( String url, String outputPath )   
                throws InvalidParameterException, MalformedURLException, IOException {  
        File output = new File(outputPath);  
        java.io.FileOutputStream fos = new java.io.FileOutputStream(output);  
  
        PD4ML pd4ml = new PD4ML();  
              
        pd4ml.setHtmlWidth(userSpaceWidth); // set frame width of "virtual web browser"   
              
        // choose target paper format and "rotate" it to landscape orientation  
        pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));   
              
        // define PDF page margins  
        pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));   
  
        // source HTML document also may have margins, could be suppressed this way   
        // (PD4ML *Pro* feature):  
        pd4ml.addStyle("BODY {margin: 0}", true);  
              
        // If built-in basic PDF fonts are not sufficient or   
        // if you need to output non-Latin texts,  
        // TTF embedding feature should help (PD4ML *Pro*)  
        pd4ml.useTTF("java:fonts", true);  
  
        pd4ml.render(new URL(url), fos); // actual document conversion from URL to file  
        fos.close();  
              
        System.out.println( outputPath + "\ndone." );  
    }  
}  
