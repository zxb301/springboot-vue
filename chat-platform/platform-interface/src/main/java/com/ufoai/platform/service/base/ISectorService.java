package com.ufoai.platform.service.base;

import com.ufoai.platform.entity.Sector;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ufoai.platform.pojo.base.UserInfoRes;
import com.ufoai.platform.pojo.business.SectorTree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxb
 * @since 2019-04-24
 */
public interface ISectorService extends IService<Sector> {

    List<SectorTree> getSectorTree();

    List<UserInfoRes> getSectorUser(Long id);
}
