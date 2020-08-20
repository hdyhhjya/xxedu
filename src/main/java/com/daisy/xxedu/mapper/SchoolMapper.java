package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolMapper {
    int insert(School school);
    School getSchoolById(Long id);
    List<School> getSchools();
    int updateSchool(School school);
    int deleteById(@Param("id") Long id);
}
