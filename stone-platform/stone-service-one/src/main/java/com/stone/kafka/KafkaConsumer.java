package com.stone.kafka;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.entity.vo.SiteVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka 消费者
 */
@Component
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 监听 topic 消费 kafka 数据记录
     *
     * @param content
     * @throws Exception
     */
    @KafkaListener(topics = "kafka.test.log")
    public void process(ConsumerRecord<String, String> content) throws Exception {
        String value = content.value();
        if (StrUtil.isNotBlank(value)) {
            SiteVO siteVO = objectMapper.readValue(content.value(), SiteVO.class);
            System.err.println(siteVO);
        }
    }
}
