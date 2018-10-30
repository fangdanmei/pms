package cn.cebest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author niuhao
 * @date   2018年10月22日
 * @description 用户角色实体类
 */
@Setter
@Getter
@TableName("USER_ROLE")
public class UserRole extends Model<UserRole> {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String roleCode;//角色编号
	
	private String description;//角色描述
	
	private Date createTime;
	
	private Date updateTime;
	
	@TableField(exist = false)
	private String url;
	
	@TableField(exist = false)
	private List<Permission> permissions;
	
	@TableField(exist = false)
	private String permissonName;
	
	@TableField(exist = false)
	private String permissonIds;
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
