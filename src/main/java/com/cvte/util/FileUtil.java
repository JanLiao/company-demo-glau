package com.cvte.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

/**
 * @author: yezilong
 */

public class FileUtil {

	/**
	 * 根据文件后缀名判断 文件是否是视频文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return 是否是视频文件

	public static boolean isVedioFile(String fileName) {
		try {
			if (StringUtil.isEmpty(fileName))
				return false;
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			// 创建视频类型数组
			String video[] = Constant.supportVideoType;
			for (int i = 0; i < video.length; i++) {
				if (video[i].equals(fileType))
					return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	 */

	/**
	 * NIO way
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] fileToBytes(String filePath) {

		File f = new File(filePath);
		if (!f.exists()) {
			System.out.println("filePath: " + filePath + " file not exist");
			return null;
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
			}
			return byteBuffer.array();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {

				}
			}
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {

				}
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] fileToBytes2(String filePath) {
		/// 验证
		if (filePath == null || filePath.length() <= 0)
			return null;

		File file = new File(filePath);

		if (!file.exists()) {
			System.out.println("filePath: " + filePath + " file not exist");
			return null;
		}

		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}

		// 开始将文件转成byte数组
		FileChannel fc = null;
		try {
			fc = new RandomAccessFile(filePath, "r").getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean bytesToFile(byte[] bytes, String directoryPath, String fileName) {
		Logger logger = Logger.getLogger(FileUtil.class);
		FileOutputStream fos = null;
		try {
			// 判断目录是否存在，不存在就创建
			File directoryFile = new File(directoryPath);
			if (!directoryFile.exists()) {
				directoryFile.mkdir();
				logger.info("创建成功=====");
			}
				

			// 如果要生成的文件，原先没有就新创建一个
			File file = new File(directoryPath + "/" + fileName);
			// if (!file.exists()) file.createNewFile();

			// 开始生成文件
			fos = new FileOutputStream(file, false); // 为false，则上述代码是重新生成文件，会覆盖之前的文件内容。为true，则表示追加到文件中写入。
			FileChannel fc = fos.getChannel();

			ByteBuffer bbf = ByteBuffer.wrap(bytes);
			bbf.put(bytes);
			bbf.flip();
			fc.write(bbf);

			fc.close();
			fos.flush();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/***
	 * 压缩GZip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] gZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/***
	 * 解压GZip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

}
