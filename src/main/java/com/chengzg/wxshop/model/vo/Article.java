package com.chengzg.wxshop.model.vo;

/**
 * 图文
 * 
 * @author qc
 * 
 */
public class Article {
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;

	public Article() {

	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
