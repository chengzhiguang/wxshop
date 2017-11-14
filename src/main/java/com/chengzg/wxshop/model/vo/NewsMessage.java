package com.chengzg.wxshop.model.vo;

import java.util.List;

/**
 * 响应图文
 * 
 * @author qc
 * 
 */
public class NewsMessage  {
	private int ArticleCount;

	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;
	public NewsMessage(){
		super();
	}
	
	//开发者微信号 
	private String ToUserName;
	//发送方帐号（一个OpenID） 
	private String FromUserName;
	//消息创建时间 （整型） 
	private Long CreateTime;
	//消息类型，event 
	private String MsgType;
	//事件类型，subscribe(订阅)、unsubscribe(取消订阅) 
	private String Event;
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
