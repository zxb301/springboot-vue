package com.ufoai.platform.mapper.base;

import com.ufoai.platform.entity.Sector;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ufoai.platform.pojo.base.UserInfoRes;
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
public interface SectorMapper extends BaseMapper<Sector> {

    List<UserInfoRes> getSectorUser(@Param("id") Long id);
}
