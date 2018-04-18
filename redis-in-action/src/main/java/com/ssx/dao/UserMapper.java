package com.ssx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by SSX on 2017/11/3.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT uid FROM User WHERE username = #{username}")
    Integer findByUsername(@Param("username")String username);
}
