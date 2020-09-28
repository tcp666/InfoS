package com.keshe.mapper;

import com.keshe.entity.Relation;
import com.keshe.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RelationMapper {

    @Select("select * from relation")
    List<Relation> getAllRelation();

    //新增关注
    @Insert("insert into relation(user_id, user_byid, relation_type, relation_time)" +
            "values(#{userId}, #{userById}, #{relationType}, #{relationTime})")
    Integer addRelation(Relation relation);

    //取消关注
    @Delete("delete from relation where user_id=#{userId} and user_byid=#{userById}")
    Integer delRelation(String userId, String userById);

    //根据传入的userId 查询关注用户的user信息
    @Select("select * from user where user_Id in (select user_byid from relation where user_id = #{userId})")
    List<User> followUserByUserId(String userId);

    //根据传入的userId和userById查询关注表
    @Select("select * from relation where user_id=#{userId} and user_byid=#{userById}")
    Relation verifyRelation(String userId, String userById);

    //根据传入的userId查询用户所关注用户的总数
    @Select("select count(1) from relation where user_id=#{userId}")
    Integer relationCountByUserId(String userId);
}
