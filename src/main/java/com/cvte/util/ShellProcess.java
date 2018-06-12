package com.cvte.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ShellProcess {
	
	public static int processImg(String filePath) throws InterruptedException {
		Logger logger = Logger.getLogger(ShellProcess.class);
        Process process = null;
        int flag = 0;
        final String path = " /home/intern1/janliao/github/ewae/wae/dataset/iSee_multi_dataset/img_data_AMD/100143.jpg  ";
        if("".equals(filePath)) {
                System.out.println("there is no image!!");
        }else {
                List<String> processList = new ArrayList<String>();
                String path1 = "python /home/intern1/yong/image-quality/main.py  --data_path";
                String path2 = "--testOnly True --retrain /home/intern1/yong/image-quality/log_quality_DenseNet_161_multiclass_bs32_0319-2/model/best_model.pkl";
                try {
                        //process = Runtime.getRuntime().exec(path1 + "  " + filePath + "  " + path2);
                        process = Runtime.getRuntime().exec(path1 + "  " + filePath + "  " + path2);
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

                for (String line : processList) {
                        System.out.println(line);
                }
                
                for(String line:processList) {
    				logger.info("line======" + line);
    			}

                String[] result = processList.get(5).split("\\t");
                logger.info("result=length=" + result.length);
    			for(int i = 0; i < result.length; i++) {
    				logger.info("result:" + i + "=" + result[i]);
    			}
    			//System.out.println("result=" + result[1]);
    			logger.info("ququququ");
                flag = Integer.parseInt(result[1]);
        }
        return 1;
}
	

//	public static String getUtlByName(String filePath) {
//		Process process = null;
//		String flag = "";
//		final String path = " /home/intern1/janliao/github/ewae/wae/dataset/iSee_multi_dataset/img_data_AMD/100143.jpg  ";
//		if("".equals(filePath)) {
//			System.out.println("there is no image!!");
//		}else {
//			List<String> processList = new ArrayList<String>();
//			String path1 = "/home/sunxu/Works/deploy/glau-cdr/experiments/glau/demo_glau.sh";
//			try {
//				//process = Runtime.getRuntime().exec(path1 + "  " + filePath + "  " + path2);
//				process = Runtime.getRuntime().exec(path1 + "  " + filePath);
//				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//				String line = "";
//				while ((line = input.readLine()) != null) {
//					processList.add(line);
//				}
//				input.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			int len = processList.size();
////			String[] result4 = processList.get(len - 5).split("\\t");
////			String[] result3 = processList.get(len - 4).split("\\t");
////			String[] result = processList.get(len - 3).split("\\t");
////			String[] result1 = processList.get(len - 2).split("\\t");
////			String[] result2 = processList.get(len - 1).split("\\t");
//			
//			String[] result = processList.get(len - 5).split(" ");
//			String[] result1 = processList.get(len - 4).split(" ");
//			String[] result2 = processList.get(len - 3).split(" ");
//			String[] result3 = processList.get(len - 2).split(" ");
//			String[] result4 = processList.get(len - 1).split(" ");
//			//System.out.println(result4.length + "=" + result3.length + "=" + result.length + "=" + result1.length + "=" + result2.length);
//			flag = result[3] + "," + result1[3] + "," +result2[1] + "," + result3[4] + "," + result4[4];
//	}
//		return flag;
//}
	
	public static void main(String[] args) {
		//ShellProcess sp = new ShellProcess();
		String path = "/home/intern1/janliao/github/ewae/wae/dataset/iSee_multi_dataset/img_data_AMD/100143.jpg";
		//System.out.println(ShellProcess.getUtlByName(path));
		try {
			ShellProcess.processImg(path);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}