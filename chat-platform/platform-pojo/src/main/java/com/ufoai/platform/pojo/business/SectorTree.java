package com.ufoai.platform.pojo.business;

import java.util.List;

public class SectorTree {
    private Long id;

    private String name;

    private Long parentId;

    private Integer isNode;

    private String level;

    private String remark;

    private List<SectorTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsNode() {
        return isNode;
    }

    public void setIsNode(Integer isNode) {
        this.isNode = isNode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SectorTree> getChildren() {
        return children;
    }

    public void setChildren(List<SectorTree> children) {
        this.children = children;
    }
}
