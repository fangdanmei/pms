package cn.cebest.param;

import cn.cebest.util.PageParam;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserRoleQuery extends PageParam{

	private String roleCode;
	
	private String description;
}
