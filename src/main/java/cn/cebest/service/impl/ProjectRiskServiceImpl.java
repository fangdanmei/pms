package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.ProjectRisk;
import cn.cebest.service.ProjectRiskService;

@Service
public class ProjectRiskServiceImpl extends ServiceImpl<BaseMapper<ProjectRisk>, ProjectRisk>  implements ProjectRiskService{

}
