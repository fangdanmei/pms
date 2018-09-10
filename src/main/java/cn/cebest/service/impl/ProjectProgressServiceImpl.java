package cn.cebest.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cebest.dao.ProjectProgressMapper;
import cn.cebest.entity.ProjectEntity;
import cn.cebest.entity.ProjectProgressEntity;
import cn.cebest.service.ProjectProgressService;
import cn.cebest.service.ProjectService;
import cn.cebest.util.PageResult;

@Service("projectProgressService")
public class ProjectProgressServiceImpl extends ServiceImpl<ProjectProgressMapper, ProjectProgressEntity> implements ProjectProgressService {
	@Autowired
	private ProjectService projectService;
	@Override
	public PageResult<ProjectProgressEntity> queryPage(Map<String, Object> params) {
		Integer pageNum = Integer.valueOf((String) params.get("page"));
		Integer pageSize = Integer.valueOf((String)params.get("limit"));
		String name = (String)params.get("name");
		String manager = (String)params.get("manager");
		String risk = (String)params.get("risk");
		String orderBy = (String)params.get("orderBy");
		String sort = (String)params.get("sort");
		boolean isAsc = false;
		if(StringUtils.isNotEmpty(sort)){
			isAsc = sort.equalsIgnoreCase("asc") ? true : false;
		}
		PageHelper.startPage(pageNum, pageSize);
		List<ProjectProgressEntity> list = this.selectList(new EntityWrapper<ProjectProgressEntity>()
				.like(StringUtils.isNotBlank(name),"name", name)
				.like(StringUtils.isNotBlank(manager),"manager", manager)
				.eq(StringUtils.isNotBlank(risk),"risk", risk)
				.orderBy(true, orderBy, isAsc));
		PageInfo<ProjectProgressEntity> p = new PageInfo<ProjectProgressEntity>(list);

		return new PageResult<ProjectProgressEntity>(p);
	}
	
	@Override
	public ProjectProgressEntity selectOne(ProjectProgressEntity entity) {
		return this.baseMapper.selectOne(entity);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insertOrUpdate(ProjectProgressEntity entity) {
		ProjectEntity projectEntity = new ProjectEntity();
		projectEntity.setId(entity.getProjectId());
		ProjectEntity project = projectService.selectOne(projectEntity);
		// 设置冗余字段
		if (null != project) {
			entity.setName(project.getName());
			entity.setStandardOnlineTime(project.getStandardOnlineTime());
		}
		return super.insertOrUpdate(entity);
	}

}
