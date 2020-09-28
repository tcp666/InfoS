package com.keshe.mapper;

import com.keshe.entity.Lable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LableMapper {

    @Select("select * from lable")
    List<Lable> getAllLable();
}
