package com.keshe.mapper;

import com.keshe.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("select * from role")
    List<Role> getAllRole();
}
