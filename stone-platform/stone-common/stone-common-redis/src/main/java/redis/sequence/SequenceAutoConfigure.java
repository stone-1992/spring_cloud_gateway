package redis.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import redis.sequence.properties.SequenceProperties;

/**
 * @classname SequenceAutoConfigure
 * @description redis id生成器自动配置
 * @date 2020/4/24 14:20
 */
@EnableConfigurationProperties({SequenceProperties.class})
@Configuration
public class SequenceAutoConfigure {

    @Autowired
    private SequenceProperties sequenceProperties;
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    @ConditionalOnMissingBean(Sequence.class)
    @ConditionalOnProperty(prefix = "zhcx.business.redis.sequence",name = "enabled",havingValue = "true",matchIfMissing = true)
    public Sequence sequence(){
        return RedisSequence.builder().redisTemplate(redisTemplate)
                .key(sequenceProperties.getKey())
                .step(sequenceProperties.getStep()).build();
    }
}
