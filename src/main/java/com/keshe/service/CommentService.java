package com.keshe.service;

import com.keshe.entity.Comment;
import com.keshe.entity.RetJsonData;

/**
 * @InterfaceName CommentService
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:36
 * @Version 1.0
 */
public interface CommentService {

    /**
     * 保存主评论
     * @param comment
     * @return
     */
    RetJsonData addComment(Comment comment);


}
