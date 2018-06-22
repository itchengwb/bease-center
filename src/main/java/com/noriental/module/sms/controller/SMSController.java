package com.noriental.module.sms.controller;

import com.noriental.module.sms.model.CommonDes;
import com.noriental.module.sms.model.RequestChuangCacheMessage;
import com.noriental.module.sms.service.ChuangCacheMessageService;
import com.noriental.module.sms.service.SMSService;
import com.noriental.module.user.model.UserDomain;
import com.noriental.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Wenbo Cheng on2017/8/16.
 */
@Controller
public class SMSController {

    @Autowired
    private ChuangCacheMessageService chuangCacheMessageService;

    @Autowired
    SMSService service;

    /**
     * 接收短信内容，发送并记录数据库表
     * @return
     */
    @ResponseBody
    @GetMapping("/send")
    public String send(){
        RequestChuangCacheMessage request = new RequestChuangCacheMessage();
        request.setMobile("18515481207");
        request.setContent("您的短信验证码为：1111，欢迎注册OKAY，请在1分钟内输入。");
        CommonDes commonResponse = chuangCacheMessageService.sendChuangCacheMessage(request);
        System.out.println("commonResponse : " + JsonUtil.obj2Json(commonResponse));
        return "sucess";
    }

    /**
     * 分页查询所有发送的短信记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object find(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        System.out.println("=================");

        return service.selectAll(pageNum,pageSize);
    }

}
