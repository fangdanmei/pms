package cn.cebest.controller;


import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;

import cn.cebest.entity.Permission;
import cn.cebest.entity.User;
import cn.cebest.entity.UserRole;
import cn.cebest.framework.util.Result;
import cn.cebest.framework.util.ResultCode;
import cn.cebest.param.UserRoleQuery;
import cn.cebest.service.UserRoleSerive;
import cn.cebest.service.UserService;
import cn.cebest.shiro.ShiroRealm;
import cn.cebest.util.PageResult;
import scala.collection.mutable.StringBuilder;

@Controller
@RequestMapping("/role")
public class UserRoleController {
	
	@Autowired
	private UserRoleSerive userRoleSerive;
	@Autowired
	private UserService userService;
	
	
	/**
	 * 查询角色列表(分页)
	 * @param param
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
    public PageResult<UserRole> list(UserRoleQuery param ) {
		
		Page<UserRole> pageData=userRoleSerive.selectListPage(param);
		List<UserRole> records = pageData.getRecords();
		for (UserRole userRole : records) {
			List<Permission> permissions = userRole.getPermissions();
			StringBuilder stringBuilder = new StringBuilder();
			StringBuilder idsBuildder = new StringBuilder();
			if(!permissions.isEmpty()){
				for (Permission permission : permissions) {
					stringBuilder.append(permission.getName()).append(",");
					idsBuildder.append(permission.getId()).append(",");
				}
				userRole.setPermissonIds(idsBuildder.substring(0,idsBuildder.length()-1).toString());
				userRole.setPermissonName(stringBuilder.substring(0, stringBuilder.length()-1).toString());
			}
		}
		return new PageResult<UserRole>(pageData);
	}
	
	/**
	 * 角色插入
	 * @param userRole
	 * @return
	 */
	@ResponseBody
	@PostMapping("/synch")
	public Result synch(UserRole userRole){
		userRoleSerive.insert(userRole);
		return new Result(ResultCode.SUCCESS,userRole);
	}
	
	/**
	 *删除角色(根据ID)
	 * @param id
	 * @return
	 */
	@DeleteMapping("/del/{id:\\d+}")
	@ResponseBody
	public Result delRole(@PathVariable("id") Integer id){
		userRoleSerive.deleteById(id);
		return new Result();
	}
	
	@PostMapping("/{roleId:\\d+}/permissions")
	@ResponseBody
	public Result setPermissons(@PathVariable("roleId") Integer roleId,
			@RequestParam(value= "ids[]",required = false) Integer ids[]){
		userRoleSerive.setPermissions(roleId,ids);
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm shiroRealm = (ShiroRealm)rsm.getRealms().iterator().next();
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		userService.findByEmail(user.getEmail());
		//重新赋值权限
		shiroRealm.reloadAuthorizing(shiroRealm, user);
		return new Result();
	}
	
	
}
