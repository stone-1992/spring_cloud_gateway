package redis.sequence.builder;


import redis.sequence.Sequence;

/**
 * @classname SeqBuilder
 * @description 序列号生成器构建者
 * @date 2020/4/24 14:27
 */
public interface SeqBuilder {
    /**
     * 构建一个序列号生成器
     *
     * @return 序列号生成器
     */
    Sequence build();
}
