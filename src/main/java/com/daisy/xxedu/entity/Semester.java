package com.daisy.xxedu.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Semester {
    private Long id;
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private Long schoolId;
    public Semester(String name, Date startDate, Date endDate, Long schoolId) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schoolId = schoolId;
    }
}
