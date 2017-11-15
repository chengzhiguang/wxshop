package com.chengzg.wxshop.controller;

import com.chengzg.wxshop.controller.support.BaseController;
import com.chengzg.wxshop.model.vo.TokenBean;
import com.chengzg.wxshop.model.vo.WeiXinUser;
import com.chengzg.wxshop.service.IWeiXinToolService;
import com.chengzg.wxshop.service.IWeixinService;
import com.chengzg.wxshop.service.IWeixinUserInfoService;
import com.chengzg.wxshop.utils.CookieUtil;
import com.chengzg.wxshop.utils.HttpUtil;
import com.chengzg.wxshop.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/9.
 */
@Controller
@RequestMapping("page/wxtool")
public class WeixinToolContraller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(WeixinToolContraller.class);

    @Autowired
    private IWeiXinToolService weiXinToolService;

    @Autowired
    private IWeixinUserInfoService weixinUserInfoService;

    @RequestMapping(value="jumpWeixinPage",method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView jumpWeixinPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String urlSource = HttpUtil.getParameter(request, "urlSource", null);
            if(urlSource != null) {
                request.setAttribute("urlSource", urlSource);
            }

            logger.info("jumpWeixinPage 微信appId:" + PropertiesUtil.getPropValAsString("wx_app_id"));

            request.setAttribute("appId", PropertiesUtil.getPropValAsString("wx_app_id"));
            request.setAttribute("redirectUri", PropertiesUtil.getPropValAsString("domain.url") + "/page/wxtool/getOpenId");
            return new ModelAndView("wxjump/jumpPage");
        } catch (Exception e) {
            logger.error("jumpWeixin 异常", e);
            return new ModelAndView("404");
        }

    }

    @RequestMapping(value="getOpenId",method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getOpenId(HttpServletRequest request,
                                  HttpServletResponse respnose){
        try{
            System.out.println("获取openId");
            String state = (String)request.getParameter("state");
            String code = (String)request.getParameter("code");
            logger.info("TestOAuthAction==code==" + code);
            TokenBean tokenBean = null;
            WeiXinUser weiXinUser = null;

            if (state == null || "".equals(state.trim()) || code == null || "".equals(code.trim())) {
                logger.info("weixinGetName 获取 state code 为空");
                throw new Exception("weixinGetName 获取 state code 为空");
            }

            //根据code获取token
            tokenBean = weiXinToolService.getToken(code);
            if(tokenBean == null) {
                logger.info("weixinGetName 获取 tokenBean 为空");
                throw new Exception("weixinGetName 获取 tokenBean 为空");
            }

            weiXinUser = weiXinToolService.getWXUserInfo(tokenBean);
            if(weiXinUser == null) {
                logger.info("weixinGetName 获取微信用户信息 为空");
                throw new Exception("weixinGetName 获取微信用户信息 为空");
            }

            weixinUserInfoService.saveWxUserinfo(weiXinUser);
            CookieUtil.addCookie(respnose, "openId", weiXinUser.getOpenid(), 365*24*60*60, "/");
            CookieUtil.addCookie(respnose, "nikeName", weiXinUser.getNickname(), 365*24*60*60, "/");

            logger.info("weixinGetName==state==" + state);

            return new ModelAndView(new RedirectView(PropertiesUtil.getPropValAsString("domain.url") + "/page/home/toHomePage"));
        }catch (Exception e) {
            logger.error("weixinGetName 获取 state code 出现错误", e);
            return new ModelAndView("jsp/404");
        }
    }
}
