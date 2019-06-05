package com.ufoai.platform.service.impl.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.RoleMenu;
import com.ufoai.platform.mapper.base.RoleMenuMapper;
import com.ufoai.platform.pojo.base.RoleMenuRes;
import com.ufoai.platform.service.base.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    /**
     *注入mapper接口
     */
     @Autowired
    RoleMenuMapper roleMenuMapper;

    /**
     * 新增角色菜单的Impl层
     * @param
     * @return
     */
    @Override
    public int addRoleMenu(RoleMenuRes roleMenuRes) {
        return roleMenuMapper.addRoleMenu(roleMenuRes);
    }

    /**
     * 根据roleId删除对应的所有菜单
     * @param
     * @return
     */
    @Override
    public int delMenuByRoleId(Long roleId) {
        return roleMenuMapper.delMenuByRoleId(roleId);
    }

    /**
     * 根据roleId查询出所有的menuId
     * @param roleId
     * @return
     */
    @Override
    public String findMenuIdByRoleId(Long roleId) {
        return roleMenuMapper.findMenuIdByRoleId(roleId);
    }


}
