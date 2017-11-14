package com.chengzg.wxshop.enums;
/**
 * 
 * 返回消息类型
 * 
 * @author chengzg
 * @date 2015-3-11 下午09:47:33
 * 
 */
public enum WxMessageType {
	/**
	 * 文本
	 */
	TEXT("文本", "text", 1),
	/**
	 * 音乐 
	 */
	MUSIC("音乐", "music", 2),
	/**
	 * 图文  
	 */
	NEWS("图文", "news", 3),
	/**
	 * 图片 
	 */
	IMAGE("图片", "image", 4),
	/**
	 * 链接 
	 */
	LINK("链接", "link", 5),
	/**
	 * 地理位置
	 */
	LOCATION("地理位置", "location", 6),
	/**
	 * 音频
	 */
	VOICE("音频", "voice", 7),
	/**
	 * 视频
	 */
	VIDEO("视频", "voice", 8),
	/**
	 * 推送
	 */
	EVENT("推送", "event", 9),
	/**
	 * 订阅
	 */
	SUBSCRIBE("订阅", "subscribe", 10),
	/**
	 * 取消订阅
	 */
	UNSUBSCRIBE("取消订阅", "unsubscribe", 11),
	/**
	 * 自定义菜单点击事件
	 */
	CLICK("自定义菜单点击事件", "CLICK", 12),
	/**
	 * 已关注用户，扫描二维码时接收到的微信平台推送的消息事件
	 */
	SCAN("已关注用户，扫描二维码时接收到的微信平台推送的消息事件", "SCAN", 13),
	/**
	 * 点击菜单跳转链接时的事件推送
	 */
	VIEW("点击菜单跳转链接时的事件推送", "VIEW", 14),
	;
	
	// 成员变量
	private String type;
	private String name;
	private int index;

	private WxMessageType(String name, String type, int index) {
		this.name = name;
		this.type = type;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
