package com.cvte.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

//import com.cvte.cons.Constant;
//import com.cvte.test.ShellCDRTest;
//import com.cvte.test.ShellPercentTest;
//import com.cvte.test.ShellQulityTest;
//import com.cvte.test.TCPCDRTest;
//import com.cvte.test.TCPPercentTest;
//import com.cvte.test.TCPQulityTest;
//import com.cvte.util.ImgProcessTest;
//import com.cvte.util.ProcessImage;
//import com.cvte.util.ProcessImg;
//import com.cvte.util.ShellCDR;
//import com.cvte.util.ShellPercent;
//import com.cvte.util.ShellQulity;
//import com.cvte.util.StartClient;
import com.cvte.util.TCPServer;
import com.cvte.util.TCPServerCDR;
import com.cvte.util.TCPServerPercent;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;


public class NettyServer implements ServletContextListener {  //用于tomcat启动时，启动netty服务器

	
	private Thread myThread;
	private Thread qulityThread;  //开启qulity  tcp服务
	private Thread cdrThread;      //开启cdr tcp服务
	private Thread percentThread; //开启 筛查tcp服务
	private Thread imgThread;  //AI处理结果
	private ExecutorService executor;
	
	/**
	 * @param arg0
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		myThread = new Thread(new Runnable() {
			@Override
			public void run() {
				startNettyServer();    //启动netty服务器
			}
		});
		myThread.start();
		
		qulityThread = new Thread(new Runnable() {

			@Override
			public void run() {
				new TCPServer().startServer();
			}
			
		});
		qulityThread.start();
		
		percentThread = new Thread(new Runnable() {

			@Override
			public void run() {
				new TCPServerPercent().startServer();
			}
			
		});
		percentThread.start();
		
		cdrThread = new Thread(new Runnable() {

			@Override
			public void run() {
				new TCPServerCDR().startServer();
			}
			
		});
		cdrThread.start();
		
//		executor = Executors.newFixedThreadPool(4);
//		executor.submit(new Runnable() {
//
//			@Override
//			public void run() {
//				startNettyServer();    //启动netty服务器
//			}
//			
//		});
//		
//		executor.submit(new Runnable() {
//			
//			@Override
//			public void run() {
//				new TCPServerCDR().startServer();
//			}
//			
//		});
//		
//		executor.submit(new Runnable() {  //启动percent tcp服务
//
//			@Override
//			public void run() {
//				new TCPServerPercent().startServer();
//			}
//			
//		});
//		
//		executor.submit(new Runnable() {  //quality tcp启动
//
//			@Override
//			public void run() {
//				TCPServer tcp = new TCPServer();
//				tcp.startServer();
//			}
//			
//		});
		
		Logger logger = Logger.getLogger(NettyServer.class);
		logger.info("start connection tcp");
		
	}
	
	

	private final int PORT = Integer.parseInt(System.getProperty("port", "12125"));
	
	private final AcceptorIdleStateTrigger idleStateTrigger = new AcceptorIdleStateTrigger();  
	
	
	private void startNettyServer() {    //启动netty服务器
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             //.handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new IdleStateHandler(65, 0, 0, TimeUnit.SECONDS));  //连接上来 的客户端65秒内，没有响应就检查是否是空连接 
                    p.addLast(idleStateTrigger);  
                    
                    p.addLast(
                            new ObjectEncoder(),
                            new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)),
                            new NettyServerHandler());
                }
             }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            b.bind(PORT).sync().channel().closeFuture().sync();
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        
        System.out.println("close ObjectEchoServer");
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {   //关闭时回收资源
		Logger logger = Logger.getLogger(NettyServer.class);
		
//		try {
//			executor.shutdown();
//			executor.awaitTermination(30, TimeUnit.SECONDS);
//			logger.info("all tcp server thread is interrupt====");
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		myThread.interrupt();
		qulityThread.interrupt();
		percentThread.interrupt();
		cdrThread.interrupt();
	}

}
