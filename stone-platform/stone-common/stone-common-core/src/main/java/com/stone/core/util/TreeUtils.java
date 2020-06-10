package com.stone.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils<T> {
	/**
	 * 把列表转换为树结构
	 *
	 * @param originalList 原始list数据
	 * @param keyName 作为唯一标示的字段名称
	 * @return 组装后的集合
	 * @throws Exception 
	 */
	public static <T> List<T> getTree(List<T> originalList, String keyName, String parentFieldName, String childrenFieldName) throws Exception{
	    if(StrUtil.isBlank(parentFieldName)) {
	    	parentFieldName = "dataParentId";
	    }
	    if(StrUtil.isBlank(childrenFieldName)) {
	    	childrenFieldName = "children";
	    }
	    // 获取根节点，即找出父节点为0的对象
	    List<T> topList = new ArrayList<>();
	    if(CollUtil.isNotEmpty(originalList)) {
	    	for (int i = 0; i < originalList.size(); i++) {
		        T t = originalList.get(i);
		        Object parentId = ReflectUtil.getFieldValue(t, parentFieldName);
		        if ("0".equals(String.valueOf(parentId))) {
		            topList.add(t);
		        }
		    }
		    // 将根节点从原始list移除，减少下次处理数据
		    originalList.removeAll(topList);
		    // 递归封装树
		    fillTree(topList, originalList, keyName, parentFieldName, childrenFieldName);

	    }
	    return topList;
	}

	/**
	 *
	 * @param originalList			数据源
	 * @param keyName				唯一标识字段名称，例如表数据：id，默认 id
	 * @param parentFieldName		标识父级节点字段：例如：parentId，默认 parentId
	 * @param childrenFieldName		子级List节点名称：例如：children，默认 children
	 * @param topValue				判断是否为最父级顶级节点的值（parentFieldName）：例如：菜单中用0表示最顶层节点，默认 0
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> getTree(List<T> originalList, String keyName, String parentFieldName, String childrenFieldName, String topValue) throws Exception{
		if(StrUtil.isBlank(keyName)){
			keyName = "id";
		}
		if(StrUtil.isBlank(parentFieldName)) {
			parentFieldName = "parentId";
		}
		if(StrUtil.isBlank(childrenFieldName)) {
			childrenFieldName = "children";
		}
		if(StrUtil.isBlank(topValue)){
			topValue = "0";
		}
		// 获取根节点，即找出父节点为0的对象
		List<T> topList = new ArrayList<>();
		if(CollUtil.isNotEmpty(originalList)) {
			for (int i = 0; i < originalList.size(); i++) {
				T t = originalList.get(i);
				Object parentId = ReflectUtil.getFieldValue(t, parentFieldName);
				if (topValue.equals(String.valueOf(parentId))) {
					topList.add(t);
				}
			}
			// 将根节点从原始list移除，减少下次处理数据
			originalList.removeAll(topList);
			// 递归封装树
			fillTree(topList, originalList, keyName, parentFieldName, childrenFieldName);

		}
		return topList;
	}
	
	/**
	 * 封装树
	 * @param parentList 要封装为树的父对象集合
	 * @param originalList 原始list数据
	 * @param keyName 作为唯一标示的字段名称
	 * @param parentFieldName 模型中作为parent字段名称
	 * @param childrenFieldName 模型中作为children的字段名称
	 */
	private static <T> void fillTree(List<T> parentList, List<T> originalList, String keyName, String parentFieldName, String childrenFieldName) throws Exception {
	    for (int i = 0; i < parentList.size(); i++) {
	        List<T> children = fillChildren(parentList.get(i), originalList, keyName, parentFieldName, childrenFieldName);
	        if (children.isEmpty()) {
	            continue;
	        }
	        originalList.removeAll(children);
	        fillTree(children, originalList, keyName, parentFieldName, childrenFieldName);
	    }
	}

	/**
	 * 封装子对象
	 *
	 * @param parent 父对象
	 * @param originalList 待处理对象集合
	 * @param keyName 作为唯一标示的字段名称
	 * @param parentFieldName 模型中作为parent字段名称
	 * @param childrenFieldName 模型中作为children的字段名称
	 */
	private static <T> List<T> fillChildren(T parent, List<T> originalList, String keyName, String parentFieldName, String childrenFieldName) throws Exception {
	    List<T> childList = new ArrayList<>();
	    Object parentId =ReflectUtil.getFieldValue(parent, keyName);
	    for (int i = 0; i < originalList.size(); i++) {
	        T t = originalList.get(i);
	        Object childParentId =ReflectUtil.getFieldValue(t, parentFieldName);
	        if (String.valueOf(parentId).equals(String.valueOf(childParentId))) {
	            childList.add(t);
	        }
	    }
	    if (!childList.isEmpty()) {
	    	ReflectUtil.setFieldValue(parent, childrenFieldName, childList);
	    }
	    return childList;
	}
}
