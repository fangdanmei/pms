package cn.cebest.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;

import cn.cebest.dao.ProjectProgressMapper;
import cn.cebest.entity.ProjectProgressEntity;
import cn.cebest.service.ProjectProgressService;
import cn.cebest.util.PageResult;
import cn.cebest.utils.PageUtils;
import cn.cebest.utils.Query;

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
		String orderByType = (String)params.get("orderByType");
		PageInfo p = new PageInfo(null);
		Page<ProjectProgressEntity> page = this.selectPage(
			new Query<ProjectProgressEntity>(params).getPage(),
			new EntityWrapper<ProjectProgressEntity>()
				.like(StringUtils.isNotBlank(name),"name", name)
				.like(StringUtils.isNotBlank(manager),"manager", manager)
				.eq(risk != null,"risk", risk)
		);

		return new PageResult(p);
	}


}
