package cn.cebest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.cebest.dao.UserPermissionMapper;
import cn.cebest.dao.UserRoleMapper;
import cn.cebest.entity.UserRole;
import cn.cebest.param.UserRoleQuery;
import cn.cebest.service.UserRoleSerive;

@Service
public class UserRoleImpl extends ServiceImpl<BaseMapper<UserRole>, UserRole> implements UserRoleSerive{

	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserPermissionMapper userPermissionMapper;
	
	@Override
	public Page<UserRole> selectListPage(UserRoleQuery param) {
		Page<UserRole> page = new Page<>(param.getPage(), param.getLimit());
		return page.setRecords(userRoleMapper.selectListPage(page, param));
	}


	@Override
	public void setPermissions(Integer roleId, Integer[] ids) {
		//先删除所拥有权限
		userPermissionMapper.delPermissions(roleId);
		if(null != ids && ids.length>0){
		//增加权限	
		userPermissionMapper.setPermissions(roleId,ids);	
		}
	}




}
