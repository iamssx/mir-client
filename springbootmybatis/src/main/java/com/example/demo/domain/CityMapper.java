package com.example.demo.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface CityMapper {
    @Select("SELECT * FROM CITY WHERE state = #{state}")
    List<City> findByState(@Param("state") String state);

}
