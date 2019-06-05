package com.ufoai.platform.service.impl.chat;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.ChatBoard;

import com.ufoai.platform.mapper.chat.ChatBoardMapper;
import com.ufoai.platform.service.chat.IChatBoardService;
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
public class ChatBoardServiceImpl extends ServiceImpl<ChatBoardMapper, ChatBoard> implements IChatBoardService {

}
