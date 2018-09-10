package cn.cebest.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.cebest.entity.ProjectProgressEntity;
import cn.cebest.util.PageResult;

public interface ProjectProgressService extends IService<ProjectProgressEntity> {
	/**
	 * 查询项目进度
	 * 
	 * @param params	查询参数
	 * @return
	 */
	PageResult<ProjectProgressEntity> queryPage(Map<String, Object> params);

	/**
	 * 匹配属性查询实体
	 * 
	 * @param entity
	 * @return
	 */
	ProjectProgressEntity selectOne(ProjectProgressEntity entity);
	
	
}
