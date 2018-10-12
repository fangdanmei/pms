package cn.cebest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cn.cebest.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件上传
 * 
 * @author maming
 * @date 2018年9月6日
 */

@Slf4j
@RestController
public class FileController {

	@Value("${file.upload.path}")
	private String path;

	@PostMapping("/upload")
	public Map<String, Object> upload(@RequestParam(value = "file", required = true) MultipartFile file)
			throws Exception {

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

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		result.put("code", 0);// 0表示成功，1失败
		result.put("msg", "上传成功");// 提示消息
		data.put("originalName", file.getOriginalFilename());
		data.put("fileName", fileName);
		result.put("data", data);
		return result;
	}

	
	
	@GetMapping(value = "/download/{fileName}/{originName}")
	public void download(@PathVariable("fileName") String fileName, @PathVariable("originName") String originName,
			HttpServletResponse response) {
		String[] strs = fileName.split("\\.");
		originName += "." + strs[strs.length - 1];
		String filePath = path + File.separator + fileName;
		try {
			// 下载文件
			FileUtil.fileDownload(response, filePath, originName);
		} catch (Exception e) {
			log.error("文件下载异常", e);
		}
	}

}
