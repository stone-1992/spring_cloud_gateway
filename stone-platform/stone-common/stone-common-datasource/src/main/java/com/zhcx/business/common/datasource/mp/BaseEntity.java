package com.zhcx.business.common.datasource.mp;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

/**
 * 数据实体父类
 * 
 * @title
 * @date 2020年5月12日
 * @version 1.0
 */
@Data
public class BaseEntity {

	/**
	 * 创建人
	 */
	@TableField(value = "creator", fill = FieldFill.INSERT)
	protected Long creator;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	protected Date createTime;

	/**
	 * 修改人
	 */
	@TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
	protected Long modifier;

	/**
	 * 修改时间
	 */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	protected Date updateTime;
}
