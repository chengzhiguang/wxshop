package com.chengzg.wxshop.servlet;

import com.chengzg.wxshop.service.IWeixinService;
import com.chengzg.wxshop.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author chengzg
 * @date 2015-3-11 下午07:44:48
 * 
 */
public class WeixinServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6586969801774474146L;
	
	private static Logger logger = LoggerFactory.getLogger(WeixinServlet.class);

	private IWeixinService weixinService = (IWeixinService)SpringHelper.getBean("weixinService");

	public WeixinServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		logger.info("doPost 开始访问");
		String signature = request.getParameter("signature");        // 随机字符串
		if (StrUtils.isNotNullOrBlank(signature)) {
			logger.info("doPost 微信接口验证");
			String echostr = request.getParameter("echostr");        // 时间戳
			String timestamp = request.getParameter("timestamp");        // 随机数
			String nonce = request.getParameter("nonce");
			response.getWriter().print(echostr);
			return;
		}
		try {
			logger.info("doPost 微信交互");
	        String content= HttpUtil.getTextContent(request.getInputStream(),"utf-8");
	        logger.info("doPost content:" + content);
	        String responseXML = "123";
	        
	        if(StringUtils.isNotBlank(content)) {
	        	Map<String,String> msg = WxUtility.parseXML(content);
	        	logger.info("doPost 微信返回的数据是："+msg.toString());
	        	responseXML=weixinService.handleMsg(msg);
	        }
        	logger.info("doPost responseXML："+responseXML);
	        out.print(responseXML);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
		/*logger.info("doPost 开始访问");
		// 微信加密签名
        String signature = request.getParameter("signature");        // 随机字符串
        String echostr = request.getParameter("echostr");        // 时间戳
        String timestamp = request.getParameter("timestamp");        // 随机数
        String nonce = request.getParameter("nonce");
        response.getWriter().print(echostr);*/
		
	}
}
