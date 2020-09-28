package com.keshe.mapper;

import com.keshe.entity.Img;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImgMapper {

    @Select("select * from img")
    List<Img> getAllImg();

    //保存Img
    @Insert("insert into img(message_id, img_url)" +
            "values(#{messageId}, #{imgUrl})")
    Integer saveImg(Img img);

    //根据messageId进行查询动态图片
    @Select("select * from img where message_id=#{messageId}")
    List<Img> imgByMessageId(String messageId);

    //根据传入的messageId进行删除该动态的图片信息
    @Delete("delete from img where img_id in (select a.img_id from (select a.img_id, message_id from img a where a.message_id = #{messageId})a);")
    Integer delImgsByMessageId(String messageId);
}
