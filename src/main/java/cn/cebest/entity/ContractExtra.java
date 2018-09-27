package cn.cebest.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 补充协议实体类
 * @author maming
 * @date 2018年9月27日
 */

@Setter
@Getter
@TableName("CONTRACT_EXTRA")
public class ContractExtra {

	private Integer id;

	private Integer contractId;

	private Double quotedPrice;

	private Double discountPrice;

	private Double managePrice;

	private Double designPrice;

	private Double technicalPrice;

	private Double otherPrice;

	private String fileName;

	private String remark;

	private String createTime;

	private String updateTime;
}
