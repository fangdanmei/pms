package cn.cebest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.cebest.entity.Contract;
import cn.cebest.framework.util.Result;
import cn.cebest.framework.util.ResultCode;
import cn.cebest.service.ContractService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/contract")
public class ContractController {

	@Value("${file.upload.path}")
	private String path;

	@Autowired
	private ContractService contractService;

	@ResponseBody
	@PostMapping("/synch")
	public Result synch(Contract contract, MultipartFile[] file) {
		// 文件上传
		List<String> fileNames = upload(file);
		if(fileNames != null && !fileNames.isEmpty()) {
			if(fileNames.size() == 1) {
				contract.setFileName(fileNames.get(0));
			} else {
				String fName = "";
				for (int i = 0; i < fileNames.size(); i++) {
					fName += fileNames.get(i) + "|";
				}
				fName = fName.substring(0,fName.length()-1);
				contract.setFileName(fName);
			}
		}
		contractService.insert(contract);
		return new Result(ResultCode.SUCCESS, contract);
	}

	/**
	 * 文件上传
	 * @param files
	 * @return
	 */
	public List<String> upload(MultipartFile[] files) {

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
