package com.chengzg.wxshop.utils;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.google.common.collect.Lists;
import com.ziroom.busrecoup.RecoupJob;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * elastic 工具类
 *
 * @Author liyb58
 * @Date 2016/12/6 15:53
 * @return
 */
public class ElasticUtils {
    private static final Logger logger = LoggerFactory.getLogger(ElasticUtils.class);

    /**
     * 根据本机分片数和需要处理的数据id，获取本机执行的数据id集合
     *
     * @param context
     * @param toBeExecutedIdList 需要处理的数据id
     * @return
     * @Author liyb58
     * @Date 2016/12/6 15:59
     */
    public static List getDealDataId(JobExecutionMultipleShardingContext context, List<Object> toBeExecutedIdList) {
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
        List realToBeExecutedIdList = Lists.newArrayList();

        //根据分片总数，和本机分片数，过去本机处理数据
        for (Object id : toBeExecutedIdList) {
            int shardingItem = id.hashCode() % shardingTotalCount;
            if (shardingItems.contains(shardingItem)) {
                realToBeExecutedIdList.add(id);
            }
        }
        return realToBeExecutedIdList;
    }


    /**
     * 生成匹配规则任务
     *
     * @return
     * @param jobClass
     * @param jobName
     * @param jobDesc
     * @param jobJsonParam
     * @param async
     * @Author liyb58
     * @Date 2017/1/12 14:41
     */
    public static RecoupJob createJob(String jobClass, String jobName, String jobDesc, String jobJsonParam, boolean async, Date startTime) {
        RecoupJob recoupJob = new RecoupJob();
        recoupJob.setJobClass(jobClass);//必须指定
        recoupJob.setJobName(jobName);//必须指定
        recoupJob.setJobDesc(jobDesc);//必须指定
        recoupJob.setRetryTotalTimes(5);//补偿5次，可以不指定，默认为3次
        recoupJob.setStartTime(Long.valueOf(TimeUtility.formatTimeStr(startTime, TimeUtility.TIME_FORMAT_YYYYMMDDHHMMss)));
        recoupJob.setJobJsonParam(jobJsonParam);//必须指定
        recoupJob.setAsyncExecute(async);//可以不指定，是否开启异步执行，默认false
        return recoupJob;
    }


}
