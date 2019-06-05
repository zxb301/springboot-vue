package com.ufoai.platform.service.impl.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.Category;
import com.ufoai.platform.entity.SystemMenu;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.mapper.base.SystemMenuMapper;
import com.ufoai.platform.pojo.base.MenuTree;
import com.ufoai.platform.service.base.ISystemMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements ISystemMenuService {

    @Autowired
    SystemMenuMapper menuMapper;


    @Override
    public List<MenuTree> selectMenuByUser(SystemUser user) {
        List<SystemMenu> listMenuTree ;
        if (user == null) {
            QueryWrapper<SystemMenu> wrapper= new QueryWrapper<>();
            wrapper.eq("type={0}", "0").or().eq("type","1");
            wrapper.eq("is_delete",0).orderByAsc("order_num");
            listMenuTree = menuMapper.selectList(wrapper);
        } else {
            listMenuTree = menuMapper.selectMenuListByUser(user);
           /* Set<SystemMenu> parentSystemMenu=new HashSet<>();
            for (Iterator<SystemMenu> iterator = listMenuTree.iterator(); iterator.hasNext(); ) {
                SystemMenu menu = iterator.next();
                if(menu.getParentId()!=0){
                    SystemMenu pmenu= new SystemMenu();
                    pmenu.setMenuId(menu.getParentId());
                    parentSystemMenu.add(menuMapper.selectOne(pmenu)) ;
                }
            }
            if(parentSystemMenu.size()>0){
                listMenuTree.addAll(parentSystemMenu);
            }*/

        }

        List<MenuTree> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < listMenuTree.size(); i++) {
            // 一级菜单没有parentId
            if (listMenuTree.get(i).getParentId() == 0) {
                MenuTree menuTree = new MenuTree();
                menuTree.setId(listMenuTree.get(i).getMenuId());
                menuTree.setLabel(listMenuTree.get(i).getName());
                menuTree.setUrl(listMenuTree.get(i).getUrl());
                menuTree.setIcon(listMenuTree.get(i).getIcon());
                menuList.add(menuTree);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (MenuTree menu : menuList) {
            menu.setChildren(getChild(menu.getId(), listMenuTree));
        }
        return menuList;
    }

    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
    private List<MenuTree> getChild(Long id, List<SystemMenu> rootMenu) {
        // 子菜单
        List<MenuTree> childList = new ArrayList<>();
        for (SystemMenu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId() != 0) {
                if (menu.getParentId().equals(id) ) {
                    MenuTree menuTree = new MenuTree();
                    menuTree.setId(menu.getMenuId());
                    menuTree.setLabel(menu.getName());
                    menuTree.setUrl(menu.getUrl());
                    menuTree.setIcon(menu.getIcon());
                    childList.add(menuTree);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuTree menu : childList) {// 没有url子菜单还有子菜单
            if (StringUtils.isBlank(menu.getUrl())) {
                // 递归
                menu.setChildren(getChild(menu.getId(), rootMenu));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
