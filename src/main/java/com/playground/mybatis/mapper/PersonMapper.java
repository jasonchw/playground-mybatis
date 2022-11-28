package com.playground.mybatis.mapper;

import com.playground.mybatis.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonMapper {
    Person findById(@Param("id") Long id);

    Person findByEmail(@Param("email") String email);

    int insert(Person person);

    int update(Person person);
}
