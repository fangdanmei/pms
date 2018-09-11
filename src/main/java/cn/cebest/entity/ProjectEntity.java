package cn.cebest.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("t_project")
public class ProjectEntity extends Model<ProjectEntity> {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 所属地
	 */
	private String place;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 项目描述
	 */
	private String description;
	/**
	 * 项目类型
	 */
	private String type;
	/**
	 * 开发语言
	 */
	private String devLang;
	/**
	 * 生产方式
	 */
	private String productionMode;
	/**
	 * 合同工期
	 */
	private String timeLimit;
	/**
	 * 基准上线时间
	 */
	private String standardOnlineTime;
	/**
	 * 部署方式
	 */
	private String deployMode;
	/**
	 * 部署方式说明
	 */
	private String deployModeDescription;
	/**
	 * 域名
	 */
	private String domainName;
	/**
	 * 是否备案
	 */
	private boolean isRecorded;
	
	////////////////项目主体实施计划//////////////////////
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 项目开始时间
	 */
	private String startTime;
	/**
	 * 实际工期
	 */
	private String actualTimeLimit;
	/**
	 * 需求阶段开始时间
	 */
	private String reqStartTime;
	/**
	 * 需求阶段结束时间
	 */
	private String reqEndTime;
	/**
	 * UI设计阶段开始时间
	 */
	private String uiStartTime;
	/**
	 * UI设计阶段结束时间
	 */
	private String uiEndTime;
	/**
	 * 前端制作阶段开始时间
	 */
	private String frontStartTime;
	/**
	 * 前端制作阶段结束时间
	 */
	private String frontEndTime;
	/**
	 * 技术开发阶段开始时间
	 */
	private String devStartTime;
	/**
	 * 技术开发阶段结束时间
	 */
	private String devEndTime;
	/**
	 * UAT测试阶段开始时间
	 */
	private String uatStartTime;
	/**
	 * UAT测试阶段结束时间
	 */
	private String uatEndTime;
	/**
	 * 部署阶段开始时间
	 */
	private String deployStartTime;
	/**
	 * 部署阶段结束时间
	 */
	private String deployEndTime;
	

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
