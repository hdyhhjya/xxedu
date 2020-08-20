package com.daisy.xxedu.service.impl;

import com.daisy.xxedu.entity.*;
import com.daisy.xxedu.mapper.*;
import com.daisy.xxedu.service.SemesterService;
import com.daisy.xxedu.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private CourseTimeMapper courseTimeMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${user.password}")
    private String password;

    @Override
    @Transactional
    public Map<String, Object> getUsers(int pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<User> userTable = userMapper.getTeacherUsers();
        PageInfo pageInfo = new PageInfo(userTable);

        List<UserInformationForm> users = new ArrayList<>();
        for (User user: userTable) {
            Teacher teacher = teacherMapper.getTeacherById(user.getTeacherId());
            School school = schoolMapper.getSchoolById(teacher.getSchoolId());
            users.add(new UserInformationForm(user.getId(),user.getUsername(), user.getPassword(),
                    teacher.getId(), teacher.getName(), teacher.getEmail(),
                    school.getId(), school.getName(), school.getAddress()));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("users", users);
        map.put("pageInfo", pageInfo);
        return map;
    }

    @Override
    @Transactional
    public void register(UserInformationForm form) {
        form.setPassword(password);
        form = form.encipher(passwordEncoder);
        Teacher teacher = new Teacher(form.getName(), form.getEmail(), form.getSchoolId());
        teacherMapper.insert(teacher);
        User user = new User(form.getUsername(), form.getPassword(), teacher.getId());
        userMapper.insert(user);
    }

    @Override
    public boolean resetPassword(Long id) {
        if(userMapper.updatePassword(id, passwordEncoder.encode(password)) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        Long teacherId = userMapper.getTeacherIdByUserId(id);
        if (teacherId != null) {
            teacherMapper.deleteById(teacherId);
            List<Course> courseList = courseMapper.getCourses(new Course(teacherId, null, semesterService.getCurrentSemester(teacherId).getId(), null));
            for (Course course : courseList) {
                courseTimeMapper.deleteByCourseId(course.getId());
            }
        }
        if(userMapper.deleteById(id) > 0) {
            return true;
        }
        return false;
    }

}
