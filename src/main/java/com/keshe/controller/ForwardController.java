package com.keshe.controller;

import com.keshe.entity.Forward;
import com.keshe.entity.RetJsonData;
import com.keshe.service.ForwardService;
import com.keshe.service.impl.ForwardServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 9:59
 */
@RestController

public class ForwardController {
    /**
     * 转发
     */
    @Resource
    private ForwardServiceImpl forwardService;
    @RequestMapping("/forward")
    public RetJsonData forward(String messageId,String userId){
        Forward forward=new Forward();
        forward.setUserId(userId);
        forward.setMessageId(messageId);
        return forwardService.forward(forward);
    }



    /*

url: forward
data:{
    messageId:messageId,
     userId: userId
}
转发成功
        {
        "success":true,
        "data":"转发成功",
        "errorMsg":"转发成功"
        }
转发失败
        {
        "success":true,
        "data":"转发失败",
        "errorMsg":"转发失败"
        }

 */

}
