package com.chengzg.wxshop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chengzg.wxshop.controller.support.BaseController;
import com.chengzg.wxshop.controller.support.ReturnResult;
import com.chengzg.wxshop.entity.WhiteData;
import com.chengzg.wxshop.enums.RuleEnum;
import com.chengzg.wxshop.service.MQProducer;
import com.chengzg.wxshop.service.WhiteListService;
import com.chengzg.wxshop.utils.Asserts;
import com.chengzg.wxshop.utils.ElasticUtils;
import com.chengzg.wxshop.utils.HttpUtil;
import com.chengzg.wxshop.utils.TimeUtility;
import com.ziroom.busrecoup.RecoupJob;
import com.ziroom.busrecoup.RecoupJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by czg on 2017/1/13.
 */
@Controller
@RequestMapping("api/test")
public class TestController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private WhiteListService whiteListService;

    @Autowired
    private MQProducer mqProducer;

    @Autowired(required = false)
    private RecoupJobService recoupJobService;

    /**
     * 查询列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "sendTestRabbitMq", method = {RequestMethod.GET})
    public @ResponseBody
    ReturnResult sendTestRabbitMq(HttpServletRequest request, HttpServletResponse response) {

        String queueKey = HttpUtil.getParameter(request, "queueKey", null);
        Asserts.checkNullOrEmpty(queueKey, "对接Key不能为空");
        Map<String,Object> msg = new HashMap<>();
        msg.put("data","hello,rabbmitmq!");
        mqProducer.sendDataToQueue(queueKey, msg);

        return successReturn(null);
    }

    @RequestMapping(value = "addTestEleasticJob", method = {RequestMethod.GET})
    public @ResponseBody
    ReturnResult addTestEleasticJob(HttpServletRequest request, HttpServletResponse response) {

        JSONObject bu = new JSONObject();
        bu.put("code", "11111");
        bu.put("name", "北京自如");

        RecoupJob job = ElasticUtils.createJob(RuleEnum.getJobClassByCode(1),
                RuleEnum.getJobNameByCode(1), "测试job",
                JSON.toJSONString(bu), true, TimeUtility.dateAddMinutes(new Date(), 2));
        recoupJobService.saveRecoupJob(job);

        return successReturn(null);
    }

}
