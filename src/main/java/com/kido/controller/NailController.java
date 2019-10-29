package com.kido.controller;

import com.kido.common.JsonMsg;
import com.kido.domain.Nail;
import com.kido.service.NailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class NailController {
    @Autowired
    private NailService nailService;

    /**
     * 保存用户信息
     * @param
     * @return
     */
    @PostMapping("/v_nail_save")
    @ResponseBody
    public JsonMsg saveInformation(String userName, String tel, String place, String information, String kind){
        Nail nail = new Nail();
        String startTime = "2019-06-11 17:20:30";
        //将字符串格式转换成Date格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;

        try {
            //判断一下手机号是否
            int result = nailService.countTel(tel);
            if(result==1){
                return JsonMsg.ERROR("您已报名,请勿重复报名");
            }else {
                dateTime = sdf.parse(startTime);
//                Date date = new java.sql.Date(dateTime.getTime());
                nail.setUserName(userName);
                nail.setTel(tel);
//                nail.setStartTime(date);
                nail.setStartTime(dateTime);
                nail.setPlace(place);
                nail.setInformation(information);
                nail.setKind(kind);
                nailService.insert(nail);
                return JsonMsg.OK("保存成功");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return JsonMsg.ERROR("网络异常,请稍后再试");
        }


    }
}
