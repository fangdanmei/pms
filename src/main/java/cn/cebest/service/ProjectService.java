package cn.cebest.service;

import com.baomidou.mybatisplus.service.IService;

import cn.cebest.entity.ProjectEntity;

public interface ProjectService extends IService<ProjectEntity> {
	/**
	 * 匹配属性查询实体
	 * 
	 * @param entity
	 * @return
	 */
	ProjectEntity selectOne(ProjectEntity entity);
}
