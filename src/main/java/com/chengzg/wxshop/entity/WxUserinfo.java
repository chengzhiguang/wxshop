package com.chengzg.wxshop.entity;

import java.util.Date;

public class WxUserinfo {
    private Long id;

    private String userPhone;

    private String wxOpenId;

    private String wxNikeName;

    private String wxCountry;

    private String wxCity;

    private String wxLanguage;

    private String wxProvince;

    private Integer wxSex;

    private String wxHeadPic;

    private Date crateTime;

    private Integer isDel;

    private Date lastModifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWxNikeName() {
        return wxNikeName;
    }

    public void setWxNikeName(String wxNikeName) {
        this.wxNikeName = wxNikeName;
    }

    public String getWxCountry() {
        return wxCountry;
    }

    public void setWxCountry(String wxCountry) {
        this.wxCountry = wxCountry;
    }

    public String getWxCity() {
        return wxCity;
    }

    public void setWxCity(String wxCity) {
        this.wxCity = wxCity;
    }

    public String getWxLanguage() {
        return wxLanguage;
    }

    public void setWxLanguage(String wxLanguage) {
        this.wxLanguage = wxLanguage;
    }

    public String getWxProvince() {
        return wxProvince;
    }

    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince;
    }

    public Integer getWxSex() {
        return wxSex;
    }

    public void setWxSex(Integer wxSex) {
        this.wxSex = wxSex;
    }

    public String getWxHeadPic() {
        return wxHeadPic;
    }

    public void setWxHeadPic(String wxHeadPic) {
        this.wxHeadPic = wxHeadPic;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}