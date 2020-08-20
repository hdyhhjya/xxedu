package com.daisy.xxedu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ClassInfo {
    private Long id;
    @DateTimeFormat(pattern="yyyy")
    private Date grade;
    private Integer number;
    private Long schoolId;
}
