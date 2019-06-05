package com.ufoai.platform.mapper.base;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ufoai.platform.entity.UserRole;
import com.ufoai.platform.pojo.base.UserRoleRes;


/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 新增用户角色
     * @param userRoleRes
     * @return
     */
    int addUserRole(UserRoleRes userRoleRes);

    /**
     * 根据角色Id查询出该叫色有没有被用户使用
     * @param roleId
     * @return
     */
    int  findByRoleId(Long roleId);


}
