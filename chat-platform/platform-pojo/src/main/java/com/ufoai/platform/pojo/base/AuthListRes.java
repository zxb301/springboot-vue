package com.ufoai.platform.pojo.base;

import java.util.List;

public class AuthListRes {

//菜单
private Long menuId;
//父id
private Long parentId;
//权限名字
private String name;
//url
private String url;
//权限授权
private  String perms;
//类型   0：目录   1：菜单   2：按钮
private  int type;
//菜单图标
private   String  icom;
//排序
private   int  orderNum;
//是否是叶子节点 0:不是 1:是
private   int  isNode;
//是否显示0显示，1不显示
private  int isShow;
//菜单等级
private  int level;
//备注
private  String remark;
//创建人
private  Long  creater;
//修改人
private  Long   updater;
//创建时间
private  String  createTime;
//修改时间
private  String  updateTime;
//存在状态0存在1删除
private  String  isDelete;

//子权限
private List<AuthListRes> childList;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcom() {
        return icom;
    }

    public void setIcom(String icom) {
        this.icom = icom;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getIsNode() {
        return isNode;
    }

    public void setIsNode(int isNode) {
        this.isNode = isNode;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public List<AuthListRes> getChildList() {
        return childList;
    }

    public void setChildList(List<AuthListRes> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "AuthListRes{" +
                "menuId=" + menuId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", perms='" + perms + '\'' +
                ", type=" + type +
                ", icom='" + icom + '\'' +
                ", orderNum=" + orderNum +
                ", isNode=" + isNode +
                ", isShow=" + isShow +
                ", level=" + level +
                ", remark='" + remark + '\'' +
                ", creater=" + creater +
                ", updater=" + updater +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isDelete='" + isDelete + '\'' +
                '}';
    }
}
