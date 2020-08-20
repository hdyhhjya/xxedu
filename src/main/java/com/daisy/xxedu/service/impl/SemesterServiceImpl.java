package com.daisy.xxedu.service.impl;

import com.daisy.xxedu.entity.Course;
import com.daisy.xxedu.entity.Semester;
import com.daisy.xxedu.mapper.CourseMapper;
import com.daisy.xxedu.mapper.SemesterMapper;
import com.daisy.xxedu.mapper.TeacherMapper;
import com.daisy.xxedu.mapper.UserMapper;
import com.daisy.xxedu.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterMapper semesterMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Semester getSemesterById(Long id) {
        return semesterMapper.getSemesterById(id);
    }

    @Override
    @Transactional
    public boolean updateSemester(Semester semester, Long userId) {
        boolean isSuccess = false;
        if (semester.getId() == null) {
            semester.setSchoolId(teacherMapper.getTeacherById(userMapper.getTeacherIdByUserId(userId)).getSchoolId());
            if(semesterMapper.insert(semester) > 0) {
                isSuccess = true;
            }
        } else {
            if(semesterMapper.updateSemester(semester) > 0) {
                isSuccess = true;
            }
        }
        return isSuccess;
    }

    @Override
    @Transactional
    public List<Semester> getSemestersByUserId(Long userId) {
        Long schoolId = teacherMapper.getTeacherById(userMapper.getTeacherIdByUserId(userId)).getSchoolId();
        return semesterMapper.getSemestersBySchoolId(schoolId);
    }

    @Override
    @Transactional
    public boolean deleteSemester(Long id) {
        if (courseMapper.getCourses(new Course(null, null, id, null)).size() == 0) {
            if (semesterMapper.deleteById(id) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Semester getCurrentSemester(Long teacherId) {
        return semesterMapper.getCurrentSemester(teacherMapper.getTeacherById(teacherId).getSchoolId());
    }
}
