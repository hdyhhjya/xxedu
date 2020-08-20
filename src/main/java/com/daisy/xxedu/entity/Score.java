package com.daisy.xxedu.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Score {
    private Long studentId;
    private Long courseId;
    private BigDecimal score;
}
