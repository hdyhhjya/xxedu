package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int insert(Teacher teacher);
    Teacher getTeacherById(Long id);
    int deleteById(@Param("id")Long id);
}
