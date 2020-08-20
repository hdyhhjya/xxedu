package com.daisy.xxedu.service;

import com.daisy.xxedu.entity.CourseForm;
import com.daisy.xxedu.entity.Subject;

import java.util.List;

public interface CourseService {
    List<Subject> getSubjects();
    String[][] getUpdatingCourses(Long teacherId, Long semesterId, Long classId);
    boolean updateCourses(CourseForm form);
    String[][] getCurrentCourses(Long teacherId, Long semesterId);
}
