package com.ufoai.platform.mapper.business;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ufoai.platform.entity.Rota;
import com.ufoai.platform.pojo.business.RotaBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2019-04-24
 */
public interface RotaMapper extends BaseMapper<Rota> {

    List<RotaBean> rotaList(@Param("date") String date);
}
