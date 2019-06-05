package com.ufoai.platform.pojo.base;

public class UserListRes {


    /**
     * 主键
     */
    private Long id;

    private Long roleId;

    /**
     * 姓名
     */

    private String userName;

    /**
     * 登录名
     */
    private String code;

    /**
     * 0：超级管理员 1：管理员 2：一般用户
     */
    private Long type;

    /**
     * 性别
     */
    private String sex;
    /**
     * 所在的单位（部门）
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

    private String auth;

    private  String roleName;
    /**
     * 创建人
     */
    private String creater;

    /**
     *密码
     */
    private String password;
    /**
     * 修改人
     */
    private String updater;
    /**
     * 修改时间
     */
    private String updateTime;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSector() {
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

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserListRes{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", sex='" + sex + '\'' +
                ", sector='" + sector + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", remark='" + remark + '\'' +
                ", auth='" + auth + '\'' +
                ", roleName='" + roleName + '\'' +
                ", creater='" + creater + '\'' +
                ", password='" + password + '\'' +
                ", updater='" + updater + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
