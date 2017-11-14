package com.chengzg.wxshop.interceptor;

import com.chengzg.wxshop.utils.CookieUtil;
import com.chengzg.wxshop.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class WeixinInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(WeixinInterceptor.class);

	protected HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {
    	
    	logger.info("WeixinInterceptor 执行顺序: 1、preHandle================");
    	setRequest(request);
		logger.info("WeixinInterceptor uri:{}",request.getRequestURI());
    	//从cookie中获取openId，如果没有获取到跳转到高级接口页面,继续往下执行
    	String openId = CookieUtil.getCookieValue(request, "openId");
    	logger.info("preHandle openId:" + openId);
    	if(openId == null || "".equals(openId.trim())){
    		CookieUtil.addCookie(response, "openId", "123123123", 12000, "/");
    		
    		String urlSource = request.getParameter("urlSource");
    		logger.info("preHandle 微信回调方法：" + PropertiesUtil.getPropValAsString("domain.url") + "page/wxtool/jumpWeixinPage?urlSource=" + urlSource);
    		response.sendRedirect(PropertiesUtil.getPropValAsString("domain.url") + "page/wxtool/jumpWeixinPage?urlSource=" + urlSource);//address=" + uri + "?" + param
    		return false;
    	}

		return true;  
    }

	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}




	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(HttpServletRequest,
	 * HttpServletResponse, Object, Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion 请求结束");
	}
}
