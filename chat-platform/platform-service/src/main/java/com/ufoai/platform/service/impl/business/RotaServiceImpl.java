package com.ufoai.platform.service.impl.business;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.Rota;
import com.ufoai.platform.mapper.business.RotaMapper;
import com.ufoai.platform.pojo.business.RotaBean;
import com.ufoai.platform.service.business.IRotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxb
 * @since 2019-04-24
 */
@Service
public class RotaServiceImpl extends ServiceImpl<RotaMapper, Rota> implements IRotaService {

    @Autowired
    private RotaMapper rotaMapper;

    @Override
    public List<RotaBean> rotaList(String date) {
        return rotaMapper.rotaList(date);
    }
}
