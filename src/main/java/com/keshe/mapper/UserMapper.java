package com.keshe.mapper;

import com.keshe.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUser();

    //登录
    @Select("select * from user where user_name = #{username} and user_password = #{password}")
    User login(String username, String password);

    //根据用户名查询用户
    @Select("select * from user where user_name = #{userName}")
    User userByname(String userName);

    //根据邮箱查询用户
    @Select("select * from user where user_email=#{userEmail}")
    User userByEmile(String email);

    //根据userId查询用户
    @Select("select * from user where user_id=#{userId}")
    User userByUserId(String userId);

    //注册
    @Insert("insert into user(user_name, user_password, user_email, user_ctime, user_sex, user_status, user_img, user_birthday, user_personalized, user_realname, user_msgcount, user_fans)" +
            "values(#{userName}, #{userPassword}, #{userEmail}, #{userCtime}, #{userSex}, #{userStatus}, #{userImg}, #{userBirthday}, #{userPersonalized}, #{userRealname}, #{userMsgcount}, #{userFans})")
    Integer register(User user);

    //忘记密码进行修改
    @Update("update user set user_password=#{password} where user_email=#{userEmail}")
    Integer updatePassword(String userEmail, String password);

    //根据userId进行查询用户数据
    @Select("select * from user where user_id=#{userId}")
    User queryUserId(String userId);

    //根据userId进行修改用户信息
    @Update("update user set user_name=#{userName},user_sex=#{userSex},user_birthday=#{userBirthday},user_personalized=#{userPersonalized},user_realname=#{userRealname}" +
            "where user_id=#{userId}")
    Integer updateUserInfo(User user);

    //根据用户userId进行修改头像
    @Update("update user set user_img = #{url} where user_id=#{userId}")
    Integer updateHeadImg(String userId, String url);

    //关注增加粉丝数
    @Update("update user set user_fans = user_fans+1 where user_id=#{userId}")
    Integer icrUserFans(String userId);

    //取关减少粉丝数
    @Update("update user set user_fans = user_fans-1 where user_id=#{userId}")
    Integer redUserFans(String userId);


    //关注增加说说数
    @Update("update user set user_msgcount = user_msgcount+1 where user_id=#{userId}")
    Integer icrUserMsgCount(String userId);

    //取关减少说说数
    @Update("update user set user_msgcount = user_msgcount-1 where user_id=#{userId}")
    Integer redUserMsgCount(String userId);

    //根据search内容插查询用户
    @Select("select * from user where user_name like CONCAT('%', #{searchInfo}, '%')")
    List<User> searchBySearchInfo(String searchInfo);
}
