package com.ufoai.platform.service.impl.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ufoai.platform.entity.Sector;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.mapper.base.SectorMapper;
import com.ufoai.platform.pojo.base.CategoryTree;
import com.ufoai.platform.pojo.base.UserInfoRes;
import com.ufoai.platform.pojo.business.SectorTree;
import com.ufoai.platform.service.base.ISectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxb
 * @since 2019-04-24
 */
@Service
public class SectorServiceImpl extends ServiceImpl<SectorMapper, Sector> implements ISectorService {

    @Autowired
    private SectorMapper sectorMapper;

    @Override
    public List<SectorTree> getSectorTree() {
        //查询所有部门
        List<Sector> sectors = sectorMapper.selectList(new QueryWrapper<>());

        List<SectorTree> sectorTrees = new ArrayList<>();
        //查询一级
        for (Sector sector : sectors) {
            if(sector.getParentId().longValue()==0){
                SectorTree sectorTree = new SectorTree();
                sectorTree.setId(sector.getId());
                sectorTree.setIsNode(sector.getIsNode());
                sectorTree.setLevel(sector.getLevel());
                sectorTree.setName(sector.getName());
                sectorTree.setParentId(sector.getParentId());
                sectorTree.setRemark(sector.getRemark());
                sectorTrees.add(sectorTree);
            }
        }
        // 为一级设置子级，getChild是递归调用的
        for (SectorTree sectorTree : sectorTrees) {
            sectorTree.setChildren(getChild(sectorTree.getId(), sectors));
        }
        return sectorTrees;
    }

    private List<SectorTree> getChild(Long rootId, List<Sector> sectors) {
        //子菜单
        List<SectorTree> child = new ArrayList<>();
        for (Sector sector : sectors) {
            if(sector.getParentId().longValue()!=0){
                if(rootId.longValue() == sector.getParentId().longValue()){
                    SectorTree sectorTree = new SectorTree();
                    sectorTree.setId(sector.getId());
                    sectorTree.setLevel(sector.getLevel());
                    sectorTree.setIsNode(sector.getIsNode());
                    sectorTree.setName(sector.getName());
                    sectorTree.setParentId(sector.getParentId());
                    sectorTree.setRemark(sector.getRemark());
                    child.add(sectorTree);
                }
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (SectorTree sectorTree : child) {//
            if (sectorTree.getIsNode()==0) {
                // 递归
                sectorTree.setChildren(getChild(sectorTree.getId(), sectors));
            }
        } // 递归退出条件
        if (child.size() == 0) {
            return null;
        }
        return child;
    }

    @Override
    public List<UserInfoRes> getSectorUser(Long id) {
        return sectorMapper.getSectorUser(id);
    }
}
