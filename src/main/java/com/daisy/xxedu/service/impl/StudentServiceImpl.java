package com.daisy.xxedu.service.impl;

import com.daisy.xxedu.entity.Student;
import com.daisy.xxedu.entity.StudentForm;
import com.daisy.xxedu.mapper.StudentMapper;
import com.daisy.xxedu.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional
    public boolean newStudent(StudentForm form) {
        for(Student student : form.getStudentList()) {
            if (student.getName() != null && student.getBirthday() != null) {
                student.setClassId(form.getClassId());
                studentMapper.insert(student);
            }
        }
        return true;
    }

    @Override
    public List<Student> getStudents(Long classId) {
        return studentMapper.getStudentsByClassId(classId);
    }

    @Override
    public boolean updateStudent(Student student) {
        studentMapper.update(student);
        return true;
    }

    @Override
    public boolean deleteStudent(Long studentId) {
        studentMapper.deleteById(studentId);
        return true;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentMapper.getStudentById(id);
    }
}
