package cn.cebest.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import cn.cebest.entity.Project;
import cn.cebest.param.ProjectQuery;

@Mapper
public interface ProjectMapper extends BaseMapper<Project>{

	/**
	 * 分页查询
	 * @param page
	 * @param project
	 * @return
	 */
	List<Project> selectListPage(Pagination page,  ProjectQuery project);
	/**
	 * 查询项目状态柱状图数据
	 * */
	List<Project> stateChart();
}
