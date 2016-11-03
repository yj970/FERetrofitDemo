//
// LoginResponse.java
// feep
//
// Created by LuTH on 2011-11-30.
// Copyright 2011 flyrise. All rights reserved.
//
package demo.yj.cn.feretrofitdemo.model;


/**
 * 登录响应协议（通用）
 *
 * @author LuTH
 */
public class LoginResponse extends ResponseContent {

    private String maxValue;
    private String userID;
    private String userName;
    private String logoUrl;
    private String userPost;

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    /**

     * FE6.03增加 by罗展健
     */
    private String accessToken;
    /**
     * FE65增加服务器版本
     */
    private String feVersion;
    /**
     * FE65增加当前人头像
     */
    private String headUrl;

    private String department;

    private String imid;

    private String imtoken;

    // 督办管理标示，1：有督办管理权限 0：无督办管理权限
    private String superviorManage;

    public String getSuperviorManage() {
        return superviorManage;
    }

    public void setSuperviorManage(String superviorManage) {
        this.superviorManage = superviorManage;
    }

    public String getDepartment () {
        return department;
    }

    public void setDepartment (String department) {
        this.department = department;
    }

    public String getFeVersion () {
        return feVersion;
    }

    public void setFeVersion (String feVersion) {
        this.feVersion = feVersion;
    }

    public String getHeadUrl () {
        return headUrl;
    }

    public void setHeadUrl (String headUrl) {
        this.headUrl = headUrl;
    }

    public String getLogoUrl () {
        return logoUrl;
    }

    public void setLogoUrl (String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getUserID () {
        return userID;
    }

    public void setUserID (String userID) {
        this.userID = userID;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getAccessToken () {
        return accessToken;
    }

    public void setAccessToken (String accessToken) {
        this.accessToken = accessToken;
    }

    public String getImid () {
        return imid;
    }

    public void setImid (String imid) {
        this.imid = imid;
    }

    public String getImtoken () {
        return imtoken;
    }

    public void setImtoken (String imtoken) {
        this.imtoken = imtoken;
    }


    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }
}
