package cn.cebest.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.cebest.entity.MilestoneFileEntity;

public interface MilestoneFileService extends IService<MilestoneFileEntity> {
	/**
	 * 查询里程碑文件
	 * 
	 * @param projectId 项目ID
	 * @return
	 */
	List<MilestoneFileEntity> selectByProjectId(Long projectId);
}
