package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.ClassInfo;

import java.util.List;

public interface ClassMapper {
    int insert(ClassInfo classInfo);
    int deleteById(Long id);
    List<ClassInfo> getClassesBySchoolId(Long schoolId);
    ClassInfo getClassById(Long id);
}
