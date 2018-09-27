package cn.cebest.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("CONTRACT_PAY")
public class ContractPay {

	private Integer id;
	
	private Integer contractId;
	
	private Double amount;
	
	private String explain;
	
	private Integer isPay;
	
	private String createTime;
	
	private String updateTime;
}
