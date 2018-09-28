package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.ProjectContract;
import cn.cebest.service.ProjectContractService;

@Service
public class ProjectContractServiceImpl extends ServiceImpl<BaseMapper<ProjectContract>, ProjectContract> implements ProjectContractService{

}
