package cn.cebest.param;

import cn.cebest.util.PageParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectQuery extends PageParam{

	private String name;
	
	private String projectState;
	
	private String area;
}
