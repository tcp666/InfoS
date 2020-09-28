package com.keshe.mapper;

import com.keshe.entity.Collections;
import com.keshe.entity.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionsMapper {

    @Select("select * from collection")
    List<Collections> getAllCollections();

    //增加收藏
    @Insert("Insert into collection(user_id, message_id, collection_status, collection_time)" +
            "values(#{userId}, #{messageId}, #{collectionStatus}, #{collectionTime})")
    Integer addCollection(Collections collections);


    //删除收藏
    @Delete("delete from collection where user_id=#{userId} and message_id=#{messageId}")
    Integer delCollection(String userId, String messageId);


    //根据传入的userId 和 messageId 查询收藏
    @Select("select * from collection where user_id=#{userId} and message_id=#{messageId}")
    Collections verifyCollection(String userId, String messageId);


    //根据用户Id在收藏表中查询所有收藏de文章，然后返回所有的message
    @Select("select * from message where message_id in (select message_id from collection where user_id=#{userId})")
    List<Message> collecMessageByUserId(String userId);


}
