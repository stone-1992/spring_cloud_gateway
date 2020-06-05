package redis.sequence;

/**
 * @classname Sequence
 * @description 序列号生成器接口
 * @date 2020/4/24 14:23
 */
public interface Sequence {

    /**
     * 生成下一个序列号
     *
     * @return 序列号
     */
    long nextValue() ;

    /**
     * 生成下一个序列号
     * @param bizKey 指定业务模块key
     * @return
     */
    long nextValue(String bizKey) ;

    /**
     * 下一个生成序号（带格式）
     *
     * @return 唯一id
     */
    String nextNo();

    /**
     * 下一个生成序号（带格式）
     * @param bizKey 指定业务模块key
     * @return 唯一id
     */
    String nextNo(String bizKey);
}
