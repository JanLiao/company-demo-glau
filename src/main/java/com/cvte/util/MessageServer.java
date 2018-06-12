package com.cvte.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageServer {

	private static boolean started = false;
		
	private static ServerSocket server = null;
	
	public static void main(String[] args) {
		new MessageServer().start();
	}
	
	public void start() {
		try {
			server  = new ServerSocket(12122);
			started = true;
			System.out.println("server is start!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			while(started) {
				Socket s= server.accept();
				Server service = new Server(s);
				new Thread(service).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class Server implements Runnable {
		private Socket s;
		private boolean bConnected = false;
		
		public Server(Socket s) {
			this.s = s;
			bConnected = true;
		}
		
		public void send(String str) throws IOException {
			OutputStream os=s.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println(str);
			pw.flush();
		}

		@Override
		public void run() {
			
			try {
				while (bConnected) {
					InputStream is=s.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String info="";
		            String temp=null;//临时变量
		            while((temp=br.readLine())!=null){
		                info+=temp;
		                //System.out.println("客户端接收服务端发送信息："+info);
		            }
					System.out.println("客户端说:" + info);
					Thread.sleep(5000);
					if(!"".equals(info)) {
						send("你好!!!sockethello!");
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (s != null) {
						s.close();
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
