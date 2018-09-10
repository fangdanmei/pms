package cn.cebest.util;

import java.util.List;
import com.github.pagehelper.PageInfo;
import cn.cebest.framework.util.ResultCode;
import lombok.Getter;
import lombok.Setter;



/**
 *  分页返回对象
  * @author maming  
  * @date 2018年9月7日
 */
@Setter
@Getter
public class PageResult<T> {
	
	public static final ResultCode PAGE_SUCCESS = new ResultCode(0, "成功");
	
	// 返回状态码
	private Integer code;
	// 数组总条数
	private long count;
	// 提示信息
	private String msg;
	// 数据列表
	private List<T> data;
	
	
	public PageResult(PageInfo<T> pageInfo) {
		this.code = PAGE_SUCCESS.getCode();
		this.msg = PAGE_SUCCESS.getMessage();
		this.count = pageInfo.getTotal();
		this.data = pageInfo.getList();
	}
}
