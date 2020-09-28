package com.keshe.service.impl;

import com.keshe.entity.Comment;
import com.keshe.entity.RetJsonData;
import com.keshe.mapper.CommentMapper;
import com.keshe.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:44
 * @Version 1.0
 */

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public RetJsonData addComment(Comment comment) {

        System.out.println(comment);
        if (commentMapper.addComment(comment) > 0){
            Comment comment1 = commentMapper.getComment(comment);
            System.out.println("comment1"+comment1);
            comment1.setCommentId("comment"+comment1.getCommentId());
            if (comment1 != null){
                return new RetJsonData(true, comment1, null);
            }
            return new RetJsonData(false, "查询错误");
        }
        return new RetJsonData(false, "评论失败");
    }

}
