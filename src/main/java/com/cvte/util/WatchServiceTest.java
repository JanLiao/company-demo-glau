package com.cvte.util;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author jan
 * @date 2018年3月18日 下午3:43:24
 * @version V1.0 
 */
public class WatchServiceTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		final Path path = Paths.get("C:/Users/CVTE/Desktop/cvte");
		
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
					}else if(kind == StandardWatchEventKinds.ENTRY_DELETE) {
						System.out.println("deleted");
					}else if(kind == StandardWatchEventKinds.ENTRY_MODIFY) {
						System.out.println("modify=");
					}
					
					@SuppressWarnings("unchecked")
					final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
					Path filename = watchEventPath.context();
					System.out.println(kind + "---->" + filename + "," );
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
}
