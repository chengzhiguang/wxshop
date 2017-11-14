package com.chengzg.wxshop.elastic;

import com.chengzg.wxshop.exception.ServiceException;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.ziroom.busrecoup.IRecoup;
import com.ziroom.busrecoup.JobStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengzhiguang on 2017/3/14.
 */
@Component
public class TestSimpleJob implements IRecoup {
    private static Logger logger = LoggerFactory.getLogger(TestSimpleJob.class);


    @Override
    public void recoup(String s, String s1) throws Exception {
        //接入cat
        Transaction t = Cat.newTransaction("job", this.getClass().getSimpleName());

        try {
            logger.info("开始执行，入参：{}", s);

            logger.info("苏醒");
            t.setStatus(Message.SUCCESS);
            logger.info("结束");
        } catch (Exception e) {
            t.setStatus(e);
            Cat.logError(e);
            throw e;
        } finally {
            t.complete();
        }
    }

    @Override
    public void afterRecoup(String jobStatus, String jobJsonParam, String busCode, String failReason) throws Exception {
        //尝试多次失败，发送报警邮件
        if (JobStatusEnum.FAILED.getCode().equals(jobStatus) && !failReason.contains("NotNeedContinueRecoupException")) {

        }
    }
}
