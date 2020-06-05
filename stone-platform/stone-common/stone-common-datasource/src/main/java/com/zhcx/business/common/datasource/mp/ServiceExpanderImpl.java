package com.zhcx.business.common.datasource.mp;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @date 2020年04月16日 14:53
 */
public class ServiceExpanderImpl<M extends BaseMapperExpander<T>, T> extends ServiceImpl<M, T>
		implements IServiceExpander<T> {
	/**
	 * 重写该接口：为了解决调用该接口时，不会自动更新update_time,modifier 的问题
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean update(Wrapper<T> updateWrapper) {
		ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) ptype.getActualTypeArguments()[1];
		try {
			T o = (T) clazz.newInstance();
			return super.update(o, updateWrapper);
		} catch (InstantiationException e) {
			return super.update(null, updateWrapper);
		} catch (IllegalAccessException e) {
			return super.update(null, updateWrapper);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertBatchSomeColumn(List<T> entityList) {
		return this.insertBatchSomeColumn(entityList, DEFAULT_BATCH_SIZE);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertBatchSomeColumn(List<T> entityList, int batchSize) {
		if (batchSize < 1 || batchSize > DEFAULT_BATCH_SIZE) {
			batchSize = DEFAULT_BATCH_SIZE;
		}
		int recordNum = 0;
		ArrayList<T> subList = new ArrayList<>(batchSize);
		for (T t : entityList) {
			if (subList.size() >= batchSize) {
				// 分批插入
				recordNum += this.baseMapper.insertBatchSomeColumn(subList);
				subList = new ArrayList<>(batchSize);
			}
			subList.add(t);
		}
		// 插入剩余的记录
		recordNum += this.baseMapper.insertBatchSomeColumn(subList);
		return recordNum;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int alwaysUpdateSomeColumnById(T entity) {
		return this.baseMapper.alwaysUpdateSomeColumnById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int logicDeleteByIdWithFill(T entity) {
		return this.baseMapper.logicDeleteByIdWithFill(entity);
	}

	/**
	 * 添加有效时间条件
	 * 
	 * @param queryWrapper
	 */
	protected void addEffTimeCondition(QueryWrapper<?> queryWrapper) {
		queryWrapper.and(wrapper -> wrapper.isNull("eff_start_time").or().le("eff_start_time", new Date()))
				.and(wrapper -> wrapper.isNull("eff_end_time").or().gt("eff_end_time", new Date()));
	}

	/**
	 * 添加有效时间条件
	 * 
	 * @param updateWrapper
	 */
	protected void addEffTimeCondition(UpdateWrapper<?> updateWrapper) {
		updateWrapper.and(wrapper -> wrapper.isNull("eff_start_time").or().le("eff_start_time", new Date()))
				.and(wrapper -> wrapper.isNull("eff_end_time").or().gt("eff_end_time", new Date()));
	}

	/**
	 * 添加有效时间条件
	 * 
	 * @param queryWrapper
	 * @param effStartTimeStr
	 *            有效开始时间字符串
	 * @param effEndTimeStr
	 *            有效结束时间字符串
	 */
	protected void addEffTimeCondition(QueryWrapper<?> queryWrapper, String effStartTimeStr, String effEndTimeStr) {
		queryWrapper.and(wrapper -> wrapper.isNull(effStartTimeStr).or().le(effStartTimeStr, new Date()))
				.and(wrapper -> wrapper.isNull(effEndTimeStr).or().gt(effEndTimeStr, new Date()));
	}

	/**
	 * 构建mybatisplus分页对象
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	protected Page<T> newInstances(Long pageNo, Long pageSize) {
		if (null == pageNo || pageNo <= 0) {
			pageNo = 1L;
		}
		if (null == pageSize || pageSize <= 0) {
			pageNo = 10L;
		}
		return new Page<>(pageNo, pageSize);
	}

	/**
	 * 获取当前时间，舍去毫秒数
	 * @return 当前时间 yyyy-MM-dd HH:mm:ss
	 */
	protected Date getNowDateTime(){
		return DateUtil.parse(DateUtil.now(), "yyyy-MM-dd HH:mm:ss");
	}
}
