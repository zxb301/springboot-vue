package com.ufoai.platform.pojo.base;

import java.util.List;

public class AuthTree {

    private String label;//权限名字

    private List<AuthTree> children;

    private  Long id;

    private Integer isNode;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<AuthTree> getChildren() {
        return children;
    }

    public void setChildren(List<AuthTree> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsNode() {
        return isNode;
    }

    public void setIsNode(Integer isNode) {
        this.isNode = isNode;
    }
}
