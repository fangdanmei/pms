package cn.cebest.param;

import cn.cebest.util.PageParam;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ContractQuery extends PageParam{
	
	private Integer projectId;

	private String name;
	
	private String concatName;
	
	private String concatPhone;
}
