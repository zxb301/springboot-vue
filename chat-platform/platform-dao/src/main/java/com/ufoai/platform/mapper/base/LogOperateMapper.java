package com.ufoai.platform.mapper.base;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ufoai.platform.entity.LogOperate;
import com.ufoai.platform.pojo.base.LogOperateRes;
import com.ufoai.platform.pojo.base.PageParamReq;
import java.util.List;


/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2018-08-16
 */
public interface LogOperateMapper extends BaseMapper<LogOperate> {

    List<LogOperateRes> selectLogOperateList(Page<LogOperateRes> page, PageParamReq param);
}
