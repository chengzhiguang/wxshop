package com.chengzg.wxshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chengzg.wxshop.model.vo.TokenBean;
import com.chengzg.wxshop.model.vo.WeiXinUser;
import com.chengzg.wxshop.service.IWeiXinToolService;
import com.chengzg.wxshop.utils.StrUtils;
import com.chengzg.wxshop.utils.WxUtility;
import com.chengzg.wxshop.utils.http.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/11/9.
 */
public class WeiXinToolServiceImpl implements IWeiXinToolService {
    private static Logger logger = LoggerFactory.getLogger(WeiXinToolServiceImpl.class);

    @Override
    public TokenBean getToken(String code) {
        if(code != null && !"".equals(code.trim())){

            try {
                logger.info("getToken CODE为====" + code);
                String geAccess_tokenturl = WxUtility.GET_WX_TOKEN + "&code=" + code;
                logger.info("getToken  geAccess_tokenturl  === " + geAccess_tokenturl);
                String json = MessageUtil.getTransmessageStr(geAccess_tokenturl);
                TokenBean tokenBean = analysisaccess_token(json);
                logger.info("获取token串为：" + tokenBean.getAccess_token());
                return tokenBean;
            } catch (Exception e) {
                logger.info("getToken获取token is error", e);
            }
        }
        return null;
    }


    @Override
    public WeiXinUser getWXUserInfo(TokenBean tokenBean) {
        if(tokenBean != null && !"".equals(tokenBean.getAccess_token())){
            String getUserInfourl = WxUtility.get_WX_USERINFO+"&access_token="+
                    tokenBean.getAccess_token()+"&openid="+tokenBean.getOpenid();
            logger.info("getWXUserInfo 微信联系人信息访问地址为：URL[" + getUserInfourl + "]");
            String userjson = MessageUtil.getTransmessageStr(getUserInfourl);
            WeiXinUser weiXinUser = analysisUser(userjson);
            return weiXinUser;
        }
        return null;
    }


    public TokenBean analysisaccess_token(String json){
        if(StrUtils.isNullOrBlank(json)){
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        if(jsonObject != null && jsonObject.containsKey("access_token")){
            TokenBean tokenBean = (TokenBean) JSON.parseObject(jsonObject.getString("access_token"), TokenBean.class);
            return tokenBean;
        }

        return null;
    }

    private WeiXinUser analysisUser(String json) {
        if(StrUtils.isNullOrBlank(json)){
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        try {
            if(jsonObject != null && jsonObject.containsKey("openid")){
                if (jsonObject.containsKey("privilege")) {
                    jsonObject.remove("privilege");
                }
                WeiXinUser weiXinUser = (WeiXinUser)JSON.parseObject(jsonObject.toJSONString(), WeiXinUser.class);
                return weiXinUser;
            }
        }catch (Exception e) {
            logger.info("analysisUser 解析微信接口异常", e);
        }
        return null;
    }
}
