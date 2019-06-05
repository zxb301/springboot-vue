package com.ufoai.platform.service.base;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ufoai.platform.entity.LogOperate;
import com.ufoai.platform.entity.SystemUser;
import com.ufoai.platform.pojo.base.LogOperateRes;
import com.ufoai.platform.pojo.base.PageParamReq;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author zxb
 * @since 2018-08-16
 */
public interface ILogOperateService extends IService<LogOperate> {

    Page<LogOperateRes> selectList(PageParamReq param);

    void saveLog(ProceedingJoinPoint joinPoint, long time, SystemUser sUser, SystemUser currUser, String ip);
}
