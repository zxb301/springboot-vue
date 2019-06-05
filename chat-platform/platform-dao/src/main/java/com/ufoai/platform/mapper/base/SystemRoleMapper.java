package com.ufoai.platform.mapper.base;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ufoai.platform.entity.SystemRole;
import com.ufoai.platform.pojo.base.RoleListRes;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    List<String> findAllRoleName();

    List<String> findRoleNameByUserId(Long userId);




    /**
     * 新增角色
     * @param role
     * @return
     */
    int addRole(RoleListRes role);

    /**
     * 修改角色和删除角色
     * @param role
     * @return
     */
    int updateRole(RoleListRes role);

    /**
     * 根据roleName查询有没有存在，新增的时候校验
     * @param roleName
     * @return
     */
    int findByRoleName(String roleName);

    /**
     * 根据roleId查询出roleName
     * @param roleId
     * @return
     */
    String  findUserNameByRoleId(Long roleId);
    /**
     * 查询出所有的角色返回给前端，用于在用户添加的时候，出现角色下拉框
     * @return
     */
    List<RoleListRes> selectRoleList();

    /**
     * 查询所有的角色信息用于展示分页
     * @param page
     * @param map
     * @return
     */
    List<RoleListRes>  findAllRole(Page<RoleListRes> page, Map map);
    /**
     * 查询该叫色拥有的权限
     * @param roleId
     * @return
     */
    List<Long> findRoleAuth(Long roleId);

    /**
     * 查询该角色的权限parentId
     * @param roleId
     * @return
     */
    List<Long>  findRoleAuthOpen(Long roleId);
}
