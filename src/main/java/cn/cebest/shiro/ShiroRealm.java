package cn.cebest.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.cebest.entity.User;
import cn.cebest.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken upt = (UsernamePasswordToken) token;

		// 获取用户名
		String userName = (String) token.getPrincipal();
		String password = String.valueOf(upt.getPassword());
		String algorithmName = "MD5";
		String salt = null;
		String credentials = password;
		int hashIterations = 1;
		String simpleHash = new SimpleHash(algorithmName, credentials, salt, hashIterations).toString();
		log.info("登录密码为:{}", simpleHash);
		User userInfo = userService.findByEmail(userName);

		if (userInfo == null) {
			return null;
		}

		return new SimpleAuthenticationInfo(userInfo, // 用户名
				userInfo.getPassword(), // 密码
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
