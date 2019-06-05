package com.ufoai.platform.pojo.base;

import java.util.List;

public class CategoryTree {

    private String label;

    private List<CategoryTree> children ;

    private  Long id;

    private String isNode;

    private String code;

    private String  value;

    private  Long pid;

    private Boolean isEdit=false;

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        isEdit = isEdit;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CategoryTree> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTree> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsNode() {
        return isNode;
    }

    public void setIsNode(String isNode) {
        this.isNode = isNode;
    }
}
