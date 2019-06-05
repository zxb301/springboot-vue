package com.ufoai.platform.pojo.base;

public class LogOperateRes {

    private Long id;


    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户操作
     */
    private String operation;


    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private String gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
