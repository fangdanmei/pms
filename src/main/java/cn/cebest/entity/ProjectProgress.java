package cn.cebest.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 *  项目进度实体类
  * @author maming  
  * @date 2018年9月28日
 */
@Setter
@Getter
@TableName("PROJECT_PROGRESS")
public class ProjectProgress extends Model<ProjectProgress>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer projectId;
	
	private String stage;
	
	private Date startTime;
	
	private Date endTime;
	
	private Integer actualDay;
	
	private String fileName;
	
	private String originName;
	
	private Date createTime;
	
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
