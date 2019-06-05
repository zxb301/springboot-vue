package com.ufoai.platform.pojo.base;

import javax.validation.constraints.NotNull;

public class PageParamReq {

    @NotNull(message = "rows 不能为空")
    private  Integer rows;
    @NotNull(message = "page 不能为空")
    private  Integer page;

    private  String beginTime;

    private  String code;

    private  String endTime;

    private  String  key1;

    private  String  key2;

    private  String  key3;

    private String type;

    private  String status;

    private  Long  userId;

    private  String other;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "PageParamReq{" +
                "rows=" + rows +
                ", page=" + page +
                ", beginTime='" + beginTime + '\'' +
                ", code='" + code + '\'' +
                ", endTime='" + endTime + '\'' +
                ", key1='" + key1 + '\'' +
                ", key2='" + key2 + '\'' +
                ", key3='" + key3 + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
