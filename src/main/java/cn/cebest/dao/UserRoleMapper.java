package cn.cebest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.cebest.entity.UserRole;
import cn.cebest.param.UserRoleQuery;
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole>{

	List<UserRole> selectListPage(Page<UserRole> page, UserRoleQuery param);

	boolean delPermissions(Integer roleId);

}
