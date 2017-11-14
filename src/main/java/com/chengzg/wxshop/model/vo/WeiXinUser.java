package com.chengzg.wxshop.model.vo;



import java.io.Serializable;

/**
 * 微信用户信息
 * @author zhaokai
 *
 */
public class WeiXinUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529887471384801572L;
	//{"openid":"oxwu7s9YcYIGIRT1eeEj2aJRDuiQ","nickname":"向天使挥手",
	//"sex":1,"language":"zh_CN","city":"昌平","province":"北京","country":"中国",
	//"headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/vi_32\/DYAIOgq83eqHqw9gU7QzTsGhrWreS9XV8OWgsLniapwPZww3FImZqEn3LLsUwVmYbahGoop545Y0kzwz2RtKu6w\/0",
	//"privilege":[]}
	private String openid;//用户的唯一标识
	private String nickname;//用户昵称
	private Integer sex;//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String province;//省-北京
	private String country;//国家，如中国为CN
	private String headimgurl;//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	private String language;//语言-zh-cn
	private String city;//市-昌平
	private String provincel;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getProvincel() {
		return provincel;
	}
	public void setProvincel(String provincel) {
		this.provincel = provincel;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
}
