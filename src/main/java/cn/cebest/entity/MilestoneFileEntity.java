package cn.cebest.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 里程碑文件实体
 *
 */
@Setter
@Getter
@TableName("t_milestone_file")
public class MilestoneFileEntity extends Model<MilestoneFileEntity> {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 项目ID
	 */
	private Long projectId;
	/**
	 * 原文件名
	 */
	private String originalFileName;
	/**
	 * 文件名
	 */
	private String fileName;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
