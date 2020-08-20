package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.CourseTime;

import java.util.List;

public interface CourseTimeMapper {
    Long save(CourseTime courseTime);
    List<CourseTime> getCourseTimeByCourseId(Long courseId);
    int deleteByCourseId(Long courseId);
}
