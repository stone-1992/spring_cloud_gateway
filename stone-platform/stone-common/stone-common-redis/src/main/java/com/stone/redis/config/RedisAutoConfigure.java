package com.stone.redis.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.stone.core.jackjson.JavaTimeModule;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @classname RedisConfig
 * @description redis template配置
 * @date 2020/3/4 9:50
 * @author stone
 */
@EnableConfigurationProperties({RedisProperties.class})
@Configuration
public class RedisAutoConfigure {

    /**
     * RedisTemplate配置
     * @param factory
     */
    /**
     @Bean public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
     RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
     redisTemplate.setConnectionFactory(factory);

     RedisSerializer<String> stringSerializer = new StringRedisSerializer();
     Jackson2JsonRedisSerializer<Object> redisObjectSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

     Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
     ObjectMapper objectMapper = builder.locale(Locale.CHINA)
     .timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
     .simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN)
     .modules(new JavaTimeModule())
     .build();
     objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
     objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
     //将类名称序列化到json串中，去掉会导致得出来的的是LinkedHashMap对象，直接转换实体对象会失败
     objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
     //已弃用
     //objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
     //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
     objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

     redisObjectSerializer.setObjectMapper(objectMapper);

     redisTemplate.setKeySerializer(stringSerializer);
     redisTemplate.setHashKeySerializer(stringSerializer);
     redisTemplate.setValueSerializer(redisObjectSerializer);
     redisTemplate.afterPropertiesSet();
     return redisTemplate;
     }
     */

    /**
     * 使用Jackson序列化
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean(value = "redisTemplateNew")
    public <K, V> RedisTemplate<K, V> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        ObjectMapper mapper = builder.locale(Locale.CHINA)
                .timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                .simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN)
                .modules(new JavaTimeModule())
                .build();
        /**
         * 将类名称序列化到json串中，去掉会导致得出来的的是LinkedHashMap对象，直接转换实体对象会失败
         */
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        RedisSerializer<Object> jsonRedisSerializer = new GenericJackson2JsonRedisSerializer(mapper);

        redisTemplate.setDefaultSerializer(jsonRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jsonRedisSerializer);

        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return redisTemplate;
    }
}
