package com.ufoai.platform.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ufoai.platform.entity.Category;
import com.ufoai.platform.pojo.base.ERMCategory;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
public interface CategoryMapper extends BaseMapper<Category> {

    Category getCode(Map<String, Object> map);

    long selectDictionaryByName(Map<String, Object> m);

    List<Category> selectCategoryList(@Param("categoryType") String categoryType);

    List<ERMCategory> selectERMcodeList(@Param("code") String code);
}
