package com.ufoai.platform.pojo.base;

import javax.validation.constraints.NotBlank;

public class UserInfoRes {

    private Long  id;

    /**
     * 姓名
     */
    @NotBlank(message = "userName 不能为空")
    private String userName;

    /**
     * 登录名
     */
    @NotBlank(message = "code 不能为空")
    private String code;

    /**
     * 0：超级管理员 1：管理员 2：一般用户
     */

    private Integer type;


    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后登录时间
     */
    private String lastLoginTime;


    /**
     * 0:禁用 1:启用
     */
    private Integer status;


    /**
     * 新增时间
     */
    private String createTime;


    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人id
     */
    private String creater;

    /**
     * 修改人id
     */
    private String updater;

    /**
     * 修改时间
     */
    private String updateTime;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
