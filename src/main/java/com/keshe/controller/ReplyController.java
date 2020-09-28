package com.keshe.controller;

import com.keshe.entity.RetJsonData;
import com.keshe.service.ReplyService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 10:06
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Resource
    private ReplyService replyService;


    /**
     * 保存回复
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:reply
     *  失败：返回   success:false
     *               errorMsg:查询失败/回复失败/评论已被删除
     *               data:null
     * @param userId
     * @param parentId
     * @param replyInfo
     * @return
     */
    @RequestMapping("/saveReply")
    @ResponseBody
    public RetJsonData saveReply(String userId, String parentId, String replyInfo){
//        System.out.println(userId);
//        System.out.println(parentId);
//        System.out.println(replyInfo);
        return replyService.saveReply(userId, parentId, replyInfo);
    }


    /**
     * 查询回复
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:list<reply>
     *  失败：返回   success:false
     *               errorMsg:获取失败
     *               data:null
     * @param parentId
     * @return
     */
    @RequestMapping("/getReplysById")
    @ResponseBody
    public RetJsonData getReplysById(String parentId){
        return replyService.getReplysById(parentId);
    }




}
