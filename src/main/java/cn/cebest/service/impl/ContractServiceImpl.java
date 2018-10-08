package cn.cebest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.dao.ContractMapper;
import cn.cebest.entity.Contract;
import cn.cebest.param.ContractQuery;
import cn.cebest.service.ContractService;

@Service
public class ContractServiceImpl extends ServiceImpl<BaseMapper<Contract>, Contract> implements ContractService{

	@Autowired
	private ContractMapper contractMapper;
	
	@Override
	public Page<Contract> selectListPage(ContractQuery contract) {
		// 当前页，总条数 构造 page 对象
		Page<Contract> page = new Page<>(contract.getPage(), contract.getLimit());
        return page.setRecords(contractMapper.selectListPage(page, contract));
	}

}
