package com.stone.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * kafka 生产者
 *
 * @author stone
 */
@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> void send(String topic, T entity) {
        try {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, objectMapper.writeValueAsString(entity));
        } catch (JsonProcessingException e) {
            log.error("发送 kafka 数据异常");
            log.error(e.getMessage(), e);
        }
    }
}
