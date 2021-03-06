package com.stone.redis.sequence.builder;


import com.stone.redis.sequence.Sequence;

/**
 * @classname SeqBuilder
 * @description 序列号生成器构建者
 * @date 2020/4/24 14:27
 * @author stone
 */
public interface SeqBuilder {
    /**
     * 构建一个序列号生成器
     *
     * @return 序列号生成器
     */
    Sequence build();
}
