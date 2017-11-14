package com.chengzg.wxshop.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/10/20.
 */
@Component
public class DbQueueListenter implements ChannelAwareMessageListener {
    private static Logger logger = LoggerFactory.getLogger(DbQueueListenter.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            channel.basicQos(10);
            String messageStr = new String(message.getBody(), "UTF-8");
            logger.info("DbQueueListenter ：" + messageStr);
        } catch (Exception e) {
            logger.error("DbQueueListenter error", e);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
