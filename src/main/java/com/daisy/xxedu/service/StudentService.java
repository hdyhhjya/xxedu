package com.daisy.xxedu.service;

import com.daisy.xxedu.entity.Student;
import com.daisy.xxedu.entity.StudentForm;

import java.util.List;

public interface StudentService {
    boolean newStudent(StudentForm form);
    List<Student> getStudents(Long classId);
    boolean updateStudent(Student student);
    boolean deleteStudent(Long studentId);
    Student getStudentById(Long id);
}
