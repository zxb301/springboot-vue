package com.ufoai.platform.service.impl.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.SystemFile;
import com.ufoai.platform.mapper.base.SystemFileMapper;
import com.ufoai.platform.service.base.ISystemFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件上传 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-09-10
 */
@Service
public class SystemFileServiceImpl extends ServiceImpl<SystemFileMapper, SystemFile> implements ISystemFileService {

}
