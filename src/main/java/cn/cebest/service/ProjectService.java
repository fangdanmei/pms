package cn.cebest.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.cebest.entity.Project;
import cn.cebest.param.ProjectQuery;

public interface ProjectService extends IService<Project>{
	
	/**
	 * 分页查询
	 * @param contract
	 * @return
	 */
	Page<Project> selectListPage(ProjectQuery project);

}
