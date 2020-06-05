package redis.constant;

/**
 * @classname BaseDataLineRedisConstants
 * @description 基础数据 线路管理Redis key常量
 * @date 2020/5/25 15:03
 */
public interface BaseDataRedisConstants {
    /**
     * 基础数据-线路管理(bp_line) redis主键key
     */
    String LINE_SEQ_KEY = "base_data:bp_line";
    /**
     * 基础数据-站点(bp_site) redis主键key
     */
    String SITE_SEQ_KEY = "base_data:bp_site";
}
