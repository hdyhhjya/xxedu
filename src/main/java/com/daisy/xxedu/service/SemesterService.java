package com.daisy.xxedu.service;

import com.daisy.xxedu.entity.Semester;

import java.util.List;

public interface SemesterService {
    Semester getSemesterById(Long id);
    boolean updateSemester(Semester semester, Long userId);
    List<Semester> getSemestersByUserId(Long userId);
    boolean deleteSemester(Long id);
    Semester getCurrentSemester(Long teacherId);
}
