package com.cvte.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.apache.commons.io.FileUtils;

import com.cvte.cons.Constant;

public class ImgTransfer {

	@SuppressWarnings("unchecked")
	public void watchToTransfer() {
		
		final Path path = Paths.get(Constant.SRC);
		
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE
					, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);
			System.out.println("start----->");
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
						//File file = new File(Constant.SRC+ "/" + name.toString());
						//System.out.println("length=" + file.length());
						String filename = name.toString();
						File srcFile = new File(Constant.SRC + "/" + filename);
						File destFile = new File(Constant.DEST + "/" + filename);
						FileUtils.copyFile(srcFile, destFile);
						//Constant.L_Queue.add(name.toString());
						//System.out.println("curr--name=" + "" + name + "===" + name.toString() + ",size=" + Constant.L_Queue.size());
					}else if(kind == StandardWatchEventKinds.ENTRY_DELETE) {
						System.out.println("deleted");
					}else if(kind == StandardWatchEventKinds.ENTRY_MODIFY) {
						System.out.println("modify=");
						WatchEvent<Path> watch = (WatchEvent<Path>) watchEvent;
						Path name = watch.context();
						//File file = new File(Constant.SRC + "/" + name.toString());
						//System.out.println("length=" + file.length());
					}
					
//					final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
//					Path filename = watchEventPath.context();
//					System.out.println(kind + "---->" + filename);
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
		ImgTransfer img = new ImgTransfer();
		img.watchToTransfer();
	}
}
