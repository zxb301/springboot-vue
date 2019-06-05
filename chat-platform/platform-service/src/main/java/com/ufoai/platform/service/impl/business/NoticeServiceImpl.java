package com.ufoai.platform.service.impl.business;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.Notice;

import com.ufoai.platform.mapper.business.NoticeMapper;
import com.ufoai.platform.service.business.INoticeService;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
