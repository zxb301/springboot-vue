package com.ufoai.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 聊天消息表
 * </p>
 *
 * @author zxb
 * @since 2019-04-29
 */
@TableName("t_chat_message")
public class ChatMessage extends Model<ChatMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 发送人ID
     */
    private String fromId;

    /**
     * 接收人ID
     */
    private String toId;

    /**
     * 群组ID
     */
    private String groupId;

    /**
     * 聊天类型：group|one||case 群组|个人||案件（案件聊天则以案件为主，新建表）
     */
    private String chatType;

    /**
     * 消息类型：text|picture|video|file
     */
    private String msgType;

    /**
     * 对应类型的消息内容
     */
    private String content;

    /**
     * 消息阅读状态：0-未读，1-已读
     */
    private Integer status;

    /**
     * 消息时间
     */
    private LocalDateTime time;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }
    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    protected Serializable pkVal() {
        return this.msgId;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
        "msgId=" + msgId +
        ", fromId=" + fromId +
        ", toId=" + toId +
        ", groupId=" + groupId +
        ", chatType=" + chatType +
        ", msgType=" + msgType +
        ", content=" + content +
        ", status=" + status +
        ", time=" + time +
        "}";
    }
}
