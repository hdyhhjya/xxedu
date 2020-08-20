package com.daisy.xxedu.service.impl;

import com.daisy.xxedu.entity.*;
import com.daisy.xxedu.mapper.CourseMapper;
import com.daisy.xxedu.mapper.CourseTimeMapper;
import com.daisy.xxedu.mapper.SubjectMapper;
import com.daisy.xxedu.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseTimeMapper courseTimeMapper;

    @Override
    public List<Subject> getSubjects() {
        return subjectMapper.getSubjects();
    }

    @Override
    @Transactional
    public String[][] getUpdatingCourses(Long teacherId, Long semesterId, Long classId) {
        List<Course> courseList = courseMapper.getCourses(new Course(teacherId, null, semesterId, classId));
        String[][] courses = getCourses(courseList);
        return courses;
    }

    @Override
    @Transactional
    public boolean updateCourses(CourseForm form) {
        List<Course> otherTeacherCourseList = courseMapper.getCoursesBySemesterClassIdNotTeacherId(form.getTeacherId(), form.getSemesterId(), form.getClassId());
        List<Course> otherClassCourseList = courseMapper.getCoursesByTeacherSemesterIdNotClassId(form.getTeacherId(), form.getSemesterId(), form.getClassId());
        if (isCourseConflict(otherTeacherCourseList, form) || isCourseConflict(otherClassCourseList, form)) {
            return false;
        }

        List<Course> oldCourseList = courseMapper.getCourses(new Course(form.getTeacherId(), null, form.getSemesterId(), form.getClassId()));
        Map<Integer, Long> savedCourse = new HashMap<>();
        for (Course course : oldCourseList) {
            savedCourse.put(course.getSubjectId(), course.getId());
            courseTimeMapper.deleteByCourseId(course.getId());
        }

        for (CourseInfo info : form.getCourse()) {
            Integer subjectId = info.getSubjectId();
            if (subjectId != null) {
                if (!savedCourse.containsKey(subjectId)) {
                    Course course = new Course(form.getTeacherId(), info.getSubjectId(), form.getSemesterId(), form.getClassId());
                    courseMapper.save(course);
                    savedCourse.put(subjectId, course.getId());
                }
                courseTimeMapper.save(new CourseTime(info.getWeekday(), info.getCourseNumber(), savedCourse.get(subjectId)));
            }
        }

        return true;
    }

    @Override
    public String[][] getCurrentCourses(Long teacherId, Long semesterId) {
        List<Course> courseList = courseMapper.getCourses(new Course(teacherId, null, semesterId, null));
        String[][] courses = getCourses(courseList);
        return courses;
    }

    private String[][] getCourses(List<Course> courseList) {
        Map<Integer, String> subjectMap = getSubjectMap();
        String[][] courses = new String[8][5];
        for (Course course : courseList) {
            List<CourseTime> courseTimeList = courseTimeMapper.getCourseTimeByCourseId(course.getId());
            for (CourseTime courseTime : courseTimeList) {
                courses[courseTime.getNumber() - 1][courseTime.getWeekday() - 1] = subjectMap.get(course.getSubjectId());
            }
        }
        return courses;
    }

    private Map<Integer, String> getSubjectMap() {
        List<Subject> subjectList = subjectMapper.getSubjects();
        Map<Integer, String> subjectMap = new HashMap<>();
        for (Subject subject : subjectList) {
            subjectMap.put(subject.getId(), subject.getName());
        }
        return subjectMap;
    }

    private boolean isCourseConflict(List<Course> otherCourseList, CourseForm form) {
        String[][] otherCourses = getCourses(otherCourseList);
        for (CourseInfo info : form.getCourse()) {
            if (info.getSubjectId() != null) {
                if (otherCourses[info.getCourseNumber() - 1][info.getWeekday() - 1] != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
