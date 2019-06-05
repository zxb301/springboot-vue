package com.ufoai.platform.pojo.base;

public class UserRoleRes {
    /**
     * 主键id
     */
   private Long  id;

    /**
     * 用户id
     */
    private Long  userId;
    /**
     * 角色Id
     */
    private  Long  roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleRes{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
