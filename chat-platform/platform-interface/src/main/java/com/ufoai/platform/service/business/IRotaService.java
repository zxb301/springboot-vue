package com.ufoai.platform.service.business;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ufoai.platform.entity.Rota;
import com.ufoai.platform.pojo.business.RotaBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxb
 * @since 2019-04-24
 */
public interface IRotaService extends IService<Rota> {

    List<RotaBean> rotaList(String date);
}
