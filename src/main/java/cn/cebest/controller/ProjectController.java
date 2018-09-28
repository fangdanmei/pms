package cn.cebest.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.cebest.entity.Contract;
import cn.cebest.entity.Project;
import cn.cebest.entity.ProjectContract;
import cn.cebest.framework.util.Result;
import cn.cebest.framework.util.ResultCode;
import cn.cebest.service.ContractService;
import cn.cebest.service.ProjectService;
import cn.cebest.service.ProjectContractService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private ProjectContractService projectContractService;
	
	@GetMapping("")
	public String index(ModelMap model){
		List<Contract> contracts = contractService.selectList(null);
		model.put("contracts", contracts);
		return "/project";
	}
	
	
	@ResponseBody
	@PostMapping("/synch")
	public Result synch(Project project){
		
		// 保存项目基本信息
		projectService.insert(project);
		String[] contractIds = project.getContractIds().split(",");
		List<ProjectContract> list = new ArrayList<>(contractIds.length);
		for (String contractId : contractIds) {
			ProjectContract projectContract = new ProjectContract();
			projectContract.setProjectId(project.getId());
			projectContract.setContractId(Integer.valueOf(contractId));
			list.add(projectContract);
		}
		
		// 批量插入项目合同关系
		projectContractService.insertBatch(list);
		return new Result(ResultCode.SUCCESS, project);
	}
	
}