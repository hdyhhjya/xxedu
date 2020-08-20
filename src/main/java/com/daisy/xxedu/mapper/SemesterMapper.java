package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.Semester;

import java.util.List;

public interface SemesterMapper {
    Semester getSemesterById(Long id);
    List<Semester> getSemestersBySchoolId(Long schoolId);
    Long insert(Semester semester);
    int updateSemester(Semester semester);
    int deleteById(Long id);
    Semester getCurrentSemester(Long schoolId);
}
