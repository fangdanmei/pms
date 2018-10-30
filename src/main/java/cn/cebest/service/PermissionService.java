package cn.cebest.service;


import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import cn.cebest.entity.Permission;

public interface PermissionService extends IService<Permission>{

	List<Permission> selectMenuList();

}
