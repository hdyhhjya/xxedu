package com.daisy.xxedu.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
public class Course {
    private Long id;
    private BigDecimal fullScore;
    private BigDecimal excellentScore;
    private BigDecimal passingScore;
    private Long teacherId;
    private Integer subjectId;
    private Long semesterId;
    private Long classId;

    public Course(Long teacherId, Integer subjectId, Long semesterId, Long classId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.semesterId = semesterId;
        this.classId = classId;
    }

    public Course(Long id, BigDecimal fullScore, BigDecimal excellentScore, BigDecimal passingScore) {
        this.id = id;
        this.fullScore = fullScore;
        this.excellentScore = excellentScore;
        this.passingScore = passingScore;
    }
}
