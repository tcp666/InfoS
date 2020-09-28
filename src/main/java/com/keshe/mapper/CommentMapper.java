package com.keshe.mapper;

import com.keshe.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment")
    List<Comment> getAllComment();

    //根据messageId查询comment
    @Select("select * from comment where message_id=#{messageId}")
    List<Comment> commentByMessageId(String messageId);

    //增加主评论
    @Insert("Insert into comment(user_id, message_id, comment_info, comment_time)" +
            "values(#{userId}, #{messageId}, #{commentInfo}, #{commentTime})")
    Integer addComment(Comment comment);

    //查询刚才存储的评论
    @Select("select * from comment where user_id=#{userId} and message_id=#{messageId} and comment_info=#{commentInfo}")
    Comment getComment(Comment comment);

    //根据commentId查询评论
    @Select("select * from comment where comment_id=#{commentId}")
    Comment commentByCommentId(String commentId);
}

//2020-06-04 08:19:20