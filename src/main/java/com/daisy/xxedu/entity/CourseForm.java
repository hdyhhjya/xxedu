package com.daisy.xxedu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class CourseForm {
    private Long teacherId;
    private Long semesterId;
    private String semesterName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private Long classId;
    private List<CourseInfo> course;
}
