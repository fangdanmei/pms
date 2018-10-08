package cn.cebest.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import cn.cebest.entity.Contract;
import cn.cebest.param.ContractQuery;

@Mapper
public interface ContractMapper extends BaseMapper<Contract>{

	/**
	 * 分页查询
	 * @param page
	 * @param contract
	 * @return
	 */
	List<Contract> selectListPage(Pagination page,  ContractQuery contract);
}
