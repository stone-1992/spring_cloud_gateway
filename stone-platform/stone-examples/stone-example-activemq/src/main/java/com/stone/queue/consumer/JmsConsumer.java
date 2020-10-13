package com.stone.queue.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsConsumer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 点对点消息模式
     *
     * @param msg
     */
    @JmsListener(destination = "sms.queue", containerFactory = "queueListenerFactory")
    public void handleMsg(String msg) {
        log.info("开始接收Queue消息：{}", msg);
    }

    /**
     * 发布-订阅模式 Topic1
     *
     * @param text
     */
    @JmsListener(destination = "sms.topic", containerFactory = "topicListenerFactory")
    public void receiveTopic1(String text) {
        log.info("Topic1, 开始接收Topic消息 : {}", text);
    }

    /**
     * 发布-订阅模式 Topic2
     *
     * @param text
     */
    @JmsListener(destination = "sms.topic", containerFactory = "topicListenerFactory")
    public void receiveTopic2(String text) {
        log.info("Topic2, 开始接收Topic消息 : {}", text);
    }

}
