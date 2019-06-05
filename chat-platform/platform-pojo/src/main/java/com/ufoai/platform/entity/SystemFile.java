package com.ufoai.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author zxb
 * @since 2019-04-29
 */
@TableName("t_system_file")
public class SystemFile extends Model<SystemFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件分类（备用）
     */
    private String code;

    /**
     * 文件类型
     */
    private Integer type;

    /**
     * URL地址
     */
    private String url;

    /**
     * 创建时间
     */
    private String createTime;

    private String creater;

    /**
     * 主表Id
     */
    private Long mainId;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 所属模块
     */
    private String moduleType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SystemFile{" +
        "id=" + id +
        ", code=" + code +
        ", type=" + type +
        ", url=" + url +
        ", createTime=" + createTime +
        ", creater=" + creater +
        ", mainId=" + mainId +
        ", name=" + name +
        ", moduleType=" + moduleType +
        "}";
    }
}
