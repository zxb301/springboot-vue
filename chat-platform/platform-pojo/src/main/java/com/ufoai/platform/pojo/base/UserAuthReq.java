package com.ufoai.platform.pojo.base;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserAuthReq {


    private Long  id;
    @NotNull(message = "auth 不能为空")
    private Long[] auth;

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
     * 所属部门（单位）：1是城关派出所；2是西岔河派出所；3是大河埧派出所；4是陈家垻派出所；5是栗子垻派出所
     */
    private String sector;
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
    private Long creater;

    /**
     * 修改人id
     */
    private Long updater;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 删除人id
     */
    private Long deleter;

    /**
     * 删除时间
     */
    private String deleteTime;

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDeleter() {
        return deleter;
    }

    public void setDeleter(Long deleter) {
        this.deleter = deleter;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
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

    public String  getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long[] getAuth() {
        return auth;
    }

    public void setAuth(Long[] auth) {
        this.auth = auth;
    }
}
