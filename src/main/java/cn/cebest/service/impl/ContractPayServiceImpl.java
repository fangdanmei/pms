package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.ContractPay;
import cn.cebest.service.ContractPayService;

@Service
public class ContractPayServiceImpl extends ServiceImpl<BaseMapper<ContractPay>, ContractPay> implements ContractPayService{

}
