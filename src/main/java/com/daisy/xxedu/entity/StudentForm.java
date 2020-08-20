package com.daisy.xxedu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class StudentForm {
    Long classId;
    List<Student> studentList;
}
