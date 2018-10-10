package cn.cebest.service;

import com.baomidou.mybatisplus.service.IService;
import cn.cebest.entity.User;

public interface UserService extends IService<User>{

	public User findByEmail(String email);
}
