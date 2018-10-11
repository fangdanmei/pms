package cn.cebest.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("PROJECT_MEMBER")
public class ProjectMember extends Model<ProjectMember>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer projectId;
	
	private String role;
	
	private Integer userId;
	
	private String userName;
	
	private Date createTime;
	
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
