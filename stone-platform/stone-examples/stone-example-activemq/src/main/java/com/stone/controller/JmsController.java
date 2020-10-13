package com.stone.controller;

import com.stone.queue.producer.JmsProducer;
import com.stone.topic.producer.JmsTopicProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class JmsController {

    @Resource
    private JmsProducer jmsProducer;

    @Resource
    private JmsTopicProducer jmsTopicProducer;

    /**
     * 发送点对点 消息信息
     *
     * @param msg
     * @return
     */
    @GetMapping(value = "sendQueue")
    public String sendQueue(@RequestParam(value = "msg") String msg) {
        jmsProducer.sendQueue(msg);
        return "操作成功";
    }

    /**
     * 发送 topic 信息
     *
     * @param msg
     * @return
     */
    @GetMapping(value = "sendTopic")
    public String sendTopic(@RequestParam(value = "msg") String msg) {
        jmsTopicProducer.sendTopic(msg);
        return "操作成功";
    }

}
