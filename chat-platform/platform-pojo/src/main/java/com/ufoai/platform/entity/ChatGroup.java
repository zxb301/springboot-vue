package com.ufoai.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 群组表
 * </p>
 *
 * @author zxb
 * @since 2019-04-29
 */
@TableName("t_chat_group")
public class ChatGroup extends Model<ChatGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 群组ID
     */
    private String groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 群组头像
     */
    private String groupIcon;

    /**
     * 群组用户ID列表{USER_ID}，上限1000人
     */
    private String userId;

    /**
     * 群组管理员ID列表{ADMIN_ID}，上限25人
     */
    private String adminId;

    /**
     * 创建者ID
     */
    private String createId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 备注
     */
    private String remark;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.groupId;
    }

    @Override
    public String toString() {
        return "ChatGroup{" +
        "groupId=" + groupId +
        ", groupName=" + groupName +
        ", groupIcon=" + groupIcon +
        ", userId=" + userId +
        ", adminId=" + adminId +
        ", createId=" + createId +
        ", createTime=" + createTime +
        ", remark=" + remark +
        "}";
    }
}
