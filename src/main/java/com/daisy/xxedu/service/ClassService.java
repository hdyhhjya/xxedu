package com.daisy.xxedu.service;

import com.daisy.xxedu.entity.ClassInfo;

import java.util.Map;

public interface ClassService {
    int insertClassInfo(ClassInfo classInfo, Long teacherId);
    Map<Long, String> getClasses(Long teacherId);
    boolean deleteClass(Long classId);
    String getClassName(Long classId);
}
