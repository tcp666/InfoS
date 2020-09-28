package com.keshe.mapper;

import com.keshe.entity.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyMapper {

    @Select("select * from reply")
    List<Reply> getAllReply();

    //根据replyId查询回复
    @Select("select * from reply where reply_id=#{replyId}")
    Reply replyByreplyId(String replyId);

    //保存回复表
    @Insert("Insert into reply(reply_userid, reply_byuserid, reply_info, commentparent, reply_time)" +
            "values(#{replyUserId}, #{replyByUserId}, #{replyInfo}, #{commentParent}, #{replyTime})")
    Integer saveReply(Reply reply);

//    查询刚保存的
    @Select("select * from reply where reply_userid=#{replyUserId} and reply_byuserid=#{replyByUserId} and reply_info=#{replyInfo} and commentparent=#{commentParent} ")
    Reply getReply(Reply reply);

    //根据父评论进行查询回复
    @Select("select * from reply where commentparent=#{parentId}")
    List<Reply> getReplyById(String parentId);
}
