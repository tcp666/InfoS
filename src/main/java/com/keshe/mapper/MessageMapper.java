package com.keshe.mapper;

import com.keshe.entity.Forward;
import com.keshe.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("select * from message")
    List<Message> getAllMessage();

    //保存动态信息
    @Insert("insert into message(user_id, message_lable, message_info, message_ctime, message_state, message_collectnum, message_commentnum, message_transpondnum, message_agreenum, message_readnum)" +
            "values(#{userId}, #{messageLable}, #{messageInfo}, #{messageCtime}, #{messageState}, #{messageCollectNum}, #{messageCommentNum}, #{messageTranspondNum}, #{messageAgreeNum}, #{messageReadNum})")
    Integer saveMessage(Message message);

    //根据info和userId查询messageId
    @Select("select message_id from message where user_id=#{userId} and message_info=#{messageInfo}")
    String getMessageId(String userId, String messageInfo);

    //根据messageId查询message
    @Select("select * from message where message_id=#{messageId}")
    Message messageByMessageId(String messageId);

    //个人信息页面的个人动态分页显示
    @Select("select * from message where user_id = #{userId} and message_state=${1}  ORDER BY message_ctime DESC")
    List<Message> messageByUserId(String userId);

    //主页信息页面的动态显示
    @Select("select * from message where message_state=${1} ORDER BY message_ctime DESC")
    List<Message> messageInfo();

    //根据用户Id查询用户动态总数
    @Select("select count(1) from message where user_id = #{userId}")
    Integer messageCountByUserId(String userId);

    //根据类型查询messages
    @Select("select * from message where message_lable=#{type}")
    List<Message> messageByType(String type);

    //根据messageId进行删除message
    @Delete("delete from message where message_id=#{messageId}")
    Integer delMessage(String messageId);

    //根据messageId增加message_collectnum字段
    @Update("update message set message_collectnum=message_collectnum+1 where message_id=#{messageId}")
    Integer addCollectnum(String messageId);

    //根据messageId减少message_collectnum字段
    @Update("update message set message_collectnum=message_collectnum-1 where message_id=#{messageId}")
    Integer redCollectnum(String messageId);

    //根据messageId增加message_agreenum字段
    @Update("update message set message_agreenum=message_agreenum+1 where message_id=#{messageId}")
    Integer addAgreenum(String messageId);

    //根据messageId减少message_agreenum字段
    @Update("update message set message_agreenum=message_agreenum-1 where message_id=#{messageId}")
    Integer redAgreenum(String messageId);

    //根据messageId增加message_readnum字段
    @Update("update message set message_readnum=message_readnum+1 where message_id=#{messageId}")
    Integer addReadnum(String messageId);

    //根据search内容插查询动态
    @Select("select * from message where message_info like CONCAT('%', #{searchInfo}, '%')")
    List<Message> messageBySearchInfo(String searchInfo);



    /**
     * 转发让转发数加一
     * @param forward
     * @return
     */
    @Update({"update message set message_transpondnum=message_transpondnum+1 where message_id=#{messageId}"})
    Integer forWardUpdateTranspondnum(Forward forward);


    @Select("select * from message where message_id=#{messageId}")
    Message findMessageById(String messageId);


}
