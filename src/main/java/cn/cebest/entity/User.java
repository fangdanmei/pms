package cn.cebest.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@TableName("USER_INFO")
public class User extends Model<User>{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String email;
	
	private String password;
	
	private String userName;
	
	private String role;
	
	private Date createTime;
	
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
