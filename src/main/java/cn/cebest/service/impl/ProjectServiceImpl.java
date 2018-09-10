package cn.cebest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.cebest.dao.ProjectMapper;
import cn.cebest.entity.ProjectEntity;
import cn.cebest.entity.ProjectProgressEntity;
import cn.cebest.service.ProjectProgressService;
import cn.cebest.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity> implements ProjectService {
	@Autowired
	private ProjectProgressService progressService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean insertOrUpdate(ProjectEntity entity) {
		ProjectProgressEntity progressEntity = new ProjectProgressEntity();
		progressEntity.setProjectId(entity.getId());
		ProjectProgressEntity progress = progressService.selectOne(progressEntity);
		// 设置冗余字段
		if (null != progress) {
			progress.setName(entity.getName());
			progress.setStandardOnlineTime(entity.getStandardOnlineTime());
			progressService.updateById(progress);
		}
		return super.insertOrUpdate(entity);
	}
}
