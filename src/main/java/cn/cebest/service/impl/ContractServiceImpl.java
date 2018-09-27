package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.Contract;
import cn.cebest.service.ContractService;

@Service
public class ContractServiceImpl extends ServiceImpl<BaseMapper<Contract>, Contract> implements ContractService{

}
