package com.daisy.xxedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfo {
    private Integer weekday;
    private Integer courseNumber;
    private Integer subjectId;
}
