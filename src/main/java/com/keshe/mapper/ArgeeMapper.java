package com.keshe.mapper;

import com.keshe.entity.Agree;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArgeeMapper {

    @Select("select * from agree")
    List<Agree> getAllAgree();

    //增加点赞
    @Insert("insert into agree(user_id, message_id)" +
            "values(#{userId}, #{messageId})")
    Integer addAgree(Agree agree);

    //删除点赞
    @Delete("delete from agree where user_id=#{userId} and message_id=#{messageId}")
    Integer delAgree(String userId, String messageId);

    //根据传入的userId和messageId查询点赞表
    @Select("select * from agree where user_id=#{userId} and message_id=#{messageId}")
    Agree verifyAgree(String userId, String messageId);
}
