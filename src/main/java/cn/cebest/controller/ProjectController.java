package cn.cebest.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import cn.cebest.entity.Contract;
import cn.cebest.entity.Project;
import cn.cebest.entity.ProjectContract;
import cn.cebest.entity.ProjectMember;
import cn.cebest.entity.ProjectPlan;
import cn.cebest.entity.ProjectProgress;
import cn.cebest.entity.ProjectRisk;
import cn.cebest.entity.User;
import cn.cebest.framework.util.FieldConvertUtil;
import cn.cebest.framework.util.Result;
import cn.cebest.framework.util.ResultCode;
import cn.cebest.param.ProjectQuery;
import cn.cebest.service.ContractService;
import cn.cebest.service.ProjectService;
import cn.cebest.service.UserService;
import cn.cebest.util.FileUtil;
import cn.cebest.util.PageParam;
import cn.cebest.util.PageResult;
import cn.cebest.service.ProjectContractService;
import cn.cebest.service.ProjectMemberService;
import cn.cebest.service.ProjectPlanService;
import cn.cebest.service.ProjectProgressService;
import cn.cebest.service.ProjectRiskService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Value("${file.upload.path}")
	private String path;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private ProjectContractService projectContractService;

	@Autowired
	private ProjectPlanService projectPlanService;

	@Autowired
	private ProjectProgressService projectProgressService;

	@Autowired
	private ProjectRiskService projectRiskService;

	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Autowired
	private UserService userService;

	@GetMapping("")
	public String index(ModelMap model) {
		List<Contract> contracts = contractService.selectList(null);
		model.put("contracts", contracts);
		List<User> users = userService.selectList(null);
		model.put("users", users);
		return "/project";
	}
	
	
	@GetMapping("/{projectId:\\d+}")
	public String detail(@PathVariable("projectId") Integer id, ModelMap model){
		Project project = projectService.selectById(id);
		model.put("project", project);
		List<User> users = userService.selectList(null);
		model.put("users", users);
		List<Contract> contracts = contractService.selectList(null);
		model.put("contracts", contracts);
		List<ProjectContract> projectContracts = projectContractService.selectList(new EntityWrapper<ProjectContract>().eq("PROJECT_ID", id));
		String contractIds = "";
		for (ProjectContract projectContract : projectContracts) {
			contractIds += projectContract.getContractId() +",";
		}
		
		if(StringUtils.isNotEmpty(contractIds)){
			contractIds = contractIds.substring(0, contractIds.length()-1);
		}
		
		model.put("contractIds", contractIds);
		return "/project";
	}
	
	
	@DeleteMapping("/plan/{id:\\d+}")
	@ResponseBody
	public Result deletePlan(@PathVariable("id") Integer id){
		projectPlanService.deleteById(id);
		return new Result();
	}
	
	
	@DeleteMapping("/progress/{id:\\d+}")
	@ResponseBody
	public Result deleteProgress(@PathVariable("id") Integer id){
		projectProgressService.deleteById(id);
		return new Result();
	}
	
	
	@DeleteMapping("/risk/{id:\\d+}")
	@ResponseBody
	public Result deleteRisk(@PathVariable("id") Integer id){
		projectRiskService.deleteById(id);
		return new Result();
	}
	
	
	@DeleteMapping("/member/{id:\\d+}")
	@ResponseBody
	public Result deleteMember(@PathVariable("id") Integer id){
		projectMemberService.deleteById(id);
		return new Result();
	}
	

	@ResponseBody
	@GetMapping("/list")
	public PageResult<Project> list(ProjectQuery param) {
		if(StringUtils.isNotEmpty(param.getOrderBy())) {
			param.setOrderBy(FieldConvertUtil.underscoreName(param.getOrderBy()).toUpperCase());
		}
		Page<Project> pageData = projectService.selectListPage(param);
		return new PageResult<Project>(pageData);
	}

	@ResponseBody
	@PostMapping("/synch")
	public Result synch(Project project) {

		// 保存项目基本信息
		projectService.insertOrUpdate(project);
		
		// 先删除
		projectContractService.delete(new EntityWrapper<ProjectContract> ().eq("PROJECT_ID", project.getId()));
		
		
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

	@ResponseBody
	@PostMapping("/plan/synch")
	public Result planSynch(ProjectPlan plan) {
		projectPlanService.insert(plan);
		return new Result();
	}

	@ResponseBody
	@GetMapping("/plans")
	public PageResult<ProjectPlan> plans(PageParam param, String projectId) {
		Page<ProjectPlan> pageData = projectPlanService.selectPage(
				new Page<ProjectPlan>(param.getPage(), param.getLimit()),
				new EntityWrapper<ProjectPlan>().eq("PROJECT_ID", projectId));
		return new PageResult<ProjectPlan>(pageData);
	}

	@ResponseBody
	@PostMapping("/progress/synch")
	public Result progressSynch(ProjectProgress progress, MultipartFile[] file) {
		// 文件上传
		List<String> fileNames = FileUtil.upload(file, path);
		progress.setFileName(fileNames.get(0));
		progress.setOriginName(file[0].getOriginalFilename());
		projectProgressService.insert(progress);
		return new Result();
	}

	@ResponseBody
	@GetMapping("/progress")
	public PageResult<ProjectProgress> progressList(PageParam param, String projectId) {
		Page<ProjectProgress> pageData = projectProgressService.selectPage(
				new Page<ProjectProgress>(param.getPage(), param.getLimit()),
				new EntityWrapper<ProjectProgress>().eq("PROJECT_ID", projectId));
		return new PageResult<ProjectProgress>(pageData);
	}

	@ResponseBody
	@PostMapping("/risk/synch")
	public Result riskSynch(ProjectRisk risk) {
		projectRiskService.insert(risk);
		return new Result();
	}

	@ResponseBody
	@GetMapping("/risks")
	public PageResult<ProjectRisk> riskList(PageParam param, String projectId) {
		Page<ProjectRisk> pageData = projectRiskService.selectPage(
				new Page<ProjectRisk>(param.getPage(), param.getLimit()),
				new EntityWrapper<ProjectRisk>().eq("PROJECT_ID", projectId));
		return new PageResult<ProjectRisk>(pageData);
	}

	@ResponseBody
	@PostMapping("/member/synch")
	public Result memberSynch(ProjectMember member) {
		projectMemberService.insert(member);
		return new Result();
	}

	@ResponseBody
	@GetMapping("/members")
	public PageResult<ProjectMember> memberList(PageParam param, String projectId) {
		Page<ProjectMember> pageData = projectMemberService.selectPage(
				new Page<ProjectMember>(param.getPage(), param.getLimit()),
				new EntityWrapper<ProjectMember>().eq("PROJECT_ID", projectId));
		return new PageResult<ProjectMember>(pageData);
	}
	
	@ResponseBody
	@PostMapping("/state/Chart")
	public Result statusChart() {
		List<Project> stateNum=projectService.stateChart();
		Result result=new Result();
		result.setData(stateNum);
		return result;
		
	}
	
	@ResponseBody
	@PostMapping("/progress/Chart")
	public Result progressChart() {
		List<Project> progressNum=projectService.progressChart();
		Result result=new Result();
		result.setData(progressNum);
		return result;
		
	}
}
