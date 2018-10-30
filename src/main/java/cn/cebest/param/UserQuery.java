package cn.cebest.param;

import cn.cebest.util.PageParam;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserQuery extends PageParam{
	
	private String username;
	
	private String email;
}
