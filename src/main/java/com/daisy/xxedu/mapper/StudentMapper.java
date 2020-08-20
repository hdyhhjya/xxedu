package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> getStudentsByClassId(Long classId);
    int insert(Student student);
    int deleteById(Long id);
    int update(Student student);
    Student getStudentById(Long id);
}
