package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.Subject;

import java.util.List;

public interface SubjectMapper {
    List<Subject> getSubjects();
    Subject getSubjectById(Integer id);
}
