package com.ufoai.platform.pojo.base;

public class RoleMenuRes {

/**
 * 主键id
 */
private Long  id;

/**
 * 角色id
 */
private Long RoleId;
/**
 * 菜单id
 */
private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuRes{" +
                "id=" + id +
                ", RoleId=" + RoleId +
                ", menuId=" + menuId +
                '}';
    }
}
