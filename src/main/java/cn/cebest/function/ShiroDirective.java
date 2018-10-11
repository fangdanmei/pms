package cn.cebest.function;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 *  shiro标签指令
  * @author maming  
  * @date 2018年10月11日
 */
public class ShiroDirective {

	
	private static final String ROLE_NAMES_DELIMETER = ",";
	
	/**
	 *  是否拥有该权限
	 * @param permission
	 * @return
	 */
	protected boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}
	
	/**
	 *  是否拥有该角色
	 * @param permission
	 * @return
	 */
	protected boolean hasRole(String roleName) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.hasRole(roleName);
	}
	
	
	
	/**
	 *  是否拥有其中某角色
	 * @param permission
	 * @return
	 */
	protected boolean hasAnyRoles(String roleNames) {
		boolean hasAnyRole = false;

		Subject subject = SecurityUtils.getSubject();

		if (subject != null) {
			for (String role : roleNames.split(ROLE_NAMES_DELIMETER)) {
				if (subject.hasRole(role.trim())) {
					hasAnyRole = true;
					break;
				}
			}
		}

		return hasAnyRole;
	}

}
