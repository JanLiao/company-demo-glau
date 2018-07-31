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

public class TCPServerCDR {

	private boolean started = false;
	private Socket socket; 
	private ServerSocket serverSocket;    
    private DataInputStream dataInputStream;
        
    public void startServer(){
    	Logger logger = Logger.getLogger(TCPServerCDR.class);
    	started = true;
    	System.out.println("server is started!!!");
    	logger.info("tcp cdr is started------");
    	try {
			serverSocket = new ServerSocket(12123);
		} catch (IOException e1) {
			e1.printStackTrace();
		}    
        try {    
        	socket = serverSocket.accept(); 
        	Constant.SocketList.add(socket);
        	logger.info("CDR socket=" + socket);
        	if(socket.isConnected()) {
        		System.out.println("CDR已连接!!!");
        		logger.info("CDR已连接!!!");
        	}
        	//sendMessage();
//            while(started) {
//            	//dataInputStream = new DataInputStream(socket.getInputStream());    
//            	//String message = GetMessageFromClient();  
//            	
//            	InputStream in = socket.getInputStream();  
//                byte[] buf = new byte[1024*256];  
//                //注意：read会产生阻塞  
//                int len = in.read(buf);  
//                String message = new String(buf, 0, len);
//                System.out.println("CDR=" + new String(buf,0,len)); 
//                if(!"zxcv".equals(message)) {      
//                	Constant.ResultList.add(message);
//                	System.out.println("cru size=" + Constant.ResultList.size());
//                }
//            	//sendMessage();
//                if(!"".equals(message)) {
//                	System.out.println("CDR message is not null!");
//                }
//            }
        } catch (IOException e) {    
            e.printStackTrace();    
        }
//        finally{  
//            if(socket!=null){  
//                try{  
//                    socket.close();  
//                }catch(IOException e) {   
//                    e.printStackTrace();   
//                }  
//            }  
//        } 
          
    }    
    
    private void sendMessage() {
    	System.out.println("start send message!");
    	OutputStream os = null;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(os);
		System.out.println("send end!");
		pw.println("/home/intern1/janliao/imgpath/u13_p13_2018_L_CON_1.jpg");
		pw.flush();
    }
        
    @SuppressWarnings("unused")
	private String GetMessageFromClient(){    
    	String message = null;
        try {    
            //获取消息的长度    
            int length = dataInputStream.read();    
            System.out.println("length=" + length);
                
            //获取消息    
            byte[] body = new byte[length];    
            dataInputStream.read(body);    
                
            message = new String(body);    
            System.out.println("客户端说："+message);    
        } catch (IOException e) {    
            e.printStackTrace();    
        }
		return message;    
    }    
    public static void main(String[] args) {  
        TCPServerCDR server = new TCPServerCDR();    
        server.startServer();    
    }  
}
