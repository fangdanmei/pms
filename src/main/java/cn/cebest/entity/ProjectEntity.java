package cn.cebest.entity;

import java.io.Serializable;
import java.util.Date;

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
//	/**
//	 * 基准上线时间(应该在项目进度实体里)
//	 */
//	private Date standardOnlineTime;
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
	private Date updateTime;
	/**
	 * 项目开始时间
	 */
	private Date startTime;
	/**
	 * 实际工期
	 */
	private String actualTimeLimit;
	/**
	 * 需求阶段开始时间
	 */
	private Date reqStartTime;
	/**
	 * 需求阶段结束时间
	 */
	private Date reqEndTime;
	/**
	 * UI设计阶段开始时间
	 */
	private Date uiStartTime;
	/**
	 * UI设计阶段结束时间
	 */
	private Date uiEndTime;
	/**
	 * 前端制作阶段开始时间
	 */
	private Date frontStartTime;
	/**
	 * 前端制作阶段结束时间
	 */
	private Date frontEndTime;
	/**
	 * 技术开发阶段开始时间
	 */
	private Date devStartTime;
	/**
	 * 技术开发阶段结束时间
	 */
	private Date devEndTime;
	/**
	 * UAT测试阶段开始时间
	 */
	private Date uatStartTime;
	/**
	 * UAT测试阶段结束时间
	 */
	private Date uatEndTime;
	/**
	 * 部署阶段开始时间
	 */
	private Date deployStartTime;
	/**
	 * 部署阶段结束时间
	 */
	private Date deployEndTime;
	

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
