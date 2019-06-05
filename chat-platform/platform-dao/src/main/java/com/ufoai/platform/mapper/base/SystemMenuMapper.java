package com.ufoai.platform.mapper.base;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ufoai.platform.entity.SystemMenu;
import com.ufoai.platform.entity.SystemUser;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    List<SystemMenu> selectMenuListByUser(SystemUser user);
}
