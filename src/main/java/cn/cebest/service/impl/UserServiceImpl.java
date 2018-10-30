package cn.cebest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.cebest.dao.UserMapper;
import cn.cebest.entity.User;
import cn.cebest.param.UserQuery;
import cn.cebest.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<BaseMapper<User>, User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByEmail(String email) {
		
		//return this.selectOne(new EntityWrapper<User>().eq("EMAIL", email));
		return userMapper.findByEmail(email);
	}

	@Override
	public Page<User> selectListPage(UserQuery param) {
		Page<User> page = new Page<>(param.getPage(),param.getLimit());
		return page.setRecords(userMapper.selectListPage(page,param));
	}

	@Override
	public void setUserRoles(Integer userId, Integer[] ids) {
		userMapper.delRoles(userId);
		if(null != ids && ids.length>0){
			userMapper.setRoleIds(userId,ids);	
		}
		
	}

}
