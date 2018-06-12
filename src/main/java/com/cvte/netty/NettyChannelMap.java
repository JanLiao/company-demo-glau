package com.cvte.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;


public class NettyChannelMap { // 用于保存登录上来的客户端
	private static Map<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

	public static boolean containsKey(String terminalId) { //判断某个终端存在与否
		return map.containsKey(terminalId);
	}
	
	
	public static void add(String terminalId, SocketChannel socketChannel) {
		map.put(terminalId, socketChannel);
	}

	public static Channel get(String terminalId) {
		return map.get(terminalId);
	}

	public static void remove(SocketChannel socketChannel) {
		for (Map.Entry entry : map.entrySet()) {
			if (entry.getValue() == socketChannel) {
				map.remove(entry.getKey());
				break;
			}
		}
	}


	public static Map<String, SocketChannel> getMap() {
		return map;
	}


	public static void setMap(Map<String, SocketChannel> map) {
		NettyChannelMap.map = map;
	}

}
