package cn.cebest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cebest.entity.ProjectProgressEntity;
import cn.cebest.service.ProjectProgressService;
import cn.cebest.util.PageResult;

@Controller
@RequestMapping("/progress")
public class ProjectProgressController {
	
	@Autowired
	private ProjectProgressService progressService;

	@PostMapping("selectById")
	@ResponseBody
	public ProjectProgressEntity selectById(@RequestParam Long id){
		ProjectProgressEntity prgress = progressService.selectById(id);
		return prgress;
	}
	
	@PostMapping("insertOrUpdate")
	@ResponseBody
	public ProjectProgressEntity insertOrUpdate(@RequestBody ProjectProgressEntity progress){
		progressService.insertOrUpdate(progress);
		return progress;
	}
	
	@GetMapping("/queryPage")
	@ResponseBody
	public PageResult<ProjectProgressEntity> queryPage(@RequestParam Map<String, Object> params){
		PageResult<ProjectProgressEntity> pageResult = progressService.queryPage(params);
		return pageResult;
	}
}
