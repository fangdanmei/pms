package cn.cebest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.cebest.dao.UserMapper;
import cn.cebest.entity.User;

@Controller
public class IndexController {
	
	@Autowired
	private UserMapper userMapper;

	@GetMapping("test")
	@ResponseBody
	public User test(){
		User user = userMapper.selectById(1);
		return user;
	}
}
