package cn.cebest.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.cebest.entity.UserRole;
import cn.cebest.param.UserRoleQuery;

public interface UserRoleSerive extends IService<UserRole>{

	Page<UserRole> selectListPage(UserRoleQuery param);


	void setPermissions(Integer roleId, Integer[] ids);

}
