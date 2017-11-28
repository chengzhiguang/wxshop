package com.chengzg.wxshop.mapper;

import com.chengzg.wxshop.entity.WxUserinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface WxUserinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxUserinfo record);

    int insertSelective(WxUserinfo record);

    WxUserinfo selectByPrimaryKey(Long id);

    WxUserinfo selectByWxOpenId(String wxOpenId);

    int updateByPrimaryKeySelective(WxUserinfo record);

    int updateByPrimaryKey(WxUserinfo record);
}