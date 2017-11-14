package com.chengzg.wxshop.service;


import com.chengzg.wxshop.model.vo.WeiXinUser;

public interface IWeixinUserInfoService {
	/**
	 * 保存微信信息到数据库
	 * @param weiXinUser
	 * @return
	 */
	Integer saveWxUserinfo(WeiXinUser weiXinUser);
}
