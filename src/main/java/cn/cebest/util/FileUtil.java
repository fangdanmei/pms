package cn.cebest.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	
	
	/**
	 * 文件上传
	 * @param files
	 * @return
	 */
	public static List<String> upload(MultipartFile[] files, String path) {

		List<String> result = new ArrayList<>();
		try {
			if (files.length > 0) {
				for (MultipartFile file : files) {
					String[] strs = file.getOriginalFilename().split("\\.");
					// 组装文件名
					String fileName = new Date().getTime() + "." + strs[strs.length - 1];
					File targetFile = new File(path);

					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					FileOutputStream out = new FileOutputStream(path + fileName);
					out.write(file.getBytes());
					out.flush();
					out.close();
					// 添加文件名
					result.add(fileName);
				}
			}
		} catch (Exception e) {
			log.error("文件上传异常", e);
		}

		return result;
	}
	
	
	/**
	 * @param response 
	 * @param filePath		//文件完整路径(包括文件名和扩展名)
	 * @param fileName		//下载后看到的文件名
	 * @return  文件名
	 * @throws IOException 
	 */
	public static void fileDownload(final HttpServletResponse response, String filePath, String fileName) throws IOException{  
		   
		byte[] data = toByteArray(filePath);  
	    fileName = URLEncoder.encode(fileName.substring(0, fileName.lastIndexOf(".")), "UTF-8") + fileName.substring(fileName.lastIndexOf("."));  
	    response.reset();  
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.addHeader("Content-Length", "" + data.length);  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(data);  
	    outputStream.flush();  
	    outputStream.close();
	    response.flushBuffer();
	    
	} 
	
	
	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		ByteBuffer byteBuffer = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
			}
			
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			try {
				if(channel != null){
					channel.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			try {
				if(fs != null){
					fs.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return byteBuffer.array();
	}
}
