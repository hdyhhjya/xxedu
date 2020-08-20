package com.daisy.xxedu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ScoreForm {
    private Long semesterId;
    private String semesterName;
    private Long classId;
    private String className;
    private Integer subjectId;
    private Subject subject;
    private BigDecimal fullScore;
    private BigDecimal excellentScore;
    private BigDecimal passingScore;
    private List<ScoreInfo> scores;

    public ScoreForm() {
        scores = new ArrayList<>();
    }
}