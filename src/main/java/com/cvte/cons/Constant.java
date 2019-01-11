package com.cvte.cons;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cvte.entity.EyeInfo;

public class Constant {
	
	//public static String PATH = "C:/Users/CVTE/Desktop/dataset/iSee_multi_dataset";    //遍历数据
	public static int APPOINT_NUM = 900000;
	public static final int REPORT_TYPE_APP = 0;
	public static final int REPORT_TYPE_PC = 1;
	
	public static String PATH = "C:/Users/CVTE/Desktop/安装包";    //遍历数据
	
	public static String SRC = "C:/Users/CVTE/Desktop/src";   //虚拟数据文件夹路径
	
	//public static String DEST = "C:/Users/CVTE/Desktop/img";  //虚拟数据转移到的路径
	//public static String DEST = "/home/intern1/janliao/web/tomcat/webapps/GlaucomaCVTE/img";  //虚拟数据转移到的路径
	public static String DEST = "/home/intern1/janliao/web/tomcat/webapps/Glaucoma/img";  //虚拟数据转移到的路径
	//public static String DEST = "F:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GlaucomaCVTE/img";

	public  static LinkedList<String> L_Queue = new LinkedList<String>();   //存储左眼图
	
	public static LinkedList<String> L_Queue_Wait = new LinkedList<String>();  //特殊状况，左眼图候补(二级队列)
	
	public static List<String>  R_List = new ArrayList<String>();   //存储右眼图
	
	public static List<EyeInfo> ListL = new ArrayList<EyeInfo>();  //存储左眼图
	
	public static List<EyeInfo> ListR = new ArrayList<EyeInfo>();  //存储右眼图
	
	public static List<String> ImgAll = new ArrayList<String>();  //存储所有图片
	
	public static List<String> Data_List = new ArrayList<String>();  //存储待虚拟数据路径
	
//	public static String csvConsole = "/home/intern1/janliao/web/tomcat/webapps/GlaucomaCVTE/csvConsole";  //一张图片生成一个csv文件
//	public static String csvReport = "/home/intern1/janliao/web/tomcat/webapps/GlaucomaCVTE/csvReport";   //csv合成报告
//	public static String csvLog = "/home/intern1/janliao/web/tomcat/webapps/GlaucomaCVTE/csvLogger";
	public static String csvConsole = "/home/intern1/janliao/web/tomcat/webapps/Glaucoma/csvConsole";  //一张图片生成一个csv文件
	public static String csvReport = "/home/intern1/janliao/web/tomcat/webapps/Glaucoma/csvReport";   //csv合成报告
	public static String csvLog = "/home/intern1/janliao/web/tomcat/webapps/Glaucoma/csvLogger";
	
	//public static String ImgServerPath = "C:/Users/CVTE/Desktop/img";  //服务器保存图片路径  == ImgProcess 检查路径
	//public static String ImgServerPath = "/home/intern1/janliao/web/tomcat/webapps/GlaucomaCVTE/img";  //PC端传输图片保存路径
	//public static String ImgResultPath = "/home/intern1/janliao/web/tomcat/webapps/GlaucomaCVTE/imgResult";  //AI生成图片保存路径
	//public static String ImgServerPath = "F:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GlaucomaCVTE/img";
	public static String ImgServerPath = "/home/intern1/janliao/web/tomcat/webapps/Glaucoma/img";  //PC端传输图片保存路径
	public static String ImgResultPath = "/home/intern1/janliao/web/tomcat/webapps/Glaucoma/imgResult";  //AI生成图片保存路径
	
	public static int FLAG = 0;
	
	public static int QueueLock = 0;
	
	public static String imgL = "";
	public static String imgR = "";
	
	public static String infoL = "L";
	public static String infoR = "R";
	
	public static List<String> ResultList = new ArrayList<String>();
	public static List<Socket> SocketList = new ArrayList<Socket>();
	public static Map<String, Socket> SocketMap = new HashMap<String, Socket>();
	
	public static String StartCDR = "/home/sunxu/Works/deploy/glau-cdr/experiments/glau/demo_glau_tcp.sh";
	public static String StartQulity = "/home/intern1/yong/demo_quality/run_demo.sh";
	public static String StartPercent = "/home/intern1/wang/demo/run_demo.sh";
	public static String StartClient = "/home/intern1/yong/shell/demo_main.sh" ;
	//PDF  shell命令
	public static String StartPDF = "/home/sunxu/Works/deploy/glau-cdr/experiments/glau/deploy_pdf.sh";
	
	public static int Threshold = 50;   //有病阈值
	
	public static int RisFlag = 5;
	public static int NormalFlag = 10;
	
	public static int NoSex = 2;
	public static int Man = 0;
	public static int Woman = 1;
	
	public static String TERMINAL = "1000";
	
}
