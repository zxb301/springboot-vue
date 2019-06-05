package com.ufoai.platform.service.base;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ufoai.platform.entity.SystemRole;
import com.ufoai.platform.pojo.base.RoleListRes;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface ISystemRoleService extends IService<SystemRole> {


    Set<String> findAllRoleName();

    Set<String> findRoleNameByUserId(Long userId);

    /**
     * 分页查询角色列表
     * @param map
     * @return
     */
    //Page<RoleListRes> selectRoleList(Map map);

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
    String findUserNameByRoleId(Long roleId);

    /**
     * 增加和修改时候对roleName做唯一校验
     * @param roleId
     * @param RoleName
     * @return
     */
   int  checkRoleName(Long roleId, String RoleName);


    /**
     * 查询出所有的角色返回给前端，用于在用户添加的时候，出现角色下拉框
     * @return
     */
    List<RoleListRes> selectRoleList();

    /**
     * 查询所有角色信息
     * @param map
     * @return
     */
    Page<RoleListRes> findAllRole(Map map);

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

    /**
     * 传进来一个数组，你便利他，我最终要一个以逗号分隔开的字符传
     * @param a
     * @return
     */
   String  getStrings(String a, Long[] b);
}
