package com.ufoai.platform.service.base;



import com.baomidou.mybatisplus.extension.service.IService;
import com.ufoai.platform.entity.UserRole;
import com.ufoai.platform.pojo.base.UserRoleRes;


/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     *   新增用户角色
     * @param userRoleRes
     * @return
     */
    int addUserRole(UserRoleRes userRoleRes);

    /**
     * 根据角色Id查询出该叫色有没有被用户使用
     * @param roleId
     * @return
     */
    int findByRoleId(Long roleId);

}
