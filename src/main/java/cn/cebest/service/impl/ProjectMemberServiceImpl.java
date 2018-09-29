package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.ProjectMember;
import cn.cebest.service.ProjectMemberService;

@Service
public class ProjectMemberServiceImpl extends ServiceImpl<BaseMapper<ProjectMember>, ProjectMember> implements ProjectMemberService{

}
