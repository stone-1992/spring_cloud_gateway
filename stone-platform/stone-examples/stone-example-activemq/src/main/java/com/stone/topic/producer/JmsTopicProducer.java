package com.stone.topic.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Topic;


@Slf4j
@Component
public class JmsTopicProducer {

    @Resource
    private Topic topic;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendTopic(String msg) {
        log.info("开始发送Topic信息 : {}", msg);
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }

}
