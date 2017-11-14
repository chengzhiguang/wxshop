package com.chengzg.wxshop.service.impl;

import com.chengzg.wxshop.service.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/19.
 */
@Service
public class MQProducerImpl implements MQProducer {
    private static Logger logger = LoggerFactory.getLogger(MQProducerImpl.class);
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendDataToQueue(String queueKey, Object object) {
        try {
            amqpTemplate.convertAndSend(queueKey, object);
        } catch (Exception e) {
            logger.error("",e);
        }
    }
}
