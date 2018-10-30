package cn.cebest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.cebest.entity.User;
import cn.cebest.param.UserQuery;

@Mapper
public interface UserMapper extends BaseMapper<User>{

	public User findByEmail(@Param("email") String email);

	public List<User> selectListPage(Page<User> page, UserQuery param);

	public void delRoles(Integer userId);

	public void setRoleIds(@Param("userId")Integer userId,@Param("ids") Integer[] ids);
}
