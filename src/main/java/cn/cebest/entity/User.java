package cn.cebest.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("user")
public class User extends Model<User> {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private Integer sex;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
