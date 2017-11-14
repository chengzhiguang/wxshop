package com.chengzg.wxshop.elastic;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 定时更新合同和锁状态信息
 *
 * @Author liyb58
 * @Date 2016/11/11 17:17
 */
@Component
public class TestJob extends AbstractSimpleElasticJob {

    private static final Logger logger = LoggerFactory.getLogger(TestJob.class);



    /**
     * 批量更新合同与锁状态信息
     *
     * @return
     * @Author liyb58
     * @Date 2016/11/29 14:58
     */
    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
        //接入cat
        Transaction t = Cat.newTransaction("job", this.getClass().getSimpleName());
        try {
            //Thread.sleep(20000);
            logger.info("苏醒");
            t.setStatus(Message.SUCCESS);
            logger.info("结束");
        } catch (Exception e) {
            t.setStatus(e);
            Cat.logError(e);
        } finally {
            t.complete();
        }


    }


    /**
     * 根据本机分片数和需要处理的数据id，获取本机执行的数据id集合
     *
     * @param context
     * @param toBeExecutedIdList 需要处理的数据id
     * @return
     * @Author liyb58
     * @Date 2016/12/6 15:59
     */
    public static List getDealDataId(JobExecutionMultipleShardingContext context, List toBeExecutedIdList) {
        //没有基础数据
        if (CollectionUtils.isEmpty(toBeExecutedIdList)) {
            return null;
        }
        //得到分片项
        List<Integer> shardingItems = context.getShardingItems();
        if (shardingItems == null || shardingItems.isEmpty()) {
            logger.info("该服务器未分配到分片项，放弃执行");
            return null;
        }

        int shardingTotalCount = context.getShardingTotalCount();//分片总数
        //处理分片项和实际数据的对应关系
        List<Object> realToBeExecutedIdList = Lists.newArrayList();

        //根据分片总数，和本机分片数，过去本机处理数据
        for (Object id : toBeExecutedIdList) {
            int shardingItem = id.hashCode() % shardingTotalCount;
            if (shardingItems.contains(shardingItem)) {
                realToBeExecutedIdList.add(id);
            }
        }
        return realToBeExecutedIdList;
    }




}
