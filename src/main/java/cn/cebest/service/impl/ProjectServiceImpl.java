package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.Project;
import cn.cebest.service.ProjectService;

@Service
public class ProjectServiceImpl extends ServiceImpl<BaseMapper<Project>,  Project> implements ProjectService{

}
