package com.ufoai.platform.controller.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.pojo.base.LogOperateRes;
import com.ufoai.platform.pojo.base.PageParamReq;
import com.ufoai.platform.pojo.base.ResultBean;
import com.ufoai.platform.service.base.ILogOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2018-08-16
 */
@Controller
@RequestMapping("/notes/logOperate")
public class LogOperateController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ILogOperateService logOperateService;

    /**
     * 列表
     * @param param
     * @return
     */
    @ResponseBody
    @GetMapping("/logOperates")
    public ResultBean logOperateList(@Validated PageParamReq param) {
        ResultBean resultBean = new ResultBean();
        try {
            Page<LogOperateRes> pages = logOperateService.selectList(param);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setInfo(pages);
        } catch (Exception e) {
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
            logger.error("LogOperateController logOperateList Exception: " + e.getMessage());
        }
        return resultBean;
    }




}
