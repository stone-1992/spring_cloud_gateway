package redis.sequence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xhe
 * @classname RedisSequence
 * @description Redis Id生成器
 * @date 2020/4/24 14:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RedisSequence implements Sequence {

    private RedisTemplate redisTemplate;
    private String key;
    private Integer step;

    @Override
    public long nextValue() {
        return redisTemplate.opsForValue().increment(key,step);
    }

    @Override
    public long nextValue(String bizKey) {
        return redisTemplate.opsForValue().increment(key + ":" + bizKey,step);
    }

    @Override
    public String nextNo() {
        return String.valueOf(redisTemplate.opsForValue().increment(key,step));
    }

    @Override
    public String nextNo(String bizKey) {
        return String.valueOf(redisTemplate.opsForValue().increment(key + ":"+ bizKey,step));
    }
}
