package com.stone.queue.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

@Slf4j
@Component
public class JmsProducer {

    @Resource
    private Queue queue;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送订阅消息信息
     *
     * @param msg
     */
    public void sendQueue(String msg) {
        log.info("开始发送Queue信息：{}", msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

}
