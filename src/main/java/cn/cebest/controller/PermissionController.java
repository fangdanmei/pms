package cn.cebest.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import cn.cebest.entity.Permission;
import cn.cebest.framework.shiro.ShiroService;
import cn.cebest.framework.util.Result;
import cn.cebest.framework.util.ResultCode;
import cn.cebest.param.PermissionQuery;
import cn.cebest.service.PermissionService;
import cn.cebest.util.PageResult;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	

	@Autowired
	private ShiroService shiroService;
	
	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	
	/**
	 * 权限列表(根据资源名模糊搜索)
	 * @param param
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public PageResult<Permission> list(PermissionQuery param){
		Page<Permission> pageData = permissionService.selectPage(
				new Page<Permission>(param.getPage(), param.getLimit()),
				new EntityWrapper<Permission>().like(StringUtils.isNotEmpty(param.getName()),"NAME", param.getName()));
		return new PageResult<Permission>(pageData);
	}
	
	/**
	 * 根据id修改权限
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{permissonId:\\d+}")
	public String permissonDetail(@PathVariable("permissonId") Integer id,ModelMap model){
		Permission permission = permissionService.selectById(id);
		model.put("permission", permission);
		return "permisson_detail";
	}
	
	/**
	 * 根据id删除资源
	 * @param id
	 * @return
	 */
	@DeleteMapping("/del/{id:\\d+}")
	@ResponseBody
	public Result deletePermissionById(@PathVariable("id") Integer id){
		permissionService.deleteById(id);
		return new Result();
	}
	
	
	/*@PostMapping("/{id:\\d+}")
	@ResponseBody
	public Result synch(@PathVariable("id") Integer id,Permission permission){
		permissionService.update(permission, new EntityWrapper<Permission>().eq("ID", id));
		return new Result();
	}*/
	/**
	 * 修改(传ID)或添加(不传id)
	 * @param permission
	 * @return
	 */
	@PostMapping("/synch")
	@ResponseBody
	public Result sysnc(Permission permission){
		permissionService.insertOrUpdate(permission);
		return new Result(ResultCode.SUCCESS, permission);
	}
	
	/**
	 * 新增权限
	 * @param permission
	 * @return
	 */
	@PostMapping("/insertPermisson")
	@ResponseBody
	public Result insertPermisson(Permission permission){
		int count = permissionService.selectCount(new EntityWrapper<Permission>().eq("URL", permission.getUrl()));
		if(count > 0){
			return new Result(ResultCode.RESOURCE_URL_EXISTS_ERROR);
		}
		int count2 = permissionService.selectCount(new EntityWrapper<Permission>().eq("PERMISSION", permission.getPermission()));
		if(count2 > 0){
			return new Result(ResultCode.PERMISSION_EXISTS_ERROR);
		}
		if(StringUtils.isNotEmpty(permission.getParentCode())){
			Permission per = (Permission) permissionService.selectOne(new EntityWrapper<Permission>().eq("UNIQUE_CODE", permission.getParentCode()));
			
			if(per == null){
				return new Result(ResultCode.PARENT_CODE_NOT_EXISTS);
			}
			permission.setParentId(per.getId());
		}
		int time  = (int) System.currentTimeMillis();
		// 生成唯一标识
		permission.setUniqueCode(Integer.toHexString(time).toUpperCase());
		//permissionService.insertEntity(permission);
		permissionService.insert(permission);
		//重新设置权限
		shiroService.reloadFilterChains(shiroFilterFactoryBean);
		Result result = new Result();
		result.setData(permission);
		return result;
	}
	
	/**
	 * 菜单列表(此功能不用)
	 * @return
	 */
	@ResponseBody
	@GetMapping("/menuList")
	public String menuList(){
		List<Permission> list = permissionService.selectMenuList();
		List<Permission> parentList = new ArrayList<>();
		List<Permission> childrenList = new ArrayList<>();
		if(list.size()>0){
			for (Permission permission : list) {
				if(permission.getParentId()==0){
					parentList.add(permission);
				}
			}
			for (Permission permission : list) {
				if(permission.getParentId() != 0){
					childrenList.add(permission);
					this.appendChild(permission, parentList);
				}
			}
			
		}
		return  new Gson().toJson(parentList);
	}
	
	/**
	 * 追加菜单子级列表
	 * @param permission
	 * @param parentList
	 */
	public void appendChild(Permission permission,List<Permission> parentList){
		for (int i = 0; i < parentList.size(); i++) {
			Permission parent = parentList.get(i);
			if(parent.getId().equals(permission.getParentId()) ){
				parent.getChildren().add(permission);
				this.appendChild(parent, parentList);
			}
		}
	}
	
	
}
