package com.keshe.controller;

import com.keshe.entity.Comment;
import com.keshe.entity.RetJsonData;
import com.keshe.service.CommentService;
import com.keshe.tools.Pack;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 9:58
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private Pack packComment;


    /**
     * 增加评论
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:comment
     *  失败：返回   success:false
     *               errorMsg:查找失败/评论失败
     *               data:null
     * @param userId
     * @param messageId
     * @param commentInfo
     * @return
     */
    @RequestMapping("/addComment")
    @ResponseBody
    public RetJsonData addComment(String userId, String messageId, String commentInfo){
//        System.out.println(userId+messageId+commentInfo);
        Comment comment = packComment.packComment(userId, messageId, commentInfo);
//        System.out.println(comment);
        return commentService.addComment(comment);
    }


}
