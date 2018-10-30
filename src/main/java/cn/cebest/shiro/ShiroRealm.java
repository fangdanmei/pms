package cn.cebest.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cebest.entity.Permission;
import cn.cebest.entity.User;
import cn.cebest.entity.UserRole;
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
		for (UserRole role : userInfo.getRoleList()) {
			authorizationInfo.addRole(role.getRoleCode());
			if(!role.getPermissions().isEmpty()){
				// 获取用户所有权限(可以在数据库配置好或灵活配置)
		        for(Permission p:role.getPermissions()){
		        	// 添加权限
		            if(StringUtils.isNotEmpty(p.getPermission())){
		            	authorizationInfo.addStringPermission(p.getPermission());
		            }
		        }
			}
		}
		//authorizationInfo.addRole(userInfo.getRole());
		return authorizationInfo;
	}

	/** 
     * 重新赋值权限(在比如:给一个角色临时添加一个权限,需要调用此方法刷新权限,否则还是没有刚赋值的权限) 
     * @param myRealm 自定义的realm 
     * @param username 用户名 
     */  
    public static void reloadAuthorizing(ShiroRealm myRealm,Object user){  
        Subject subject = SecurityUtils.getSubject();   
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();   
        //第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户   
        SimplePrincipalCollection principals = new SimplePrincipalCollection(user, realmName);
        // 重新加载
        subject.runAs(principals);   
    } 

	
	
}
