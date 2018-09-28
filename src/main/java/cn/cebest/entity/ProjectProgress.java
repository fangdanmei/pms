package cn.cebest.entity;

import java.io.Serializable;
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
	
	private String startTime;
	
	private String endTime;
	
	private Integer actualDay;
	
	private String fileName;
	
	private String originName;
	
	private String createTime;
	
	private String updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
