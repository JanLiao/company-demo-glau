package com.cvte.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.csvreader.CsvWriter;
import com.cvte.cons.Constant;
import com.cvte.dao.impl.ImageDao;
import com.cvte.entity.Person;
import com.cvte.netty.NettyChannelMap;
import com.cvte.netty.msg.DataKey;
import com.cvte.netty.msg.ImgInfo;
import com.cvte.netty.msg.LoggerInfo;
import com.cvte.netty.msg.MsgType;
import com.cvte.netty.msg.ResultMsg;
import com.cvte.netty.msg.TransferMsg;

import io.netty.channel.ChannelHandlerContext;

public class SaveToCSV {
	
	//只保存LRPDF  单只眼LPDF和RPDF为空
	private static int flag = 0;   //1 save to mysql / 2 update to mysql(单只眼)  3 update to mysql(L+R)  4 update to mysql(LR)
	private static ImageDao imgDao = (ImageDao) SpringContextHelper.getBean("imgDao");
	
	public static void save(ImgInfo info, String pcnum, String datepath) {
		String csvFile = Constant.csvConsole;
		File f = new File(csvFile + "/" + pcnum + "/" + info.getUid());
		if(!f.exists()) {
			f.mkdirs();
		}
		
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		String filepath = csvFile + "/" + pcnum + "/" + info.getUid() + "/" + info.getUid() + "_" + formate.format(new Date())+ ".csv";
		File f3 = new File(filepath);
		if(!f3.exists()) {
			try {
				f3.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		writeCSVNew(info, filepath, pcnum);
		
	}
	
	public static void writeCSVNew(ImgInfo info, String filePath, String pcnum) {  
	    // 定义一个CSV路径  
	    try {  
	    	CsvWriter csvWriter = new CsvWriter(filePath,',',Charset.forName("UTF-8"));
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	       // CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        String[] csvHeaders = { "UID", "PID", "L/R","Original_img","Segent_img","OD","OC","CDR",
	        		"Zoom_img","Qulity", "AMD","DR","Glaucoma","Myopia"};  
	        csvWriter.writeRecord(csvHeaders);  
	        // 写内容  
	        String[] org = info.getOriginalImg().split("/", 2);
	        String orgPath = Constant.ImgServerPath + "/" + org[1];
	        String[] seg = info.getSegmentedImg().split("/", 2);
	        String[] zoom = info.getZoomImg().split("/", 2);
	        String segPath = Constant.ImgResultPath + "/" + seg[1];
	        String zoomPath = Constant.ImgResultPath + "/" + zoom[1];
	        String[] csvContent = {info.getUid(), info.getPid(), info.getLr(),orgPath, segPath
	        		,info.getOd(), info.getOc(), info.getCdr(), zoomPath,info.getQulity(), percentToFloat(info.getMyopia()),
	        		percentToFloat(info.getDr()), percentToFloat(info.getAmd()),  percentToFloat(info.getGlaucoma())};
	        
	        csvWriter.writeRecord(csvContent);  
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经写入--------");  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}

	public static void save(ImgInfo info) {
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+1;
		int date = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				
		
		String csvFile = Constant.csvConsole;
//		File f1 = new File(csvFile + "/" + year + "-" + month + "-" + date);
//		if(!f1.exists()) {
//			f1.mkdirs();
//		}
		
		File f4 = new File(csvFile + "/" + info.getUid());
		if(!f4.exists()) {
			f4.mkdirs();
		}
		
		//long timeStr = System.currentTimeMillis();
		String time = "" + year + month + date + hour + min + second;
		time = format.format(new Date());
		String filepath = csvFile + "/" + info.getUid() + "/" + info.getUid() + "_" + time+ ".csv";
		File f3 = new File(filepath);
		if(!f3.exists()) {
			try {
				f3.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		writeCSV(info, filepath);
				
//		String uid = info.getUid();
//		String filePath = csvFile + "/" + year + "-" + month + "-" + date + "/" + uid + ".csv";
//		File f2 = new File(filePath);
//		if(!f2.exists()) {
//			try {
//				f2.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			writeCSV(info, filePath);
//		}else {
//			appendCSV(info, filePath);
//		}
		
	}

	public static void writeCSV(ImgInfo info, String filePath) {  
	    // 定义一个CSV路径  
	    try {  
	    	CsvWriter csvWriter = new CsvWriter(filePath,',',Charset.forName("UTF-8"));
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	       // CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        String[] csvHeaders = { "UID", "PID", "L/R","Original_img","Segent_img","OD","OC","CDR",
	        		"Zoom_img","Qulity", "AMD","DR","Glaucoma","Myopia"};  
	        csvWriter.writeRecord(csvHeaders);  
	        // 写内容  
	        String[] org = info.getOriginalImg().split("/");
	        String orgPath = Constant.ImgServerPath + "/" + org[1];
	        String[] seg = info.getSegmentedImg().split("/");
	        String[] zoom = info.getZoomImg().split("/");
	        String segPath = Constant.ImgResultPath + "/" + seg[1];
	        String zoomPath = Constant.ImgResultPath + "/" + zoom[1];
	        String[] csvContent = {info.getUid(), info.getPid(), info.getLr(),orgPath, segPath
	        		,info.getOd(), info.getOc(), info.getCdr(), zoomPath,info.getQulity(), percentToFloat(info.getMyopia()),
	        		percentToFloat(info.getDr()), percentToFloat(info.getAmd()),  percentToFloat(info.getGlaucoma())};
	        
	        csvWriter.writeRecord(csvContent);  
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经写入--------");  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public static String percentToFloat(String percent) {
		float f = 0.00f;
		  if(percent.contains("%")) {
		     percent = percent.replaceAll("%", "");
		     f = Float.valueOf(percent);		  
		  }
		  return "" + f/100;
	}
	
	public static void appendCSVNew(ImgInfo info, String filePath, String pcnum) {  
	    // 定义一个CSV路径  
	    //String csvFilePath = "E://StemQ.csv";  
	    try {  
	    	 BufferedWriter out = new BufferedWriter(new 

	    	           OutputStreamWriter(new FileOutputStream(filePath,true),"UTF-8"),1024);
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	        CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        //String[] csvHeaders = { "编号", "姓名", "年龄" };  
	        //csvWriter.writeRecord(csvHeaders);  
	        // 写内容  
	        //String
	        String[] org = info.getOriginalImg().split("/", 2);
	        String orgPath = Constant.ImgServerPath + "/" + org[1];
	        String[] seg = info.getSegmentedImg().split("/", 2);
	        String[] zoom = info.getZoomImg().split("/", 2);
	        String segPath = Constant.ImgResultPath + "/" + seg[1];
	        String zoomPath = Constant.ImgResultPath + "/" + zoom[1];
	        String[] csvContent = {info.getUid(), info.getPid(), info.getLr(),orgPath, segPath  //青光眼  黄斑病风险  糖网病风险  病理性近视
	        		,info.getOd(), info.getOc(), info.getCdr(), zoomPath,info.getQulity(), percentToFloat(info.getMyopia()),
	        		percentToFloat(info.getDr()), percentToFloat(info.getAmd()),  percentToFloat(info.getGlaucoma())};
	        csvWriter.writeRecord(csvContent);
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经写入--------");  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public static void appendCSV(ImgInfo info, String filePath) {  
	    // 定义一个CSV路径  
	    //String csvFilePath = "E://StemQ.csv";  
	    try {  
	    	 BufferedWriter out = new BufferedWriter(new 

	    	           OutputStreamWriter(new FileOutputStream(filePath,true),"UTF-8"),1024);
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	        CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        //String[] csvHeaders = { "编号", "姓名", "年龄" };  
	        //csvWriter.writeRecord(csvHeaders);  
	        // 写内容  
	        //String
	        String[] org = info.getOriginalImg().split("/");
	        String orgPath = Constant.ImgServerPath + "/" + org[1];
	        String[] seg = info.getSegmentedImg().split("/");
	        String[] zoom = info.getZoomImg().split("/");
	        String segPath = Constant.ImgResultPath + "/" + seg[1];
	        String zoomPath = Constant.ImgResultPath  + "/" + zoom[1];
	        String[] csvContent = {info.getUid(), info.getPid(), info.getLr(),orgPath, segPath  //青光眼  黄斑病风险  糖网病风险  病理性近视
	        		,info.getOd(), info.getOc(), info.getCdr(), zoomPath,info.getQulity(), percentToFloat(info.getMyopia()),
	        		percentToFloat(info.getDr()), percentToFloat(info.getAmd()),  percentToFloat(info.getGlaucoma())};
	        csvWriter.writeRecord(csvContent);
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经写入--------");  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public static void report(ImgInfo img, String pcnum, String datepath, 
			ChannelHandlerContext ctx, String imgId) {
		Logger logger = Logger.getLogger(SaveToCSV.class);
		Person person = new Person();
		person.setUid(img.getUid());
		person.setTid(pcnum);
		if(new Random().nextInt(2) == 0) {
			person.setSex("男");
		}else {
			person.setSex("女");
		}
		if("L".equals(img.getLr())) {
			person.setLid(imgId);
		}else if("R".equals(img.getLr())) {
			person.setRid(imgId);
		}
		SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
		try {
			person.setPdfTime(forma.parse(datepath));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
				
		logger.info("开始生成report csv");
		String csvFile = Constant.csvReport;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		File f = new File(csvFile + "/" + pcnum + "/" + datepath);
		if(!f.exists()) {
			f.mkdirs();
		}
		String filepath = csvFile + "/" + pcnum + "/" + datepath + "/" + img.getUid() + ".csv";
		File f3 = new File(filepath);
		if(!f3.exists()) {
			try {
				//f3.createNewFile();
				//String[] ssss = filepath.split("[.]");
				new File(filepath).createNewFile();
				//filepath = ssss[0] + "_" + img.getLr() + ".csv";
				writeCSVNew(img, filepath, pcnum);
				flag = 1;
				//调用shell进行Run(孙旭    下面已调用)
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			List<String[]> list = ReadCSV.readCSV(filepath);
			logger.info("cur total csv=" + list);
			if(list.size() == 1) {
				if(img.getLr().equals(list.get(0)[2])) {
					if(Integer.parseInt(img.getQulity()) >= Integer.parseInt(list.get(0)[9])) {
						writeCSVNew(img, filepath, pcnum);
						flag = 2;
					}
				}else {
					appendCSVNew(img, filepath, pcnum);
					flag = 3;
				}
			}else if(list.size() == 2) {
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i)[2].equals(img.getLr())) {
						if(Integer.parseInt(img.getQulity()) >= Integer.parseInt(list.get(0)[9])) {
							list.remove(i);
							writeOne(list, filepath);
							appendCSVNew(img, filepath, pcnum);
							flag = 2;
						}
					}
				}
			}
										
			}
		//调用shell命令
		String pdfPath = "";
		
		List<String[]> list = ReadCSV.readCSV(filepath);
		String[] fs = filepath.split("[.]");
		String csvpath = "";
		File srcFile = new File(filepath);
		if(list.size() == 1) {
			csvpath = fs[0] + "_" + format.format(new Date()) + list.get(0)[2] + ".csv";
			File destFile = new File(fs[0] + "_" + format.format(new Date()) + list.get(0)[2] + ".csv");
			//srcFile.renameTo(destFile);  方式不行
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(list.size() == 2) {
			csvpath = fs[0] + "_" + format.format(new Date()) + "LR.csv";
			File destFile = new File(fs[0] + "_" + format.format(new Date()) + "LR.csv");
			//srcFile.renameTo(destFile);  方式不行
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ShellPDF.generatePDF(csvpath);
		
		//pdfPath = filepath;
		String[] ss = csvpath.split("[.]");
		pdfPath = ss[0] + ".pdf";
		logger.info("pdfPath=" + pdfPath);
		String[] orgname = img.getOriginalImg().split("/");
		logger.info("orgname=" + img.getOriginalImg());
		
		Date start = new Date();
		while(true) {
			File file = new File(pdfPath);
			if(file.exists()) {
				break;
			}
			Date end = new Date();
			if((end.getTime() - start.getTime()) / 1000 > 5) {
				break;
			}
		}
		
		File pdfF = new File(pdfPath);
		if(pdfF.exists()) {
			//传输pdf到PC端
			logger.info("start transfer PC");
			transferPDF(pdfPath, orgname[orgname.length - 1], ctx);
			logger.info("transfer end====");
			//保存到数据库Person
			imgDao.savePerson(person, img.getLr(), pdfPath, flag);
		}else {
			logger.info("pdfF is not exist========");
		}
		
	}

	public static void report(ImgInfo img) {
		Logger logger = Logger.getLogger(SaveToCSV.class);
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+1;
		int date = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
				
		logger.info("开始生成report csv");
		String csvFile = Constant.csvReport;
//		File f1 = new File(csvFile + "/" + year + "-" + month + "-" + date);
//		if(!f1.exists()) {
//			f1.mkdirs();
//		}
		
		long timeStr = System.currentTimeMillis();
//		File fi = new File(csvFile + "/" + img.getUid());
//		if(!fi.exists()) {
//			fi.mkdirs();
//		}
		
		String time = "" + year + month + date + hour + min + second;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		time = format.format(new Date());
		String filepath = csvFile + "/" + img.getUid() + ".csv";
		File f3 = new File(filepath);
		if(!f3.exists()) {
			try {
				//f3.createNewFile();
				//String[] ssss = filepath.split("[.]");
				new File(filepath).createNewFile();
				//filepath = ssss[0] + "_" + img.getLr() + ".csv";
				writeCSV(img, filepath);
				//调用shell进行Run(孙旭    下面已调用)
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			List<String[]> list = ReadCSV.readCSV(filepath);
			logger.info("cur total csv=" + list);
			if(list.size() == 1) {
				if(img.getLr().equals(list.get(0)[2])) {
					if(Integer.parseInt(img.getQulity()) >= Integer.parseInt(list.get(0)[9])) {
						writeCSV(img, filepath);
					}
				}else {
					appendCSV(img, filepath);
				}
			}else if(list.size() == 2) {
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i)[2].equals(img.getLr())) {
						if(Integer.parseInt(img.getQulity()) >= Integer.parseInt(list.get(0)[9])) {
							list.remove(i);
							writeOne(list, filepath);
							appendCSV(img, filepath);
						}
					}
				}
			}
										
			}
		//调用shell命令
		String pdfPath = "";
		
		List<String[]> list = ReadCSV.readCSV(filepath);
		String[] fs = filepath.split("[.]");
		String csvpath = "";
		File srcFile = new File(filepath);
		if(list.size() == 1) {
			csvpath = fs[0] + "_" + format.format(new Date()) + list.get(0)[2] + ".csv";
			File destFile = new File(fs[0] + "_" + format.format(new Date()) + list.get(0)[2] + ".csv");
			//srcFile.renameTo(destFile);  方式不行
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(list.size() == 2) {
			csvpath = fs[0] + "_" + format.format(new Date()) + "LR.csv";
			File destFile = new File(fs[0] + "_" + format.format(new Date()) + "LR.csv");
			//srcFile.renameTo(destFile);  方式不行
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ShellPDF.generatePDF(csvpath);
		
//		//传输处理日志   已放到serverhandler处理
//		Date day=new Date();    
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String[] orgname = img.getOriginalImg().split("/");
//		String[] sss = csvpath.split("/");
//		LoggerInfo log = new LoggerInfo(orgname[orgname.length - 1], sss[sss.length - 1], "true", df.format(day));
//		System.out.println("start transfer logger=" + log);
//		logger.info("start transfer logger=" + log);
//		//传输日志处理信息到PC端
//		ResultMsg resultMsg = new ResultMsg(true, MsgType.ImgMessage);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put(DataKey.ImgMessage, log);
//		resultMsg.setData(map);
//		NettyChannelMap.get(DataKey.Account).writeAndFlush(resultMsg);
//		logger.info("logger transfer is over");
		
		//传输pdf到PC端
		logger.info("start transfer PC");
		//pdfPath = filepath;
		String[] ss = csvpath.split("[.]");
		pdfPath = ss[0] + ".pdf";
		logger.info("pdfPath=" + pdfPath);
		String[] orgname = img.getOriginalImg().split("/");
		File pdfF = new File(pdfPath);
		if(pdfF.exists()) {
			transferPDF(pdfPath, orgname[orgname.length - 1]);
			logger.info("transfer end====");
		}
		
	}
	
	private static void writeOne(List<String[]> list, String filepath) {
		 // 定义一个CSV路径  
	    try {
	    	CsvWriter csvWriter = new CsvWriter(filepath,',',Charset.forName("UTF-8"));
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	       // CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        String[] csvHeaders = { "UID", "PID", "L/R","Original_img","Segent_img","OD","OC","CDR",
	        		"Zoom_img","Qulity", "AMD","DR","Glaucoma","Myopia"};
	        csvWriter.writeRecord(csvHeaders);  
	        // 写内容  
	        String[] csvContent = {list.get(0)[0], list.get(0)[1], list.get(0)[2],list.get(0)[3], list.get(0)[4]
	        		,list.get(0)[5], list.get(0)[6], list.get(0)[7], list.get(0)[8],list.get(0)[9],
	        		list.get(0)[10],  list.get(0)[11], list.get(0)[12], list.get(0)[13]};
	        
	        csvWriter.writeRecord(csvContent);  
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经写入--------");  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		
	}
	
	public static void transferPDF(String filePath, String filename, ChannelHandlerContext ctx) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		byte[] attachment = FileUtil.fileToBytes(filePath);
		System.out.println("attach=" + attachment.length);
		logger.info("attachment=" + attachment);
		
		TransferMsg transferMsg = new TransferMsg();
		transferMsg.setMsgType(MsgType.ImgTransfer);
		transferMsg.setAttachment(FileUtil.gZip(attachment));
		String[] str = filePath.split("/");
		transferMsg.setFileName(str[str.length - 1] + "," + filename);
		
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setReplyType(MsgType.ImgTransfer);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imgdata", transferMsg);
		resultMsg.setData(map);
		ctx.writeAndFlush(resultMsg);
		logger.info("send img final");
	}

	private static void transferPDF(String filePath, String filename) {
		Logger logger = Logger.getLogger(ProcessImage.class);
		byte[] attachment = FileUtil.fileToBytes(filePath);
		System.out.println("attach=" + attachment.length);
		logger.info("attachment=" + attachment);
		
		TransferMsg transferMsg = new TransferMsg();
		transferMsg.setMsgType(MsgType.ImgTransfer);
		transferMsg.setAttachment(FileUtil.gZip(attachment));
		String[] str = filePath.split("/");
		transferMsg.setFileName(str[str.length - 1] + "," + filename);
		
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setReplyType(MsgType.ImgTransfer);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imgdata", transferMsg);
		resultMsg.setData(map);
		NettyChannelMap.get(DataKey.Account).writeAndFlush(resultMsg);
		logger.info("send img final");
	}
	
	private static void writeLoggerCSV(LoggerInfo log, String logPath) {
		Logger logger = Logger.getLogger(SaveToCSV.class);
		CsvWriter csvWriter = new CsvWriter(logPath);
		String[] csvHeaders = {"img_name", "csvLog_path", "is_processed", "process_time"};
		try {
			csvWriter.writeRecord(csvHeaders);
			
			String[] csvContent = {log.getFileName(), log.getReportName(), log.getIsProcessed(), log.getProcessTime()};
			csvWriter.writeRecord(csvContent);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		csvWriter.close();
		System.out.println("csv Logger 写入成功");
		
	}
	
	private static void appendLoggerCSV(LoggerInfo log, String logpath) {
		try {
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logpath, true), "UTF-8"), 1024);
			CsvWriter csvWriter = new CsvWriter(bw, ',');
			
			String[] csvContent = {log.getFileName(), log.getReportName(), log.getIsProcessed(), log.getProcessTime()};
			csvWriter.writeRecord(csvContent);
			
			csvWriter.close();
			System.out.println("logger csv 成功追加");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void writeCSV(List<String[]> list, String filePath) {
		
		Logger logger = Logger.getLogger(SaveToCSV.class);
		// 定义一个CSV路径  
	    try {  
	    	CsvWriter csvWriter = new CsvWriter(filePath,',',Charset.forName("UTF-8"));
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	       // CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        String[] csvHeaders = { "UID", "PID", "L/R","Original_img","Segent_img","OD","OC","CDR",
	        		"Zoom_img","Qulity", "AMD","DR","Glaucoma","Myopia"};  
	        csvWriter.writeRecord(csvHeaders);  
	        // 写内容  
	        
	        for(int i = 0; i < list.size(); i++) {
	        	String[] seg = list.get(i)[4].split("/");
	 	        String[] zoom = list.get(i)[8].split("/");
	 	        String segPath = Constant.ImgResultPath + "/" + seg[1];
	 	        String zoomPath = Constant.ImgResultPath + "/" + zoom[1];
	 	        String[] csvCon = list.get(i);
	 	        csvCon[4] = segPath;
	 	        csvCon[8] = zoomPath;
	 	        csvCon[10] = percentToFloat(list.get(i)[10]);
	 	        csvCon[11] = percentToFloat(list.get(i)[11]);
	 	        csvCon[12] = percentToFloat(list.get(i)[12]);
	 	        csvCon[13] = percentToFloat(list.get(i)[13]);
	 	        logger.info("cur csv=" + csvCon);
	 	        csvWriter.writeRecord(csvCon);
	        }
	       
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经写入--------");  
	        logger.info("完成写入");
	    } catch (IOException e) {
	        e.printStackTrace();  
	    }  
		
	}

	public static void writeCSV(LoggerInfo log) {
		String[] str = log.getReportName().split("[.]");
		File file = new File(Constant.csvLog + "/" + str[0] + ".csv");
		log.setReportName(Constant.csvLog + "/" + str[0] + ".csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			writeLoggerCSV(log, Constant.csvLog + "/" + str[0] + ".csv");
		}else {
			appendLoggerCSV(log, Constant.csvLog + "/" + str[0] + ".csv");
		}
		
	}

}
