package redis.sequence.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @classname SequenceProperties
 * @description redis id生成器属性
 * @date 2020/4/24 14:05
 */
@Data
@ConfigurationProperties(prefix = "zhcx.business.redis.sequence")
public class SequenceProperties {
    /**
     * 自增key前缀
     */
    private String key = "uuid";
    /**
     * 每次自增步长
     */
    private Integer step = 1;
}
