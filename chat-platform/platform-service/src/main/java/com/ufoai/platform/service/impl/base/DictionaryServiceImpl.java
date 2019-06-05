package com.ufoai.platform.service.impl.base;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.Dictionary;
import com.ufoai.platform.mapper.base.DictionaryMapper;
import com.ufoai.platform.service.base.IDictionaryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

}
