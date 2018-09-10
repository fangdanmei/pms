package cn.cebest.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("t_project_progress")
public class ProjectProgressEntity extends Model<ProjectProgressEntity> {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 项目阶段
	 */
	private String phase;
	/**
	 * 项目开始时间
	 */
	private Date startTime;
	/**
	 * 里程碑进度
	 */
	private String milestone;
	/**
	 * 项目状态
	 */
	private String projectStatus;
	/**
	 * 项目延期天数
	 */
	private int delay;
	/**
	 * 项目并行情况
	 */
	private String concurrent;
	/**
	 * 项目并行情况描述
	 */
	private String concurrentDesc;
	/**
	 * 当前任务描述
	 */
	private String currentTaskDesc;
	/**
	 * UI设计阶段开始时间
	 */
	private Date uiStartTime;
	/**
	 * UI设计阶段结束时间
	 */
	private Date uiEndTime;
	/**
	 * UI工期
	 */
	private int uiTimeLimit;
	/**
	 * 前端制作阶段开始时间
	 */
	private Date frontStartTime;
	/**
	 * 前端制作阶段结束时间
	 */
	private Date frontEndTime;
	/**
	 * 前端制作工期
	 */
	private int frontTimeLimit;
	/**
	 * 技术开发阶段开始时间
	 */
	private Date devStartTime;
	/**
	 * 技术开发阶段结束时间
	 */
	private Date devEndTime;
	/**
	 * 技术开发工期
	 */
	private int devTimeLimit;
	/**
	 * 测试联调阶段开始时间
	 */
	private Date debugStartTime;
	/**
	 * 测试联调阶段结束时间
	 */
	private Date debugEndTime;
	/**
	 * 测试联调工期
	 */
	private int debugTimeLimit;
	/**
	 * UAT测试阶段开始时间
	 */
	private Date uatStartTime;
	/**
	 * UAT测试阶段结束时间
	 */
	private Date uatEndTime;
	/**
	 *  UAT测试工期
	 */
	private int uatTimeLimit;
	/**
	 * 项目整体验收时间
	 */
	private Date acceptanceTime;
	/**
	 * 项目需求范围确认单
	 */
	private String reqConfirmFile;
	/**
	 * 首页设计签收单
	 */
	private String indexDesignFile;
	/**
	 * 子页面设计签收单
	 */
	private String subpageDesignFile;
	/**
	 * 静态效果签收单
	 */
	private String staticEffectFile;
	/**
	 * 项目验收单
	 */
	private String projectAcceptanceFile;
	/**
	 * 项目交接单
	 */
	private String projectHandoverFile;
	/**
	 * 证书开始时间
	 */
	private Date certStartTime;
	/**
	 * 证书结束时间
	 */
	private Date certEndTime;
	/**
	 * 证书工作日
	 */
	private int certWorkDays;
	/**
	 * 基准上线时间
	 */
	private Date standardOnlineTime;
	/**
	 * 项目经理
	 */
	private String manager;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 下一里程碑节点
	 */
	private Date nextMilestone;
	/**
	 * 项目整体进度
	 */
	private String progress;
	/**
	 * 已付款期数
	 */
	private String payProgress;
	/**
	 * 已回款金额
	 */
	private Long returnedMoney;
	/**
	 * 项目风险
	 */
	private String risk;
	/**
	 * 是否备案
	 */
	private boolean recorded;
	/**
	 * 是否归档
	 */
	private boolean archived;
	/**
	 * 所属地
	 */
	private String place;
	

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
