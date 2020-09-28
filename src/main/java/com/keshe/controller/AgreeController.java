package com.keshe.controller;

import com.keshe.entity.Agree;
import com.keshe.entity.RetJsonData;
import com.keshe.service.AgreeService;
import com.keshe.tools.Pack;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 9:56
 */
@RestController
@RequestMapping("/agree")
public class AgreeController {

    @Resource
    private AgreeService agreeService;
    @Resource
    private Pack packAgree;

    /**
     * 增加点赞
     *  成功：返回   success:true
     *               data:点赞成功
     *               errorMsg:null
     *  失败：返回   success:false
     *               errorMsg:点赞失败/已被点赞
     *               data:null
     * @param userId
     * @param messageId
     * @return
     */
    @RequestMapping("/addAgree")
    @ResponseBody
    public RetJsonData addAgree(String userId, String messageId){
        Agree agree = packAgree.packAgree(userId, messageId);
        return agreeService.addAgree(agree);
    }


    /**
     * 删除点赞
     *  成功：返回   success:true
     *               data:取赞成功
     *               errorMsg:null
     *  失败：返回   success:false
     *               errorMsg:取赞失败
     *               data:null
     * @param userId
     * @param messageId
     * @return
     */
    @RequestMapping("/redAgree")
    @ResponseBody
    public RetJsonData redAgree(String userId, String messageId){
        return agreeService.redAgree(userId, messageId);
    }


}
