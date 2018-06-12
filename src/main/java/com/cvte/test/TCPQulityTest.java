package com.cvte.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;
import com.cvte.util.TCPServer;

public class TCPQulityTest {

	private boolean started = false;
	private Socket socket; 
	private ServerSocket serverSocket;    
    private DataInputStream dataInputStream;    
        
    public void startServer(){    
    	Logger logger = Logger.getLogger(TCPServer.class);
    	started = true;
    	System.out.println("server is started!!!");
    	logger.info("tcp Qulity is started!!!!");
    	try {
			serverSocket = new ServerSocket(12122);
		} catch (IOException e1) {
			e1.printStackTrace();
		}    
        try {    
        	socket = serverSocket.accept(); 
        	System.out.println(9999);
        	logger.info("qulity socket=" + socket);
        	Constant.SocketList.add(socket);
        	if(socket.isConnected()) {
        		logger.info("qulity已连接!!!");
        		System.out.println("qulity已连接!!!");
        	}
        	//sendMessage();
            while(started) {            	
            	InputStream in = socket.getInputStream();  
                byte[] buf = new byte[1024];  
                //注意：read会产生阻塞  
                int len = in.read(buf);  
                String message = new String(buf, 0, len);
                System.out.println("qulity=" + new String(buf,0,len)); 
                Constant.ResultList.add(message);

                if(!"".equals(message)) {
                	System.out.println("qulity message is not null!");
                }
            }
        } catch (IOException e) {    
            e.printStackTrace();    
        }finally{  
            if(socket!=null){  
                try{  
                    socket.close();  
                }catch(IOException e) {   
                    e.printStackTrace();   
                }  
            }  
        }  
          
    }    
    
    @SuppressWarnings("unused")
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
		pw.println("/home/intern1/jan/imgpath/u13_p13_2018_R_CON_1.jpg");
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
        TCPQulityTest server = new TCPQulityTest();    
        server.startServer();    
    }  
	
}
