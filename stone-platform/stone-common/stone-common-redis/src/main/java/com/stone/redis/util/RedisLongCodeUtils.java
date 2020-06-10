package com.stone.redis.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class RedisLongCodeUtils {

    /**
     * 根据给定的longcode 填充 incrCode
     *
     * @param longCode 0000/00001
     * @param incrCode 2
     * @param fill     4
     * @return 0000/00001/0004
     */
    public static String getLongCodeByLongCode(String longCode, long incrCode, int fill) {
        if (StrUtil.isBlank(longCode)) {
            return StrUtil.fill(String.valueOf(incrCode), '0', fill, true) + "/";
        } else {
            return longCode + StrUtil.fill(String.valueOf(incrCode), '0', fill, true) + "/";
        }
    }

    /**
     * 指定redis 的 key 自增长1
     *
     * @param key
     * @param redisTemplate
     * @return
     */
    public static long getIncr(String key, RedisTemplate redisTemplate) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long increment = entityIdCounter.getAndIncrement();
        return increment;
    }

    /**
     * 设置redis 对应的 key 的值
     *
     * @param key           redis 中对应的key
     * @param value         指定的value
     * @param redisTemplate
     */
    public static void setIncr(String key, long value, RedisTemplate redisTemplate) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        entityIdCounter.set(value);
    }

    /**
     * 自动填充给定
     *
     * @param key               redis 自增长key
     * @param redisTemplate     redis 连接
     * @param fill              填充位数
     * @param isPre             是否在前填充
     * @param filledChar        填充得字符
     * @return
     */
    public static String getFilledValue(String key, RedisTemplate redisTemplate, int fill, boolean isPre, char filledChar) {
        long incr = getIncr(key, redisTemplate);
        return StrUtil.fill(String.valueOf(incr), filledChar, fill, isPre);
    }
}
