package cn.cebest.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageParam {

	// 页号
	private Integer page = 1;
	
	// 每页显示条数
	private Integer limit = 10;
}
