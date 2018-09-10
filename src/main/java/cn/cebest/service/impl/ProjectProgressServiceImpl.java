package cn.cebest.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cebest.dao.ProjectProgressMapper;
import cn.cebest.entity.ProjectProgressEntity;
import cn.cebest.service.ProjectProgressService;
import cn.cebest.util.PageResult;

@Service("projectProgressService")
public class ProjectProgressServiceImpl extends ServiceImpl<ProjectProgressMapper, ProjectProgressEntity> implements ProjectProgressService {

	@Override
	public PageResult queryPage(Map<String, Object> params) {
		Integer pageNum = (Integer)params.get("pageNum");
		Integer pageSize = (Integer)params.get("pageSize");
		String name = (String)params.get("name");
		String manager = (String)params.get("manager");
		String risk = (String)params.get("risk");
		String orderBy = (String)params.get("orderBy");
		Boolean isAsc = (Boolean)params.get("isAsc");
		
		PageHelper.startPage(pageNum, pageSize);
		List<ProjectProgressEntity> list = this.selectList(new EntityWrapper<ProjectProgressEntity>()
				.like(StringUtils.isNotBlank(name),"name", name)
				.like(StringUtils.isNotBlank(manager),"manager", manager)
				.eq(risk != null,"risk", risk)
				.orderBy(StringUtils.isNotBlank(orderBy), orderBy, isAsc));
		PageInfo p = new PageInfo(list);

		return new PageResult(p);
	}
	
	@Override
	public ProjectProgressEntity selectOne(ProjectProgressEntity entity) {
		return this.baseMapper.selectOne(entity);
	}

}
