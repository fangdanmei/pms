package cn.cebest.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.cebest.entity.User;
import cn.cebest.service.UserService;

@Component
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upt=(UsernamePasswordToken)token;
		
		// 获取用户名
		String userName = (String)token.getPrincipal();
		String password = String.valueOf(upt.getPassword());
		User userInfo = userService.findByEmail(userName);
		
		if (userInfo == null) {
			return null;
		}

		return new SimpleAuthenticationInfo(
				userInfo, // 用户名
				password, // 密码
				getName());

	}

	/**
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User userInfo = (User) principals.getPrimaryPrincipal();
		authorizationInfo.addRole(userInfo.getRole());
		return authorizationInfo;
	}

}
