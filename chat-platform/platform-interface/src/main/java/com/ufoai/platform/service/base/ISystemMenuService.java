package com.ufoai.platform.service.base;



import com.baomidou.mybatisplus.extension.service.IService;
import com.ufoai.platform.entity.SystemMenu;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.pojo.base.MenuTree;

import java.util.List;


/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface ISystemMenuService extends IService<SystemMenu> {

    List<MenuTree> selectMenuByUser(SystemUser user);

}
