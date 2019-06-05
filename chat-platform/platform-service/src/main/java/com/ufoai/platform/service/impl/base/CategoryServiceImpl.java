package com.ufoai.platform.service.impl.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ufoai.platform.entity.Category;
import com.ufoai.platform.mapper.base.CategoryMapper;
import com.ufoai.platform.pojo.base.CategoryTree;
import com.ufoai.platform.pojo.base.ERMCategory;
import com.ufoai.platform.service.base.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryTree> categoryTree(String categoryType) {
        List<Category> listCategory;
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("is_delete ",0);
        listCategory = categoryMapper.selectList(wrapper);
        List<CategoryTree> list = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < listCategory.size(); i++) {
            // 一级菜单没有parentId
            if (listCategory.get(i).getParentId().longValue() == 0 ) {
                CategoryTree tree = new CategoryTree();
                tree.setId(listCategory.get(i).getId());
                tree.setLabel(listCategory.get(i).getName());
                tree.setIsNode(listCategory.get(i).getIsLeaf());
                tree.setCode(listCategory.get(i).getCode());
                tree.setValue(listCategory.get(i).getCode());

                list.add(tree);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (CategoryTree category : list) {
            category.setChildren(getChild(category.getId(), listCategory));
        }
        return list;
    }

    @Override
    public String getCode(Integer level, Long parentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("level", level);
        map.put("parentId", parentId);
        String code = "1001";
        Category category = categoryMapper.getCode(map);
        if (category != null) {
            if(parentId==null){
                return (Long.parseLong(category.getCode())+1)+"";
            }else{
                String fullCode = category.getCode();	//完整code
                String lastCode = fullCode.substring(fullCode.length() - 4);	//截取后四位。
                String nextCode = Long.parseLong(lastCode) + 1 + "";			//生成下一个code   未考虑超过9999时的进位问题，但实际情况为分类，不太可能到达此数字。
                return fullCode.substring(0, fullCode.length() - 4) + nextCode;	//重新拼接code并返回。
            }
        }else{
            if(!StringUtils.isEmpty(parentId+"")){
                Category pc = categoryMapper.selectById(parentId);
                code = pc.getCode()+"1001";
            }else{
                code = "1001";
            }
        }
        return code;
    }

    @Override
    public long selectByName(String name, Long parentId) {
        Map<String, Object> m = new HashMap<>();
        m.put("name", name);
        if (null==parentId) {
            m.put("parentId", null);
        } else {
            m.put("parentId", parentId);
        }
        return categoryMapper.selectDictionaryByName(m);
    }

    @Override
    public int checkName(Long pId, String name,Long id) {
        if (pId == null) {
            QueryWrapper<Category> ew= new QueryWrapper<>();
            ew.eq("name={0}", name).eq("is_delete", 0).eq("parent_id",0);
            if(id!=null){
                ew.ne("id",id);
            }
            List<Category> userList = categoryMapper.selectList(ew);
            if (userList.size() >= 1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            QueryWrapper<Category> ew= new QueryWrapper<>();
            ew.eq("name={0}", name).
                    eq("is_delete", 0).eq("parent_id",pId);
            if(id!=null){
                ew.ne("id", id);
            }
            List<Category> userList = categoryMapper.selectList(ew);
            if (userList.size() >= 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }


    /**
     * 递归查找子菜单
     *
     * @param rootId           当前菜单id
     * @param rootCategory 要查找的列表
     * @return
     */
    private List<CategoryTree> getChild(Long rootId, List<Category> rootCategory) {
        // 子菜单
        List<CategoryTree> childList = new ArrayList<>();
        for (Category category : rootCategory) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (category.getParentId()!=0 ) {
                if (category.getParentId().longValue() == rootId.longValue()) {
                    CategoryTree categoryTree = new CategoryTree();
                    categoryTree.setId(category.getId());
                    categoryTree.setLabel(category.getName());
                    categoryTree.setIsNode(category.getIsLeaf());
                    categoryTree.setCode(category.getCode());
                    categoryTree.setValue(category.getCode());
                    categoryTree.setPid(category.getParentId());
                    childList.add(categoryTree);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (CategoryTree categoryTree : childList) {// 没有url子菜单还有子菜单
            if (categoryTree.getIsNode().equals("0")) {
                // 递归
                categoryTree.setChildren(getChild(categoryTree.getId(), rootCategory));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }


    @Override
    public List<CategoryTree> categoryType(String categoryType) {
        List<Category> listCategory;
        listCategory = categoryMapper.selectCategoryList(categoryType);
        List<CategoryTree> list = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < listCategory.size(); i++) {
            // 一级菜单没有parentId
            if (listCategory.get(i).getLevel()==2) {
                CategoryTree tree = new CategoryTree();
                tree.setId(listCategory.get(i).getId());
                tree.setIsNode(listCategory.get(i).getIsLeaf());
                tree.setLabel(listCategory.get(i).getName());
                tree.setCode(listCategory.get(i).getCode());
                tree.setValue(listCategory.get(i).getCode());
                list.add(tree);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (CategoryTree category : list) {
            category.setChildren(getChild(category.getId(), listCategory));
        }
        return list;
    }

    @Override
    public List<ERMCategory> selectERMcodeList(String code) {
        return categoryMapper.selectERMcodeList(code);
    }
}
