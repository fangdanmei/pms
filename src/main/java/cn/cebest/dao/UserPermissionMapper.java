package cn.cebest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.cebest.entity.Permission;

@Mapper
public interface UserPermissionMapper extends BaseMapper<Permission> {

	boolean delPermissions(Integer roleId);

	void setPermissions(@Param("roleId")Integer roleId, @Param("ids")Integer[] ids);

	List<Permission> selectMenuList();

}
