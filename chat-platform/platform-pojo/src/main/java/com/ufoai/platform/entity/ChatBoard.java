package com.ufoai.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 聊天列表记录
 * </p>
 *
 * @author zxb
 * @since 2019-04-29
 */
@TableName("t_chat_board")
public class ChatBoard extends Model<ChatBoard> {

    private static final long serialVersionUID = 1L;

    /**
     * 根据聊天类型：群组ID，个人ID，案件ID
     */
    private String chatId;

    /**
     * 消息接收者ID
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
     * 聊天类型：group|one||case
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
     * 未读消息数
     */
    private Integer unReadCount;

    /**
     * 聊天记录是否存在：0-在，1-删除
     */
    private Integer status;

    /**
     * 最新消息时间
     */
    private LocalDateTime time;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
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
    public Integer getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
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
        return this.chatId;
    }

    @Override
    public String toString() {
        return "ChatBoard{" +
        "chatId=" + chatId +
        ", fromId=" + fromId +
        ", toId=" + toId +
        ", groupId=" + groupId +
        ", chatType=" + chatType +
        ", msgType=" + msgType +
        ", content=" + content +
        ", unReadCount=" + unReadCount +
        ", status=" + status +
        ", time=" + time +
        "}";
    }
}
