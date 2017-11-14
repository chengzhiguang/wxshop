package com.chengzg.wxshop.controller;

import com.chengzg.wxshop.controller.support.BaseController;
import com.chengzg.wxshop.utils.HttpUtil;
import com.chengzg.wxshop.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/9.
 */
@Controller
@RequestMapping("page/wxtool")
public class WeixinToolContraller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(WeixinToolContraller.class);

    @RequestMapping(value="jumpWeixinPage",method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView jumpWeixinPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String urlSource = HttpUtil.getParameter(request, "urlSource", null);
            if(urlSource != null) {
                request.setAttribute("urlSource", urlSource);
            }

            logger.info("jumpWeixinPage 微信appId:" + PropertiesUtil.getPropValAsString("wx_app_id"));

            request.setAttribute("appId", PropertiesUtil.getPropValAsString("wx_app_id"));
            request.setAttribute("redirectUri", PropertiesUtil.getPropValAsString("domain.url") + "/weixintool/weiXinToolController/getOpenId.do");
            return new ModelAndView("wxjump/jumpPage");
        } catch (Exception e) {
            logger.error("jumpWeixin 异常", e);
            return new ModelAndView("404");
        }

    }
}
