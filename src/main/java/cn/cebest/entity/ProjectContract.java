package cn.cebest.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 *  项目-合同对应关系实体类
  * @author maming  
  * @date 2018年9月28日
 */

@Setter
@Getter
@TableName("PROJECT_CONTRACT")
public class ProjectContract extends Model<ProjectContract>{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer projectId;
	
	private Integer contractId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
