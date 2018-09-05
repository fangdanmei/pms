package cn.cebest.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.cebest.dao.ProjectMapper;
import cn.cebest.entity.ProjectEntity;
import cn.cebest.service.ProjectService;

@Service("userService")
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity> implements ProjectService {


}
