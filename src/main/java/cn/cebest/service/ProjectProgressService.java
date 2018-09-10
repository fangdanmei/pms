package cn.cebest.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.cebest.entity.ProjectProgressEntity;
import cn.cebest.utils.PageResult;

public interface ProjectProgressService extends IService<ProjectProgressEntity> {
	/**
	 * 查询项目进度
	 * 
	 * @param params	查询参数
	 * @return
	 */
	PageResult queryPage(Map<String, Object> params);
}
