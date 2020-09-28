package com.keshe.mapper;

import com.keshe.entity.RoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleUserMapper {

    @Select("select * from role_user")
    List<RoleUser> getAllRoleUser();
}
