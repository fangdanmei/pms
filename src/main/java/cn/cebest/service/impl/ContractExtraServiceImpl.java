package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.ContractExtra;
import cn.cebest.service.ContractExtraService;

@Service
public class ContractExtraServiceImpl extends ServiceImpl<BaseMapper<ContractExtra>, ContractExtra> implements ContractExtraService{

}
