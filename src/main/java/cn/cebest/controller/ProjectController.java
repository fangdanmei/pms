package cn.cebest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cebest.entity.ProjectEntity;
import cn.cebest.framework.util.Result;
import cn.cebest.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	@PostMapping("selectById")
	@ResponseBody
	public ProjectEntity selectById(@RequestParam Long id){
		ProjectEntity project = projectService.selectById(id);
		return project;
	}
	
	@PostMapping("/insertOrUpdate")
	@ResponseBody
	public Result insertOrUpdate(ProjectEntity project){
		projectService.insertOrUpdate(project);
		return new Result();
	}
}
