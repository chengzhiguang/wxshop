package com.chengzg.wxshop.algorithm;

import com.google.common.collect.Range;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingjdbc.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;

/**
 * Created by chengzg3 on 2017/12/5.
 */
public final  class PreciseModuloDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<String> shardingValue) {
        for (String each : availableTargetNames) {
            return each;
            /*if (each.endsWith(shardingValue.getValue()+"")) {
                return each;
            }*/
        }
        throw new UnsupportedOperationException();
    }
}
