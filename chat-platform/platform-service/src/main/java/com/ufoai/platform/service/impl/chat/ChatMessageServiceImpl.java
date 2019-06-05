package com.ufoai.platform.service.impl.chat;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.ChatMessage;

import com.ufoai.platform.mapper.chat.ChatMessageMapper;
import com.ufoai.platform.service.chat.IChatMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxb
 * @since 2019-04-24
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements IChatMessageService {

}
