package cn.cebest.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;


/**
 *  项目风险实体类
  * @author maming  
  * @date 2018年9月29日
 */
@Setter
@Getter
@TableName("PROJECT_RISK")
public class ProjectRisk extends Model<ProjectRisk>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer projectId;
	
	private String describe;
	
	private String level;
	
	private String probability;
	
	private String state;
	
	private Date trackTime;
	
	private String measures;
	
	private String personLiable;
	
	private Date createTime;
	
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
}
