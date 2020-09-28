package com.keshe.mapper;

import com.keshe.entity.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoMapper {

    @Select("select * from video")
    List<Video> getAllVideo();

    //保存video
    @Insert("Insert into video(message_id, video_url)" +
            "values(#{messageId}, #{videoUrl})")
    Integer saveVideo(Video video);

    //根据messageId返回video对象
    @Select("select * from video where message_id=#{messageId}")
    Video videoByMessageId(String messageId);

    //根据传入messageId的删除video
    @Delete("delete from video where message_id=#{messageId}")
    Integer delVideoByMessageId(String messageId);


}
