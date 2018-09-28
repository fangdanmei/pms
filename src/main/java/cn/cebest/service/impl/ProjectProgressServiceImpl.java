package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.ProjectProgress;
import cn.cebest.service.ProjectProgressService;

@Service
public class ProjectProgressServiceImpl extends ServiceImpl<BaseMapper<ProjectProgress>, ProjectProgress> implements ProjectProgressService{

}
