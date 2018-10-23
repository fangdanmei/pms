package cn.cebest.service;

import java.util.List;

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
	
	/**
	 * 查询项目状态柱状图数据
	 * */
	List<Project> stateChart();
	/**
	 * 查询项目进度柱状图数据
	 * */
	List<Project> progressChart();

}
