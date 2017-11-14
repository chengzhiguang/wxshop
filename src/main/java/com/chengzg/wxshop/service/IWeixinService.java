package com.chengzg.wxshop.service;

import java.util.Map;

/**
 * 微信业务层接口
 * @author chengzg
 * @date 2015-3-13 上午10:38:12
 * 
 */
public interface IWeixinService {
	/**
	 * 处理消息
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	String handleMsg(Map<String, String> msg) throws Exception;
}
