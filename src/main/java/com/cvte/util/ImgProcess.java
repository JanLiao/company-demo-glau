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

import com.cvte.cons.Constant;

public class ImgProcess {
	

	@SuppressWarnings("unchecked")
	public static void process() {
		final Path path = Paths.get(Constant.DEST);
		
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE
					, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);
			System.out.println("watch  start----->");
			while(true){
				final WatchKey key = watchService.take();
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					final WatchEvent.Kind<?> kind = watchEvent.kind();
					if(kind == StandardWatchEventKinds.OVERFLOW) {
						System.out.println("OverFlow");
						continue;
					}else if(kind == StandardWatchEventKinds.ENTRY_CREATE) {
						System.out.println("create=====");
						
//						WatchEvent<Path> watch = (WatchEvent<Path>) watchEvent;
//						Path name = watch.context();
//						File file = new File(Constant.DEST + "/" + name.toString());
//						System.out.println("cr_length=" + file.length());
//						String[] message = name.toString().split("_");   //该出记得校验MD5验证完整性
//						if(message.length < 7) {
//							System.out.println("文件名有误");
//						}else {
//							if("L".equals(message[3])) {
//								Constant.L_Queue.add(name.toString());
//							}else if ("R".equals(message[3])) {
//								//Constant.R_List.add(name.toString());
//								Constant.R_List.add(message[0] + "_" + message[1]);
//							}
//							//System.out.println("name=" + name.toString() + "=" + message[6] + "==" + message[7]);
//							int md5Check = MDCheck.checkMd5(message[6], message[7], Constant.DEST + "/" + name.toString());
//							if(md5Check == 0) System.out.println("true");
//							else if(md5Check == 1) System.out.println("false");
//							else System.out.println("false");
//							//System.out.println("curr--name=" + name + "===" + name.toString() + ",size=" + Constant.L_Queue.size());
//						}						
					}else if(kind == StandardWatchEventKinds.ENTRY_DELETE) {
						System.out.println("deleted");
					}else if(kind == StandardWatchEventKinds.ENTRY_MODIFY) {
						//System.out.println("modify=");
//						File file = new File(Constant.DEST + "/" + watchEvent.context().toString());
//						System.out.println("mo_length=" + file.length());
						
						WatchEvent<Path> watch = (WatchEvent<Path>) watchEvent;
						Path name = watch.context();
						File file = new File(Constant.DEST + "/" + name.toString());
						System.out.println("cr_length=" + file.length());
						String[] message = name.toString().split("_");   //该出记得校验MD5验证完整性
						if(message.length < 7) {
							System.out.println("文件名有误");
						}else {
							if("L".equals(message[3])) {
								Constant.L_Queue.add(name.toString());
							}else if ("R".equals(message[3])) {
								//Constant.R_List.add(name.toString());
								Constant.R_List.add(message[0] + "_" + message[1]);
							}
							//System.out.println("name=" + name.toString() + "=" + message[6] + "==" + message[7]);
//							int md5Check = MDCheck.checkMd5(message[6], message[7], Constant.DEST + "/" + name.toString());
//							if(md5Check == 0) System.out.println("true");
//							else if(md5Check == 1) System.out.println("false");
//							else System.out.println("false");
							//System.out.println("curr--name=" + name + "===" + name.toString() + ",size=" + Constant.L_Queue.size());
						}
						
					}
					
//					final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
//					Path filename = watchEventPath.context();
//					System.out.println(kind + "---->" + filename + ",then=" + new File(Constant.DEST + "/" + filename.toString()).length());
//					System.out.println();
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
