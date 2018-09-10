package cn.cebest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.cebest.dao.MilestoneFileMapper;
import cn.cebest.entity.MilestoneFileEntity;
import cn.cebest.service.MilestoneFileService;

@Service("milestoneFileService")
public class MilestoneFileServiceImpl extends ServiceImpl<MilestoneFileMapper, MilestoneFileEntity> implements MilestoneFileService {

	@Override
	public List<MilestoneFileEntity> selectByProjectId(Long projectId) {
		assert projectId != null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project_id", projectId);
		return this.baseMapper.selectByMap(map);
	}
}
