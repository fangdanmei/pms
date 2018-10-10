package cn.cebest.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.cebest.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

	public User findByEmail(@Param("email") String email);
}
