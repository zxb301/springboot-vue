package com.ufoai.platform.controller.base;


import com.ufoai.platform.constants.ErrorMatrix;
import com.ufoai.platform.pojo.base.ResultBean;
import com.ufoai.platform.pojo.base.UserInfoRes;
import com.ufoai.platform.pojo.business.SectorTree;
import com.ufoai.platform.service.base.ISectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SectorController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISectorService sectorService;

    /**
     * 查询部门
     * @param
     * @return
     */
    @GetMapping("/sector")
    public ResultBean getSector() {
        ResultBean resultBean = new ResultBean();
        try {
            List<SectorTree> sectorTrees = sectorService.getSectorTree();
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setInfo(sectorTrees);
        }catch (Exception e){
            logger.info("SectorController getSector Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }

    /**
     * 查询部门人员
     * @param
     * @return
     */
    @GetMapping("/sectorUser/{id}")
    public ResultBean getSectorUser(@PathVariable("id") Long id) {
        ResultBean resultBean = new ResultBean();
        try {
            List<UserInfoRes> userInfoRes = sectorService.getSectorUser(id);
            resultBean.setMessage(ErrorMatrix.DB_QUERY_SUCCESS);
            resultBean.setSuccess(true);
            resultBean.setCode(ErrorMatrix.CODE_OK);
            resultBean.setInfo(userInfoRes);
        }catch (Exception e){
            logger.info("SectorController getSector Exception: " + e.getMessage());
            resultBean.setCode(ErrorMatrix.SYS_FAILED);
            resultBean.setMessage(ErrorMatrix.SYS_FILE_ERROR);
        }
        return resultBean;
    }
}
