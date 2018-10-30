package cn.cebest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("PERMISSION")
public class Permission extends Model<Permission>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String uniqueCode;//唯一标识
	
	private String name;//资源名称
	
	private String type;//资源类型
	
	private String url;//资源路径
	
	private String permission;//权限
	
	private Integer parentId;
	
	private String parentCode;
	
	private Date createTime;
	
	private Date updateTime;
	
	@TableField(exist = false)
	private List<Permission> children = new ArrayList<>();
	
	@TableField(exist = false)
	private String title;//菜单名称
	
	@TableField(exist = false)
	private String href;//菜单跳转地址

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
