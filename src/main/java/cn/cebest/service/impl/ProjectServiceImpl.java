package cn.cebest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.dao.ProjectMapper;
import cn.cebest.entity.Project;
import cn.cebest.param.ProjectQuery;
import cn.cebest.service.ProjectService;

@Service
public class ProjectServiceImpl extends ServiceImpl<BaseMapper<Project>,  Project> implements ProjectService{

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public Page<Project> selectListPage(ProjectQuery project) {
		// 当前页，总条数 构造 page 对象
		Page<Project> page = new Page<>(project.getPage(), project.getLimit());
        return page.setRecords(projectMapper.selectListPage(page, project));
	}

}
