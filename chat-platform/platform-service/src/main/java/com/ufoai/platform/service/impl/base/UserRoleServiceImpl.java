package com.ufoai.platform.service.impl.base;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.UserRole;
import com.ufoai.platform.mapper.base.UserRoleMapper;
import com.ufoai.platform.pojo.base.UserRoleRes;
import com.ufoai.platform.service.base.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
  //注入dao层接口
    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * 新增用户角色
     * @param userRoleRes
     * @return
     */
    @Override
    public int addUserRole(UserRoleRes userRoleRes) {
        return userRoleMapper.addUserRole(userRoleRes);
    }

  /**
   * 根据角色Id查询出该叫色有没有被用户使用
   * @param roleId
   * @return
   */
  @Override
  public int findByRoleId(Long roleId) {
    return userRoleMapper.findByRoleId(roleId);
  }
}
