package com.ufoai.platform.mapper.base;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.pojo.base.AuthListRes;
import com.ufoai.platform.pojo.base.UserListRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2018-08-16
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    List<String> selectAllPermissions();

    List<String> selectPermissionsByUser(Long userId);

    int deleteUserById(Map map);

    List<Long> queryUserAuth(Long userId);

    List<Long> queryUserAuthOpen(Long userId);

    Integer deleteUserAuth(Long id);

    Integer addUserAuth(@Param("id") Long id, @Param("ids") Long[] auth);

    List<UserListRes> selectUserList(Page<UserListRes> page, @Param("map") Map map);

    List<String> selectUserAuthName(Long userId);

    /**
     * 根据用户id查询出对应的角色名字
     * @param userId
     * @return
     */
    String findRoleNameByUserId(Long userId);

    /**
     * 新增一个用户
     * @return
     */
    int addUser(UserListRes userListRes);

    /**
     * 修改一个用户
     * @param userListRes
     * @return
     */
    int updateUsers(UserListRes userListRes);

    /**
     * 根据id查询出对应的用户信息
     * @param id
     * @return
     */
    UserListRes findUserById(Long id);

    /**
     * 查询用户对应的权限
     * @param map
     * @return
     */
    List<AuthListRes>  findUserAllAuth(Map map);
}
