package cn.cebest.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.cebest.entity.User;
import cn.cebest.entity.UserRole;
import cn.cebest.framework.util.Result;
import cn.cebest.param.UserQuery;
import cn.cebest.service.UserService;
import cn.cebest.util.PageResult;
import cn.cebest.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import scala.collection.mutable.StringBuilder;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final String CURRENT_USER = "CURRENT_USER";
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String login(User user, ModelMap model) throws Exception {

		log.info("用户登录...");
		String msg = "success";

		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			// 把用户名和密码封装为 UsernamePasswordToken 对象
			UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(), user.getPassword());
			try {
				// 登录,自定义的realm
				subject.login(token);
			} catch (UnknownAccountException e) {
				log.info("用户不存在");
				msg = "用户不存在";
			} catch (IncorrectCredentialsException ice) {
				log.info("用户密码错误");
				msg = "用户密码错误";
			} catch (AuthenticationException ae) {
				log.info("用户名或密码错误");
				msg = "用户名或密码错误";
			}
		}

		// 将提示信息放到request作用域
		model.put("msg", msg);

		// 验证是否登录成功
		if (subject.isAuthenticated()) {
			Session session = subject.getSession();
			User userInfo = ShiroUtils.getUserEntity();
			session.setAttribute(CURRENT_USER, userInfo);
			return "redirect:/index";
		} else {
			return "/login";
		}
	}
	
	
	
	@GetMapping("/logout")
	public String logout() {
		log.info("用户注销...");
		ShiroUtils.logout();
		return "redirect:/login";
	}
	
	/**
	 * 用户列表
	 * @param param
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
	public PageResult<User> list(UserQuery param){
		Page<User> pageData = userService.selectListPage(param);
		List<User> userList = pageData.getRecords();
		for (User user : userList) {
			List<UserRole> roleList = user.getRoleList();
			StringBuilder builder = new StringBuilder();
			StringBuilder idsBuilder = new StringBuilder();
			if(!roleList.isEmpty()){
				for (UserRole userRole : roleList) {
					builder.append(userRole.getDescription()).append(",");
					idsBuilder.append(userRole.getId()).append(",");
				}
				user.setRoleName(builder.substring(0, builder.length()-1).toString());
				user.setRoleIds(idsBuilder.substring(0, idsBuilder.length()-1).toString());
			}
		}
		return new PageResult<User>(pageData);
	}
	
	@PostMapping("/{userId:\\d+}/roleIds")
	@ResponseBody
	public Result setUserRoles(@PathVariable("userId") Integer userId,
			@RequestParam(value= "ids[]",required = false) Integer ids[]){
		userService.setUserRoles(userId,ids);
		return new Result();
	}

}
