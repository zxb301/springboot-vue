package com.ufoai.platform.pojo.base;



public class RoleListRes {
    /**
     * 主键
     */
    private Long  roleId;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 角色标识
     */
   private String  roleSign;
    /**
     *1禁用，0启用
     */
    private String  status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建用户id
     */
    private Long creater;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 修改用户id
     */
    private Long updater;

    /**
     * 0存在，1删除
     */
    private String isDelete;

    /**
     * 角色菜单id的字符串
     */
     private Long[] auth;

    /**
     * 因为前端不讲理，开始没告诉我，传字符串该传数组，操作如下
     */
     private String auths;





    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Long[] getAuth() {
        return auth;
    }

    public void setAuth(Long[] auth) {
        this.auth = auth;
    }

    public String getAuths() {
        return auths;
    }

    public void setAuths(String auths) {
        this.auths = auths;
    }




    @Override
    public String toString() {
        return "RoleListRes{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleSign='" + roleSign + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", creater=" + creater +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updater=" + updater +
                ", isDelete='" + isDelete + '\'' +
               // ", auth=" + Arrays.toString(auth) +
                ", auths='" + auths + '\'' +
                '}';
    }
}
