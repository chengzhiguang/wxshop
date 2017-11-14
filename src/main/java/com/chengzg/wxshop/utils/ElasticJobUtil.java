package com.chengzg.wxshop.utils;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by chengzhiguang on 2017/3/14.
 */
public class ElasticJobUtil {
    private static Logger logger = LoggerFactory.getLogger(ElasticJobUtil.class);

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
