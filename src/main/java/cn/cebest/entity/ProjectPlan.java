package cn.cebest.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 *  项目计划实体类
  * @author maming  
  * @date 2018年9月28日
 */
@Setter
@Getter
@TableName("PROJECT_PLAN")
public class ProjectPlan {

	private Integer id;
	
	private Integer projectId;
	
	private String stage;
	
	private Date startTime;
	
	private Date endTime;
	
	private Date createTime;
	
	private Date updateTime;
}
