package com.ufoai.platform.controller.business;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ufoai.platform.common.utils.ShiroUtils;
import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.entity.Rota;
import com.ufoai.platform.pojo.base.ResultBean;
import com.ufoai.platform.pojo.business.RotaBean;
import com.ufoai.platform.service.business.IRotaService;
import com.ufoai.platform.utils.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxb
 * @since 2019-04-24
 */
@RestController
@RequestMapping("/platform")
public class RotaController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IRotaService rotaService;

    /**
     * 新增
     * @param rota
     * @return
     */
    @PostMapping("/rota")
    public ResultBean saveRota(@RequestBody @Validated Rota rota) {
        ResultBean resultBean = new ResultBean();
        try {
            rota.setCreater(ShiroUtils.getUserId());
            rota.setCreateTime(DateTimeUtil.getToday());
            rotaService.save(rota);
            resultBean.setMessage(ErrorMatrix.DB_SAVE_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setCode(ErrorMatrix.CODE_OK);
        }catch (Exception e){
            logger.info("RotaController insert Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 修改
     * @param rota
     * @return
     */
    @PutMapping("/rota")
    public ResultBean updateRota(@RequestBody @Validated Rota rota) {
        ResultBean resultBean = new ResultBean();
        try {
            rota.setUpdater(ShiroUtils.getUserId());
            rota.setUpdateTime(DateTimeUtil.getToday());
            rotaService.updateById(rota);
            resultBean.setMessage(ErrorMatrix.DB_UPDATE_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setCode(ErrorMatrix.CODE_OK);
        }catch (Exception e){
            logger.info("RotaController updateRota Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 查询部门值班
     * @param rotaInfo
     * @return
     */
    @GetMapping("/rota")
    public ResultBean getRota(Rota rotaInfo) {
        ResultBean resultBean = new ResultBean();
        try {
            Rota rota = rotaService.getOne(new QueryWrapper<Rota>().eq("sector_id",rotaInfo.getSectorId())
                    .eq("date",rotaInfo.getDate()));
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setInfo(rota);
        }catch (Exception e){
            logger.info("RotaController getRota Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 值班表
     * @param date
     * @return
     */
    @GetMapping("/rotas/{date}")
    public ResultBean getRotas(@PathVariable("date") String date) {
        ResultBean resultBean = new ResultBean();
        try {
            List<RotaBean> rotas = rotaService.rotaList(date);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setInfo(rotas);
        }catch (Exception e){
            logger.info("RotaController getRota Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }
}
