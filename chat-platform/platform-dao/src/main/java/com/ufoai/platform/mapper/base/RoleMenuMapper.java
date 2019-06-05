package com.ufoai.platform.mapper.base;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ufoai.platform.entity.RoleMenu;
import com.ufoai.platform.pojo.base.RoleMenuRes;

/**
 * <p>
 * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    /**
     * 新增角色菜单Mapper接口
     * @param roleMenuRes
     * @return
     */
    int addRoleMenu(RoleMenuRes roleMenuRes);

    /**
     * 根据roleId删除对应的所有菜单
     * @param roleId
     * @return
     */
    int delMenuByRoleId(Long roleId);

    /**
     * 根据roleId查询出所有的mennuId
     * @param roleId
     * @return
     */
    String findMenuIdByRoleId(Long roleId);
}
