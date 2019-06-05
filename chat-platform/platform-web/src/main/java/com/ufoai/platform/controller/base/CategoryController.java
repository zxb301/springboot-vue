package com.ufoai.platform.controller.base;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ufoai.platform.annotation.Log;
import com.ufoai.platform.common.utils.ShiroUtils;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.entity.Category;
import com.ufoai.platform.pojo.base.CategoryRes;
import com.ufoai.platform.pojo.base.CategoryTree;
import com.ufoai.platform.pojo.base.ERMCategory;
import com.ufoai.platform.pojo.base.ResultBean;
import com.ufoai.platform.service.base.ICategoryService;
import com.ufoai.platform.utils.BeanUtils;
import com.ufoai.platform.utils.DateTimeUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2018-08-14
 */
@Controller
@RequestMapping("/notes/category")
public class CategoryController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICategoryService categoryService;


    /**
     * 分类树查询
     *
     * @return ResultBean
     */
    @ResponseBody
    @GetMapping("/categoryTree")
    public ResultBean getCategoryTree(String categoryType) {
        ResultBean resultBean = new ResultBean();
        try {
            //查询分类树
            List<CategoryTree> trees = categoryService.categoryTree(categoryType);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setInfo(trees);
            resultBean.setSuccess(true);
        } catch (Exception e) {
            logger.error("CategoryController categoryTree Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }
    /**
     * 分类树查询
     *
     * @return ResultBean
     */
    @ResponseBody
    @GetMapping("/categoryType")
    public ResultBean getCategoryByType(String categoryType) {
        ResultBean resultBean = new ResultBean();
        try {
            //查询分类树
            List<CategoryTree> trees = categoryService.categoryType(categoryType);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setInfo(trees);
            resultBean.setSuccess(true);
        } catch (Exception e) {
            logger.error("CategoryController categoryTree Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 分类树查询
     *
     * @return ResultBean
     */
    @ResponseBody
    @GetMapping("/ERMcode")
    public ResultBean getERMcode(String code) {
        ResultBean resultBean = new ResultBean();
        try {
            //查询分类
            List<ERMCategory> list = categoryService.selectERMcodeList(code);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setInfo(list);
            resultBean.setSuccess(true);
        } catch (Exception e) {
            logger.error("CategoryController categoryTree Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }


    /**
     * 根据code查询分类名称
     *
     * @return ResultBean
     */
    @ResponseBody
    @GetMapping("/categoryName")
    public ResultBean getCategoryName(String code) {
        ResultBean resultBean = new ResultBean();
        try {
            //查询分类树
            Category person = new Category();
            person.setCode(code);
            QueryWrapper pw = new QueryWrapper(person);
            Category cat = categoryService.getOne(pw);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setInfo(cat.getName());
            resultBean.setSuccess(true);
        } catch (Exception e) {
            logger.error("CategoryController getCategoryName Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    //新增
    @Log("新增分类")
    @RequiresPermissions("category:add")
    @ResponseBody
    @PostMapping("/categoryTree")
    public ResultBean insert(@RequestBody @Validated Category category) {
        ResultBean resultBean = new ResultBean();
        try {
            //去重
            long param = categoryService.selectByName(category.getName(), category.getParentId());
            if (param > 0) {
                resultBean.setMessage("分类名称[" + category.getName() + "]已经存在！");
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_FAILED);
                return resultBean;
            }
            //根分类
            if (category.getParentId() == null) {
                category.setIsLeaf("1");
                category.setLevel(1);
                String code = categoryService.getCode(category.getLevel(), null);
                category.setCode(code);
            } else {
                //子分类
                Category pCategory = categoryService.getById(category.getParentId());
                category.setParentId(category.getParentId());
                Integer level = pCategory.getLevel() + 1;
                category.setLevel(level);
                String code = categoryService.getCode(category.getLevel(), category.getParentId());
                category.setCode(code);

                //将父节点设置为非叶子节点
                pCategory.setIsLeaf("0");
                categoryService.updateById(pCategory);
            }
            category.setCreater(ShiroUtils.getUserId());
            category.setCreateTime(DateTimeUtil.getToday());
            boolean a = categoryService.save(category);
            if (a) {
                resultBean.setMessage(ErrorMatrix.DB_SAVE_SUCCESS);
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setInfo(category);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_SAVE_FAIL);
            }
        } catch (Exception e) {
            logger.info("CategoryController categoryTree Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    @Log("删除分类")
    @RequiresPermissions("category:del")
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResultBean deleteCategory(@PathVariable("id") Long id) {
        ResultBean resultBean = new ResultBean();
        try {
            boolean a = categoryService.removeById(id);
            if (a) {
                resultBean.setMessage(ErrorMatrix.DB_DEL_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setSuccess(true);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_DEL_FAIL);
            }
        } catch (Exception e) {
            logger.error("CategoryController deleteCategory Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    @Log("修改分类")
    @RequiresPermissions("category:edit")
    @ResponseBody
    @PutMapping("/categoryTree")
    public ResultBean edit(@RequestBody @Validated CategoryRes categoryRes) {
        ResultBean resultBean = new ResultBean();
        try {
            int a = 0;
            Category c = categoryService.getById(categoryRes.getId());
            if (c.getParentId() == 0) {
                a = categoryService.checkName(null, categoryRes.getName(), categoryRes.getId());
            } else {
                a = categoryService.checkName(c.getParentId(), categoryRes.getName(), categoryRes.getId());
            }
            if (a != 0) {
                resultBean.setMessage("分类名称[" + categoryRes.getName() + "]已经存在！");
                resultBean.setSuccess(true);
                resultBean.setCode(ErrorMatrix.CODE_FAILED);
                return resultBean;
            }
            Category category = new Category();
            BeanUtils.copyProperties(categoryRes, category);
            category.setUpdater(ShiroUtils.getUserId());
            category.setUpdateTime(DateTimeUtil.getToday());
            boolean b = categoryService.updateById(category);
            if (b) {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
                resultBean.setCode(ErrorMatrix.CODE_OK);
                resultBean.setSuccess(true);
                resultBean.setInfo(category);
            } else {
                resultBean.setMessage(ErrorMatrix.DB_UPDATE_FAIL);
            }
        } catch (Exception e) {
            logger.error("CategoryController edit Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    @ResponseBody
    @GetMapping("/checkCateName")
    public boolean checkCateName(Long pId, String name, Long id) {
        try {
            int a;
            if (pId == null) {
                a = categoryService.checkName(null, name, id);
            } else {
                a = categoryService.checkName(pId, name, id);
            }
            if (a == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("CategoryController checkCateName Exception: " + e.getMessage());
        }
        return false;
    }
}
