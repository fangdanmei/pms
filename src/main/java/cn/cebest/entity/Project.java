package cn.cebest.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 *  项目实体类
  * @author maming  
  * @date 2018年9月28日
 */

@Setter
@Getter
@TableName("PROJECT")
public class Project extends Model<Project>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private String description;
	
	private String contactName;
	
	private String contactDep;
	
	private String contactPhone;
	
	private String contactEmail;
	
	private String contactWechat;
	
	private String contactRemark;
	
	@TableField(exist = false)
	private String contractIds;
	
	private String area;
	
	private String projectType;
	
	private String productType;
	
	private Integer timeLimit;
	
	private Date onlineTime;
	
	private String domain;
	
	private String deployType;
	
	private String explain;
	
	private String progress;
	
	private Date startTime;
	
	private String milepostProgress;
	
	private String projectState;
	
	private Integer delay;
	
	private String parallel;
	
	private String progressExplain;
	
	private String taskDesc;
	
	private Date acceptTime;
	
	private Integer actualDay;
	
	private Date certStime;
	
	private Date certEtime;
	
	private Integer certDays;
	
	private Date createTime;
	
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
