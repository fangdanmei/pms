package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.ProjectPlan;
import cn.cebest.service.ProjectPlanService;

@Service
public class ProjectPlanServiceImpl extends ServiceImpl<BaseMapper<ProjectPlan>, ProjectPlan> implements ProjectPlanService{

}
