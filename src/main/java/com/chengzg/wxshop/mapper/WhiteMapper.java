package com.chengzg.wxshop.mapper;

import com.chengzg.wxshop.entity.WhiteData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongh38@ziroom
 * @Date 2016/11/10
 * @Time 16:26
 * @Description
 * @Since 1.0.0
 */
@Repository
public interface WhiteMapper {
    List<WhiteData> whiteList();

    int addWhiteList(WhiteData whiteData);

    int delWhiteList(String serviceIp);

}
