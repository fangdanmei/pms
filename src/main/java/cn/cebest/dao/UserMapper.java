package cn.cebest.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.cebest.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

}
