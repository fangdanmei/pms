package cn.cebest.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 *  支付节点实体类
  * @author maming  
  * @date 2018年9月27日
 */


@Setter
@Getter
@TableName("CONTRACT_PAY")
public class ContractPay {

	private Integer id;
	
	private Integer contractId;
	
	private Double amount;
	
	private String explain;
	
	private Integer isPay;
	
	private Date createTime;
	
	private Date updateTime;
}
