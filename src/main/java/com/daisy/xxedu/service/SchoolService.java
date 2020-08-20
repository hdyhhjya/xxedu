package com.daisy.xxedu.service;

import com.daisy.xxedu.entity.School;

import java.util.Map;

public interface SchoolService {
    Map<String, Object> getSchools(int pageNum);
    int updateSchool(School school);
    School getSchoolById(Long id);
    int insertSchool(School school);
}
