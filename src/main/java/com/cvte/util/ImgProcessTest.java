package com.cvte.util;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.apache.log4j.Logger;

import com.cvte.cons.Constant;

public class ImgProcessTest {
	

	@SuppressWarnings("unchecked")
	public static void process() {
		Logger log = Logger.getLogger(ImgProcessTest.class);
		final Path path = Paths.get(Constant.ImgServerPath);
		
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE
					, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);
			System.out.println("watch  start----->");
			log.info("检索文件开始-------");
			while(true){
				final WatchKey key = watchService.take();
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					final WatchEvent.Kind<?> kind = watchEvent.kind();
					if(kind == StandardWatchEventKinds.OVERFLOW) {
						System.out.println("OverFlow");
						continue;
					}else if(kind == StandardWatchEventKinds.ENTRY_CREATE) {
						System.out.println("create=====");				
						WatchEvent<Path> watch = (WatchEvent<Path>) watchEvent;
						Path name = watch.context();
						
						Thread.sleep(1500);
						//处理接收到的图片
						log.info("接收到的图片=" + name.toString());
						int size = Constant.ImgAll.size();
						if(size == 0) {
							Constant.ImgAll.add(name.toString());
						}else {
							int flag = 0;
							for(int i = 0; i < size; i++) {
								if((name.toString()).equals(Constant.ImgAll.get(i))) {
									flag = 1;
									break;
								}
							}
							if(flag == 0) {
								Constant.ImgAll.add(name.toString());
							}
						}
						
						log.info("cur size=" + Constant.ImgAll.size());
						
					}else if(kind == StandardWatchEventKinds.ENTRY_DELETE) {
						System.out.println("deleted");
					}else if(kind == StandardWatchEventKinds.ENTRY_MODIFY) {	
						
						WatchEvent<Path> watch = (WatchEvent<Path>) watchEvent;
						Path name = watch.context();
						File file = new File(Constant.DEST + "/" + name.toString());
						System.out.println("cr_length=" + file.length());
//						String[] message = name.toString().split("_");   //该出记得校验MD5验证完整性
//						if("L".equals(message[3])) {
//							Constant.imgL = name.toString();
//							System.out.println("zuo=" + name.toString());
//							log.info("检索到左眼图---");
//							System.out.println("检索到左眼图---");
//						}else if("R".equals(message[3])) {
//							Constant.imgR = name.toString();
//							System.out.println("yuo=" + name.toString());
//							log.info("检索到右眼图---");
//							System.out.println("检索到右眼图---");
//						}
																								
					}			
				}
				// reset the keyf
                boolean valid = key.reset();
                // exit loop if the key is not valid (if the directory was
                // deleted,for
                if (!valid) {
                    break;
                }
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static void main(String[] args) {
//		ImgProcess proc = new ImgProcess();
		ImgProcess.process();
	}
}
