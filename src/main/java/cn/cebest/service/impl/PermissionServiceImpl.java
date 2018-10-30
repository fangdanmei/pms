package cn.cebest.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.cebest.dao.UserPermissionMapper;
import cn.cebest.entity.Permission;
import cn.cebest.service.PermissionService;

@Service
public class PermissionServiceImpl extends ServiceImpl<BaseMapper<Permission>, Permission> implements PermissionService{

	@Autowired
	private UserPermissionMapper userPermissionMapper;
	
	@Override
	public List<Permission> selectMenuList() {
		
		return userPermissionMapper.selectMenuList();
	}
	
}
