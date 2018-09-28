package cn.cebest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.cebest.entity.Project;
import cn.cebest.framework.util.Result;
import cn.cebest.framework.util.ResultCode;
import cn.cebest.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	
	@Autowired
	private ProjectService projectService;
	
	@ResponseBody
	@PostMapping("/synch")
	public Result synch(Project project){
		projectService.insert(project);
		return new Result(ResultCode.SUCCESS, project);
	}
	
}
