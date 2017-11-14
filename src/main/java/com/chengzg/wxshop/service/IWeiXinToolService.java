package com.chengzg.wxshop.service;


import com.chengzg.wxshop.model.vo.TokenBean;
import com.chengzg.wxshop.model.vo.WeiXinUser;

public interface IWeiXinToolService {

	TokenBean getToken(String code);
	
	/**
	 * 根据token得到微信用户信息
	 * @param tokenBean
	 * @return
	 */
	WeiXinUser getWXUserInfo(TokenBean tokenBean);
}
