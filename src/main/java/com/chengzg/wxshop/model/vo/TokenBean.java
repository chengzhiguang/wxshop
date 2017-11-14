package com.chengzg.wxshop.model.vo;


import java.io.Serializable;

/**
 * 封装返回access_token时的json
 * @author zhaokai
 *
 */

public class TokenBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3023996741868835764L;
	
	private String access_token;//网页授权接口调用凭证
	private String expires_in;//access_token接口调用凭证超时时间，单位（秒）
	private String refresh_token;//用户刷新access_token 
	private String openid;//用户唯一标识
	private String scope;//用户授权的作用域，使用逗号（,）分隔 
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String accessToken) {
		access_token = accessToken;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expiresIn) {
		expires_in = expiresIn;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refreshToken) {
		refresh_token = refreshToken;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
