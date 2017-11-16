package com.chengzg.wxshop.service.impl;

import com.chengzg.wxshop.entity.WxUserinfo;
import com.chengzg.wxshop.enums.DelType;
import com.chengzg.wxshop.mapper.WxUserinfoMapper;
import com.chengzg.wxshop.model.vo.WeiXinUser;
import com.chengzg.wxshop.service.IWeixinUserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinUserInfoServiceImpl implements IWeixinUserInfoService {

	private static Logger logger = Logger.getLogger(WeixinUserInfoServiceImpl.class);

	@Autowired
	private WxUserinfoMapper wxUserinfoMapper;

	@Override
	public Integer saveWxUserinfo(WeiXinUser weiXinUser) {
		try {
			if (weiXinUser == null) {
				return -1;
			}
			WxUserinfo wxUserinfo = wxUserinfoMapper.selectByWxOpenId(weiXinUser.getOpenid());
			if (wxUserinfo == null) {
				wxUserinfo = new WxUserinfo();
				wxUserinfo.setCrateTime(new Date());
				wxUserinfo.setIsDel(DelType.NO.getIndex());
				wxUserinfo.setLastModifyTime(new Date());
				wxUserinfo.setWxHeadPic(weiXinUser.getHeadimgurl());
				wxUserinfo.setWxNikeName(weiXinUser.getNickname());
				wxUserinfo.setWxOpenId(weiXinUser.getOpenid());
				wxUserinfo.setWxCity(weiXinUser.getCity());
				wxUserinfo.setWxCountry(weiXinUser.getCountry());
				wxUserinfo.setWxLanguage(weiXinUser.getLanguage());
				wxUserinfo.setWxProvince(weiXinUser.getProvince());
				wxUserinfo.setWxSex(weiXinUser.getSex());
				wxUserinfoMapper.insertSelective(wxUserinfo);
			} else {
				wxUserinfo.setIsDel(DelType.NO.getIndex());
				wxUserinfo.setLastModifyTime(new Date());
				wxUserinfo.setWxHeadPic(weiXinUser.getHeadimgurl());
				wxUserinfo.setWxNikeName(weiXinUser.getNickname());
				wxUserinfo.setWxOpenId(weiXinUser.getOpenid());
				wxUserinfo.setWxCity(weiXinUser.getCity());
				wxUserinfo.setWxCountry(weiXinUser.getCountry());
				wxUserinfo.setWxLanguage(weiXinUser.getLanguage());
				wxUserinfo.setWxProvince(weiXinUser.getProvince());
				wxUserinfo.setWxSex(weiXinUser.getSex());
				wxUserinfoMapper.updateByPrimaryKeySelective(wxUserinfo);
			}
		} catch (Exception e) {
			logger.error("saveWxUserinfo 异常", e);
		}
		
		return -1;
	}

}
