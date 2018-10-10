package cn.cebest.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.cebest.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

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
		model.put(msg, msg);

		// 验证是否登录成功
		if (subject.isAuthenticated()) {
			return "redirect:/index";
		} else {
			return "/login";
		}
	}

}
