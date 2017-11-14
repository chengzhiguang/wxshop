package com.chengzg.wxshop.service;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface MQProducer {
    /**
     * 发送消息到指定队列
     * @param queueKey
     * @param object
     */
    public void sendDataToQueue(String queueKey, Object object);

}
