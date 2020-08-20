package com.daisy.xxedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ScoreInfo {
    private Long studentId;
    private String studentName;
    private BigDecimal score;
}
