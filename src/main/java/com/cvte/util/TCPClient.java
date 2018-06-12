package com.cvte.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

	public static void main(String[] args) throws UnknownHostException, IOException {  
        // TODO Auto-generated method stub  
               //1.建立TCP连接  
        String ip="172.17.195.44";   //服务器端ip地址  
        int port=10005;        //端口号  
        Socket sck=new Socket(ip,port);  
        //2.传输内容  
        String content="这是一个java模拟客户端";  
        byte[] bstream=content.getBytes("UTF-8");  //转化为字节流  
        OutputStream os=sck.getOutputStream();   //输出流  
        os.write(bstream);  
        //3.关闭连接  
        sck.close();  
    }  
}
