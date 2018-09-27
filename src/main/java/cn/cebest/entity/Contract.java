package cn.cebest.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 *  合同实体类
  * @author maming  
  * @date 2018年9月27日
 */

@Setter
@Getter
@TableName("CONTRACT")
public class Contract extends Model<Contract>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private String contactName;
	
	private String contactDep;
	
	private String contactPhone;
	
	private String contactEmail;
	
	private String contactWechat;
	
	private String contactRemark;
	
	private Double amount;
	
	private String signTime;
	
	private Double account;
	
	private Double receivable;
	
	private Double quotedPrice;
	
	private Double discountPrice;
	
	private Double managePrice;
	
	private Double designPrice;
	
	private Double technicalPrice;
	
	private Double otherPrice;
	
	private Double zhSubtotal;
	
	private Double enSubtotal;
	
	private String percentage;
	
	private Double taxation;
	
	private String fileName;
	
	private String createTime;
	
	private String updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
