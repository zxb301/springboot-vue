package com.ufoai.platform.controller.business;


import com.alibaba.fastjson.JSONObject;
import com.ufoai.platform.common.utils.Base64Utils;
import com.ufoai.platform.entity.Notice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 *  公告 controller
 * </p>
 *
 * @ClassName:
 * @Author WengQuan
 * @Description: TODO
 * @param:
 * @return:
 * @date: 2019/4/25 16:57
 */
@RestController
@RequestMapping("/platform/notice")
public class NoticeController {

    @Value("${notes.uploadPath}")
    String pathUrl;

    @Value("${notes.staticAccessPath}")
    String pathMapping;

    /**
     * @ClassName: imgUpdate
     * @Author WengQuan
     * @Description: TODO
     * @param: []
     * @return: void
     * @date: 2019/4/25 14:49
     */
    @RequestMapping("/imgUpload")
    public JSONObject imgUpdate(HttpServletRequest request, HttpServletResponse resp){
        JSONObject jsonObject = new JSONObject();
        InputStream is;
        Collection<Part> parts= null;
        try {
            parts = request.getParts();
            Iterator<Part> iterator=parts.iterator();
            while (iterator.hasNext()) {
                Part part = iterator.next();
                is = part.getInputStream();
                byte[] data  = new byte[is.available()];
                is.read(data);
                is.close();
                // 对字节数组Base64编码
                BASE64Encoder encoder = new BASE64Encoder();
                //System.out.println(encoder.encode(data));
                long pathimg = System.currentTimeMillis();
                Base64Utils.Base64ToImage(encoder.encode(data),pathUrl+pathimg+".jpg");
                jsonObject.put("url",pathMapping+pathimg+".jpg");//类似 /file/test.mp4
                jsonObject.put("code","200");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

//    @RequestMapping("/addnotice")
//    public Map addNotice(Notice notice){
//        //新增公告(类型)
//        String userName_type ="";
//        if("合成作战民警".equals(userName_type)){
//            notice.insert();//保存到数据库
//        }else {
//            //保存到数据库
//            //添加审核
//        }
//
//        return null;
//    }
}
