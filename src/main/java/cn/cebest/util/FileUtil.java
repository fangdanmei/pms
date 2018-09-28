package cn.cebest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
}
