package cn.cebest.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.entity.User;
import cn.cebest.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<BaseMapper<User>, User> implements UserService {

	@Override
	public User findByEmail(String email) {
		
		return this.selectOne(new EntityWrapper<User>().eq("EMAIL", email));
	}

}
