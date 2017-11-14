package com.chengzg.wxshop.controller.support;

import org.apache.log4j.Logger;

/**
 * Created by czg on 2016/12/15.
 */
public class BaseController {

    private static Logger logger = Logger.getLogger(BaseController.class);
    /**
     * 失败返回
     * @param code
     * @return
     */
    protected ReturnResult errorReturn(int code) {
        return ReturnResult.failure(code);
    }

    protected ReturnResult errorReturn(int code, String message) {
        return ReturnResult.failure(code, message);
    }


    /**
     * 成功返回
     * @param data
     * @return
     */
    protected ReturnResult successReturn(Object data) {
        return ReturnResult.ok(data);
    }

}
