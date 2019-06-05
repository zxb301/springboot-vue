package com.ufoai.platform.service.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.pojo.base.AuthListRes;
import com.ufoai.platform.pojo.base.AuthTree;
import com.ufoai.platform.pojo.base.UserAuthReq;
import com.ufoai.platform.pojo.base.UserListRes;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author zxb
 * @since 2018-08-08
 */
public interface ISystemUserService extends IService<SystemUser> {
    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    Page<UserListRes> selectUserList(Map map);

    /**
     * 用户查询
     *
     * @param id
     * @return
     */
    SystemUser findByUserId(Long id);

    /**
     * 所有权限查询
     *
     * @return
     */


    Set<String> selectPermissionsByUser(SystemUser user);

    int insertUser(SystemUser user, UserAuthReq authReq);

    int updateUserById(SystemUser user, UserAuthReq authReq);

    int deleteUser(Map map);

    /**
     * 权限设置相关查询
     *
     * @return
     */
    List<AuthTree> queryAuthTree();

    List<Long> queryUserAuth(Long userId);

    List<Long> queryUserAuthOpen(Long userId);

    boolean editUserAuth(UserAuthReq authReq);

    int resetPassword(Map map);

    int editPassword(Map map);

    int checkCode(Long userId, String code);


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
