package cn.cebest.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.cebest.entity.User;
import cn.cebest.param.UserQuery;

public interface UserService extends IService<User>{

	public User findByEmail(String email);

	public Page<User> selectListPage(UserQuery param);

	public void setUserRoles(Integer userId, Integer[] ids);
}
