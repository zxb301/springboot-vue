package com.ufoai.platform.pojo.base;

public class CategoryRes {


    /**
     * 主键
     */

    private Long id;

    /**
     * 分类名称
     */

    private String name;

    /**
     * 分类编号
     */
    private String code;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 上级分类ID
     */

    private Long parentId;

    /**
     * 分类索引（key）
     */
    private String indexs;

    /**
     * 是否是叶子节点1：是，0：否
     */

    private String isLeaf;

    /**
     * 创建人
     */
    private Long creater;

    /**
     * 新增时间
     */

    private String createTime;

    /**
     * 修改人
     */
    private Long updater;

    /**
     * 修改时间
     */

    private String updateTime;

    /**
     * 删除人
     */
    private Long deleter;

    /**
     * 删除时间
     */

    private String deleteTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除（1是；0否）
     */

    private Integer isDelete;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIndexs() {
        return indexs;
    }

    public void setIndexs(String indexs) {
        this.indexs = indexs;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
