package com.daisy.xxedu.service.impl;

import com.daisy.xxedu.entity.ClassInfo;
import com.daisy.xxedu.entity.Course;
import com.daisy.xxedu.mapper.ClassMapper;
import com.daisy.xxedu.mapper.CourseMapper;
import com.daisy.xxedu.mapper.TeacherMapper;
import com.daisy.xxedu.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    @Transactional
    public int insertClassInfo(ClassInfo classInfo, Long teacherId) {
        classInfo.setSchoolId(teacherMapper.getTeacherById(teacherId).getSchoolId());
        return classMapper.insert(classInfo);
    }

    @Override
    public Map<Long, String> getClasses(Long teacherId) {
        Long schoolId = teacherMapper.getTeacherById(teacherId).getSchoolId();
        List<ClassInfo> classInfoList = classMapper.getClassesBySchoolId(schoolId);
        Map<Long, String> classes = new HashMap<>();
        for (ClassInfo c: classInfoList) {
            classes.put(c.getId(), getClassName(c));
        }
        return classes;
    }

    @Override
    @Transactional
    public boolean deleteClass(Long classId) {
        if (courseMapper.getCourses(new Course(null, null, null, classId)).size() == 0) {
            if (classMapper.deleteById(classId) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getClassName(Long classId) {
        ClassInfo classInfo = classMapper.getClassById(classId);
        return getClassName(classInfo);
    }

    private String getClassName(ClassInfo c) {
        StringBuilder className = new StringBuilder();
        Calendar now = Calendar.getInstance();
        Calendar grade = Calendar.getInstance();
        grade.setTime(c.getGrade());
        if (now.get(Calendar.MONTH) >= 9) {
            className.append(now.get(Calendar.YEAR) - grade.get(Calendar.YEAR) + 1);
        } else {
            className.append(now.get(Calendar.YEAR) - grade.get(Calendar.YEAR));
        }
        className.append("年级");
        className.append(c.getNumber());
        className.append("班");
        return className.toString();
    }
}
