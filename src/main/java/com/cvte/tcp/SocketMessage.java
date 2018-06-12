package com.cvte.tcp;

import java.io.IOException;
import java.net.*;

public class SocketMessage {

	static ServerSocket server = null;
	
	public static void startSocket(int port) throws IOException {
		Socket socket = null;
		server = new ServerSocket(port);
		socket = server.accept();
		
	}
	
}
