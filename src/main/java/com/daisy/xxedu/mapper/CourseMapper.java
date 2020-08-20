package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    Long save(Course course);
    int deleteById(Long id);
    List<Course> getCoursesBySemesterClassIdNotTeacherId(@Param("teacherId") Long teacherId, @Param("semesterId") Long semesterId, @Param("classId") Long classId);
    List<Course> getCoursesByTeacherSemesterIdNotClassId(@Param("teacherId")Long teacherId, @Param("semesterId")Long semesterId, @Param("classId")Long classId);
    List<Course> getCourses(Course course);
    int updateScoresById(Course course);
}
