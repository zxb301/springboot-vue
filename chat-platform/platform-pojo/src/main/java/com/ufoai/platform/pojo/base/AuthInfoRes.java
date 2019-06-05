package com.ufoai.platform.pojo.base;

import java.util.List;

/**
 * 用户权限设置pojo
 */
public class AuthInfoRes {

    private List<AuthTree>  trees;

    private List<Long> expanded_keys;

    private List<Long> checked_keys;

    /**
     * 修改的时候角色名字
     */
    private String   roleName;

    public List<AuthTree> getTrees() {
        return trees;
    }

    public void setTrees(List<AuthTree> trees) {
        this.trees = trees;
    }

    public List<Long> getExpanded_keys() {
        return expanded_keys;
    }

    public void setExpanded_keys(List<Long> expanded_keys) {
        this.expanded_keys = expanded_keys;
    }

    public List<Long> getChecked_keys() {
        return checked_keys;
    }

    public void setChecked_keys(List<Long> checked_keys) {
        this.checked_keys = checked_keys;
    }

    public String getRoleName() { return roleName; }

    public void setRoleName(String roleName) {  this.roleName = roleName; }
}
