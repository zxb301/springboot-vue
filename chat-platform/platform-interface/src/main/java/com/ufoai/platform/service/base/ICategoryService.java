package com.ufoai.platform.service.base;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ufoai.platform.entity.Category;
import com.ufoai.platform.pojo.base.CategoryTree;
import com.ufoai.platform.pojo.base.ERMCategory;
import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface ICategoryService extends IService<Category> {

    List<CategoryTree> categoryTree(String categoryType);

    String getCode(Integer level, Long parentId);

    long selectByName(String name, Long parentId);

    int checkName(Long pId, String name, Long id);

    List<CategoryTree> categoryType(String categoryType);

    List<ERMCategory> selectERMcodeList(String code);
}
