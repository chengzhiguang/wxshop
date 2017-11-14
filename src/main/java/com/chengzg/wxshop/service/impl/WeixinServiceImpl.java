package com.chengzg.wxshop.service.impl;

import com.chengzg.wxshop.enums.WxMessageType;
import com.chengzg.wxshop.model.vo.Article;
import com.chengzg.wxshop.model.vo.NewsMessage;
import com.chengzg.wxshop.model.vo.ViewMessage;
import com.chengzg.wxshop.service.IWeixinService;
import com.chengzg.wxshop.utils.PropertiesUtil;
import com.chengzg.wxshop.utils.Utility;
import com.chengzg.wxshop.utils.WxUtility;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 微信业务层实现类
 * @author chengzg
 * @date 2015-3-13 上午10:40:44
 * 
 */
@Service
public class WeixinServiceImpl implements IWeixinService {
	private static Logger logger = Logger.getLogger(WeixinServiceImpl.class);
	
	/**
	 * 处理消息
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public String handleMsg(Map<String, String> msg) throws Exception {
		String respMsg = "";
		String msgType = msg.get("MsgType");//消息类型
		String event = msg.get("Event");//事件类型
		String eventKey=msg.containsKey("EventKey")?msg.get("EventKey"):"";
		logger.info("handleMsg msgType:" + msgType);
		logger.info("handleMsg event:" + event);
		logger.info("handleMsg eventKey:" + eventKey);
		if(WxMessageType.TEXT.getType().equals(msgType)) {//文本消息
			String content = msg.get("Content");
			msg.put("Content", content);
			return handleTextMsg(msg);
			
		} else if(WxMessageType.VIDEO.getType().equals(msgType)) {//语音消息
			logger.info("handleMsg 接收到语音消息");
			String endText = "“抢年终奖”活动已结束\ue056";
			respMsg = WxUtility.sendTxtMessage(msg, endText);
		} else if(WxMessageType.CLICK.getType().equals(msgType)) {
			
		} else if(WxMessageType.EVENT.getType().equals(msgType)) {
			String fomUserName = msg.get("FromUserName");
			if(WxMessageType.UNSUBSCRIBE.getType().equals(event)) {//取消关注
				
				if(fomUserName == null){
					String endText = "系统累了\ue056";
					respMsg = WxUtility.sendTxtMessage(msg, endText);
				}
				
				logger.info("handleMsg 用户【" + fomUserName + "】取消关注");
				String endText = "感谢您的关注\ue056";
				respMsg = WxUtility.sendTxtMessage(msg, endText);
			} else if(WxMessageType.SUBSCRIBE.getType().equals(event)) {//关注
				if(fomUserName == null){
					String endText = "系统累了\ue056";
					respMsg = WxUtility.sendTxtMessage(msg, endText);
				}
				logger.info("handleMsg 用户【" + fomUserName + "】关注程式香油");
				StringBuffer str = new StringBuffer();
				str.append("亲爱的小伙伴");
				String endText = str.toString();
				respMsg = WxUtility.sendTxtMessage(msg, endText);
				
			} else if(WxMessageType.CLICK.getType().equals(event)) {//自定义按钮
				if(eventKey.equals("B1")) {//录入房源
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(msg.get("FromUserName"));
					newsMessage.setFromUserName(msg.get("ToUserName"));
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(WxMessageType.NEWS.getType());
					
					List<Article> articleList = new ArrayList<Article>();
					Article article = new Article();
					article.setTitle("大名府香油坊");
					article.setDescription("");
					article.setPicUrl(PropertiesUtil.getPropValAsString("domain.url") + "/img/images/logo.jpg");
					article.setUrl(PropertiesUtil.getPropValAsString("domain.url") + "/page/home/toHomePage?openId=" + msg.get("FromUserName"));
					articleList.add(article);
					logger.info("weixinService 请求的URL是："+article.getUrl());
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMsg=WxUtility.newMessageToXml(newsMessage);
					
				} else if(eventKey.equals("B2")) {//累计录入
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(msg.get("FromUserName"));
					newsMessage.setFromUserName(msg.get("ToUserName"));
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(WxMessageType.NEWS.getType());
					
					List<Article> articleList = new ArrayList<Article>();
					Article article = new Article();
					article.setTitle("累计录入");
					article.setDescription("");
					article.setPicUrl(PropertiesUtil.getPropValAsString("domain.url") + "/menuimg/totalenter.png");
					article.setUrl(PropertiesUtil.getPropValAsString("domain.url") + "/record/recordController/recordPage.action?status=-1&openId=" + msg.get("FromUserName"));
					articleList.add(article);
					logger.info("weixinService 请求的URL是："+article.getUrl());
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMsg=WxUtility.newMessageToXml(newsMessage);
				} else if(eventKey.equals("B3")) {//累计核准
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(msg.get("FromUserName"));
					newsMessage.setFromUserName(msg.get("ToUserName"));
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(WxMessageType.NEWS.getType());
					
					List<Article> articleList = new ArrayList<Article>();
					Article article = new Article();
					article.setTitle("累计核准");
					article.setDescription("");
					article.setPicUrl(PropertiesUtil.getPropValAsString("domain.url") + "/menuimg/totalpass.png");
					article.setUrl(PropertiesUtil.getPropValAsString("domain.url") + "/record/recordController/recordPage.action?status=2&openId=" + msg.get("FromUserName"));
					articleList.add(article);
					logger.info("weixinService 请求的URL是："+article.getUrl());
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMsg=WxUtility.newMessageToXml(newsMessage);
				} else if(eventKey.equals("B4")) {//审核中
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(msg.get("FromUserName"));
					newsMessage.setFromUserName(msg.get("ToUserName"));
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(WxMessageType.NEWS.getType());
					
					List<Article> articleList = new ArrayList<Article>();
					Article article = new Article();
					article.setTitle("审核中");
					article.setDescription("");
					article.setPicUrl(PropertiesUtil.getPropValAsString("domain.url") + "/menuimg/passing.png");
					article.setUrl(PropertiesUtil.getPropValAsString("domain.url") + "/record/recordController/recordPage.action?status=1&openId=" + msg.get("FromUserName"));
					articleList.add(article);
					logger.info("weixinService 请求的URL是："+article.getUrl());
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMsg=WxUtility.newMessageToXml(newsMessage);
				}
			} else if(WxMessageType.VIEW.getType().equals(event)) {//点击菜单跳转链接时的事件推送
				if(eventKey.contains("toHelpPage.action")) {
					ViewMessage viewMessage = new ViewMessage();
					viewMessage.setCreateTime(new Date().getTime());
					viewMessage.setEvent(WxMessageType.VIEW.getType());
					viewMessage.setEventKey("www.qq.com");
					viewMessage.setFromUserName(msg.get("ToUserName"));
					viewMessage.setToUserName(msg.get("FromUserName"));
					viewMessage.setMsgType(WxMessageType.EVENT.getType());
					respMsg=WxUtility.viewMessageToXml(viewMessage);
				}
				
			}
		} else if(WxMessageType.IMAGE.getType().equals(msgType)) {
			logger.info("handleMsg 接收到用户图片");
			String url = msg.get("PicUrl");
			logger.info("handleMsg 图片url：" + url);
			String endText = "“欢迎关注这么牛逼的公众号，你是2B么？\ue056";
			respMsg = WxUtility.sendTxtMessage(msg, endText);
		} else if(WxMessageType.LINK.getType().equals(msgType)) {
			
		} else if(WxMessageType.LOCATION.getType().equals(msgType)) {
			
		} else if(WxMessageType.MUSIC.getType().equals(msgType)) {
			
		} else if(WxMessageType.NEWS.getType().equals(msgType)) {
			
		} else if(WxMessageType.SCAN.getType().equals(msgType)) {
			
		} else if(WxMessageType.VIDEO.getType().equals(msgType)) {
			
		} else if(WxMessageType.VOICE.getType().equals(msgType)) {
			
		}

		return respMsg;
	}
	
	/**
	 * 文本信息
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public String handleTextMsg(Map<String, String> msg) {
		String respMsg=null;
		try {
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(msg.get("FromUserName"));
			newsMessage.setFromUserName(msg.get("ToUserName"));
			newsMessage.setCreateTime(new Date().getTime());
			
			String content = msg.get("Content");
			logger.info("传入的消息值为："+content);
			if(content!=null && "首页".equals(content)) {
				newsMessage.setMsgType(WxMessageType.NEWS.getType());
				
				List<Article> articleList = new ArrayList<Article>();
				Article article = new Article();
				article.setTitle("大名府香油坊");
				article.setDescription("一个专注传统工艺的高品质使用香油作坊！");
				article.setPicUrl(PropertiesUtil.getPropValAsString("domain.url") + "/img/images/logo.jpg");
													    
				article.setUrl(PropertiesUtil.getPropValAsString("domain.url") + "/home/homeController/toHomePage.action?openId=" + msg.get("FromUserName"));
				articleList.add(article);
				logger.info("weixinService 请求的URL是："+article.getUrl());
				newsMessage.setArticleCount(articleList.size());
				newsMessage.setArticles(articleList);
				respMsg=WxUtility.newMessageToXml(newsMessage);
			} else {
				logger.info("weixinService 我也不知道你要干啥");
				String endText = "你想要干啥，也不说清楚~~~\ue056";
				respMsg = WxUtility.sendTxtMessage(msg, endText);
			}
		} catch (Exception e) {
			String endText = "小微信有点累啦\ue056";
			respMsg = WxUtility.sendTxtMessage(msg, endText);
		}
		
		logger.info("weixinService.handleTextMsg 返回的结果是："+respMsg);
		return respMsg;
	}

}
