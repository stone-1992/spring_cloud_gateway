package com.stone.redis.constant;

import static com.stone.redis.constant.BaseRedisConstants.BASE;


/**
 * @classname OrgConstants
 * @description application redis key常量
 * @date 2020/4/23 10:03
 */
public interface ApplicationRedisConstants {
	
	/**
     * 菜单长编码自增长redis前缀
     */
    String MENU_LONG_CODE_GENC = BASE + "seq:menu:code:%s";
	
}
