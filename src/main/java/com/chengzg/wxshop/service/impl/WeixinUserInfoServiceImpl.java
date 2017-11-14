package com.chengzg.wxshop.service.impl;

import com.chengzg.wxshop.model.vo.WeiXinUser;
import com.chengzg.wxshop.service.IWeixinUserInfoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeixinUserInfoServiceImpl implements IWeixinUserInfoService {

	private static Logger logger = Logger.getLogger(WeixinUserInfoServiceImpl.class);

	@Override
	public Integer saveWxUserinfo(WeiXinUser weiXinUser) {
		try {
			/*//1 根据openId从数据库中获取
			//不存在就添加
			//存在就修改，并修改删除标识
			OilWxUserinfo oilWxUserinfo = new OilWxUserinfo();
			oilWxUserinfo.setWxOpenId(weiXinUser.getOpenid());
			List<OilWxUserinfo> oilWxUserinfoList = oilWxUserinfoDao.queryOilWxUserinfos(oilWxUserinfo);
			if(oilWxUserinfoList == null) {
				logger.error("saveWxUserinfo 查询微信用户信息错误。oilWxUserinfoList为null");
				return -1;
			}
			if(oilWxUserinfoList.size() > 0) {//已经存在
				OilWxUserinfo oilWxUserinfo2 = oilWxUserinfoList.get(0);
				oilWxUserinfo2.setIsDel(DelType.NO_DEL.getIndex());
				oilWxUserinfo2.setLastModifyTime(new Date());
				oilWxUserinfo2.setWxHeadPic(weiXinUser.getHeadimgurl());
				oilWxUserinfo2.setWxNikeName(weiXinUser.getNickname());
				oilWxUserinfo2.setWxOpenId(weiXinUser.getOpenid());
				oilWxUserinfo2.setWxCity(weiXinUser.getCity());
				oilWxUserinfo2.setWxCountry(weiXinUser.getCountry());
				oilWxUserinfo2.setWxLanguage(weiXinUser.getLanguage());
				oilWxUserinfo2.setWxProvince(weiXinUser.getProvince());
				oilWxUserinfo2.setWxSex(weiXinUser.getSex());
				oilWxUserinfoDao.modifyOilWxUserinfoById(oilWxUserinfo2);
			} else {//不存在
				OilWxUserinfo oilWxUserinfo2 = new OilWxUserinfo();
				oilWxUserinfo2.setCrateTime(new Date());
				oilWxUserinfo2.setIsDel(DelType.NO_DEL.getIndex());
				oilWxUserinfo2.setLastModifyTime(new Date());
				oilWxUserinfo2.setWxHeadPic(weiXinUser.getHeadimgurl());
				oilWxUserinfo2.setWxNikeName(weiXinUser.getNickname());
				oilWxUserinfo2.setWxOpenId(weiXinUser.getOpenid());
				oilWxUserinfo2.setWxCity(weiXinUser.getCity());
				oilWxUserinfo2.setWxCountry(weiXinUser.getCountry());
				oilWxUserinfo2.setWxLanguage(weiXinUser.getLanguage());
				oilWxUserinfo2.setWxProvince(weiXinUser.getProvince());
				oilWxUserinfo2.setWxSex(weiXinUser.getSex());
				oilWxUserinfoDao.addOilWxUserinfo(oilWxUserinfo2);
			}*/
		} catch (Exception e) {
			logger.error("saveWxUserinfo 异常", e);
		}
		
		return -1;
	}

}
