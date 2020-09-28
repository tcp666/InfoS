package com.keshe.mapper;

import com.keshe.entity.ChatRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper
public interface ChatRecordMapper {
    /**
     * svae  chatRecord
     */
    @Insert("insert into chatrecord(comuserid,touserid,message,recordtime) values(#{comUserId},#{toUserId},#{message},#{recordTime})")
    Integer save(ChatRecord chatRecord);

    /**
     *
     * @param comUserId
     * @param toUserId
     * @return
     */
    @Select("select *\n" +
            "from chatrecord where comUserId=#{comUserId} and toUserId=#{toUserId} or comUserId=#{toUserId} and toUserId=#{comUserId} order by recordTime ASC;")
    List<ChatRecord> findAll(@NotNull String comUserId,@NotNull String toUserId);
}
