package com.cvte.util;

import java.io.DataInputStream;  
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;  
import java.net.Socket;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;

import java.io.*;

public class TCPServerQuality {

//	private Logger logger = Logger.getLogger(TCPServerQuality.class);
//	private boolean started = false;
//	private Socket socket;
//	private ServerSocket serverSocket;
//    private DataInputStream dataInputStream;
//    
//    public void startServer(){
//    	Logger logger = Logger.getLogger(TCPServerQuality.class);
//    	started = true;
//    	System.out.println("server is started!!!");
//    	logger.info("tcp qulity is started------");
//    	try {
//			serverSocket = new ServerSocket(12122);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}    
//    	
//    	try {
//			socket = serverSocket.accept();
//			logger.info("quality socket is connected = " + socket);
//			System.out.println("quality socket is connected = " + socket);
//			Constant.SocketList.add(socket);
//		} catch (IOException e1) {
//			logger.info("socket connect err");
//			if(socket != null) {
//				try {
//					socket.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			//重启serverSocket
//			restartSocket();
//			e1.printStackTrace();
//		}
//    	
//    	while(started) {
//    		try {
//				InputStream in = socket.getInputStream();
//			} catch (IOException e) {
//				logger.info("socket inputStream err");
//				if(socket != null) {
//					try {
//						socket.close();
//					} catch (IOException e2) {
//						e2.printStackTrace();
//					}
//				}
//				//重启serverSocket
//				restartSocket();
//				e.printStackTrace();
//			}
//    	}
//    	
//        try {    
//        	socket = serverSocket.accept(); 
//        	logger.info("qulity socket=" + socket);
//        	Constant.SocketList.add(socket);
//        	if(socket.isConnected()) {
//        		logger.info("qulity已连接!!!");
//        		System.out.println("qulity已连接!!!");
//        	}
//        	//sendMessage();
//            while(started) {
//            	//dataInputStream = new DataInputStream(socket.getInputStream());    
//            	//String message = GetMessageFromClient();  
//            	
//            	InputStream in = socket.getInputStream();  
//                byte[] buf = new byte[1024*256];  
//                //注意：read会产生阻塞  
//                int len = in.read(buf);  
//                String message = new String(buf, 0, len);
//                System.out.println("qulity=" + new String(buf,0,len)); 
//                Constant.ResultList.add(message);
//            	
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//        	logger.info("qulity tcp close=============");
//        	//sendMessage(1);
//            if(socket!=null){
//                try{
//                    socket.close();
//                }catch(IOException e) {
//                    e.printStackTrace();
//                }
//            }  
//        }  
//          
//    }   
//    
//    public void closeSocket() {
//    	if(Constant.SocketList.size() != 0) {
//    		for(int i = 0; i < Constant.SocketList.size(); i++) {
//    			if(Constant.SocketList.get(i) != null) {
//    				try {
//						Constant.SocketList.get(i).close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//    			}
//    		}
//    	}else {
//    		System.out.println("socket close");
//    	}
//    }
//    
//    public void restartSocket() {
//    	
//    }
//    
//    public void sendMessage(int close) {
//    	System.out.println(" send close message!");
//    	OutputStream os = null;
//		try {
//			os = socket.getOutputStream();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		PrintWriter pw = new PrintWriter(os);
//		System.out.println("send end!");
//		pw.println("close");
//		pw.flush();
//    }
//    
//    public void sendMessage() {
//    	System.out.println("start send message!");
//    	OutputStream os = null;
//		try {
//			os = socket.getOutputStream();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		PrintWriter pw = new PrintWriter(os);
//		System.out.println("send end!");
//		pw.println("/home/intern1/janliao/imgpath/u13_p13_2018_R_CON_1.jpg");
//		pw.flush();
//    }
//        
//    @SuppressWarnings("unused")
//	private String GetMessageFromClient(){    
//    	String message = null;
//        try {    
//            //获取消息的长度    
//            int length = dataInputStream.read();    
//            System.out.println("length=" + length);
//                
//            //获取消息    
//            byte[] body = new byte[length];    
//            dataInputStream.read(body);    
//                
//            message = new String(body);    
//            System.out.println("客户端说："+message);    
//        } catch (IOException e) {    
//            e.printStackTrace();    
//        }
//		return message;    
//    }    
//    public static void main(String[] args) {  
//        TCPServerQuality server = new TCPServerQuality();    
//        server.startServer();    
//    }  
}