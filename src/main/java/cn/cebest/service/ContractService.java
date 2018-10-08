package cn.cebest.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.cebest.entity.Contract;
import cn.cebest.param.ContractQuery;

public interface ContractService extends IService<Contract>{

	/**
	 * 分页查询
	 * @param contract
	 * @return
	 */
	Page<Contract> selectListPage(ContractQuery contract);
}
