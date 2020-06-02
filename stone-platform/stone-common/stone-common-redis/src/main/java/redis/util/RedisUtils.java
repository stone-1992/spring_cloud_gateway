package redis.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashSet;
import java.util.Set;

/**
 * @classname RedisUtils
 * @description redis工具类
 * @date 2020/4/23 10:03
 * @author xhe
 */
@Slf4j
@UtilityClass
public class RedisUtils {

    /**
     * 每次扫描key数量
     */
    private static final Integer COUNT = 10000;

    public static Set<String> getKeySet(RedisTemplate redisTemplate, String pattern) {
        return getKeySet(redisTemplate,pattern,COUNT);
    }

    /**
     *
     * @param redisTemplate
     * @param pattern 需要匹配的key
     * @return
     */
    public static Set<String> getKeySet(RedisTemplate redisTemplate, String pattern,Integer count) {
        long start = System.currentTimeMillis();
        //这里指定每次扫描key的数量(不能使用Integer.MAX_VALUE，效果等同于keys)
        ScanOptions options = ScanOptions.scanOptions()
                .count(COUNT)
                .match(pattern).build();
        Cursor cursor = null;
        Set<String> result = new HashSet<>();
        try {
            RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
            cursor = (Cursor) redisTemplate.executeWithStickyConnection(redisConnection ->
                    new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
            while (cursor.hasNext()) {
                result.add(cursor.next().toString());
            }
        } finally {
            try {
                if(null !=cursor){
                    //这里一定要关闭，否则会耗尽连接数。
                    //报Cannot get Jedis connection; nested exception is redis.clients.jedis.exceptions.JedisException: Could not get
                    cursor.close();
                }
            } catch (Exception e) {
                log.error("关闭Cursor异常");
                log.error(e.getMessage(), e);
            }
        }
        log.info("scan扫描共耗时：{} ms key数量：{}", System.currentTimeMillis() - start, result.size());
        return result;
    }
}
