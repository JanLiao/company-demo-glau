package com.cvte.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

public class ReadCSV {

	public static List<String[]> readCSV(String filePath) {  
		// 用来保存数据  
		ArrayList<String[]> list = new ArrayList<String[]>();  
	    try {  	        	        
	        // 定义一个CSV路径  
	        //String csvFilePath = "D://StemQ.csv";  
	        // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);  
	        CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));  
	        // 跳过表头 如果需要表头的话，这句可以忽略  
	        reader.readHeaders();  
	        // 逐行读入除表头的数据  
	        while (reader.readRecord()) {
	            System.out.println(reader.getRawRecord());
	            list.add(reader.getValues());
	        }
	        reader.close();
	          
	        // 遍历读取的CSV文件  
//	        for (int row = 0; row < list.size(); row++) {  
//	            // 取得第row行第0列的数据  
//	            String cell = list.get(row)[0];  
//	            System.out.println("------------>"+cell);  
//	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
	    
	    return list;
	}
	
	public static void main(String[] args) {
		String filePath = "E:/csvFile/2018-3-25/u01.csv";
		System.out.println(ReadCSV.readCSV(filePath));
		System.out.println(ReadCSV.readCSV(filePath).size());
	}
	
}
