package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.Score;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface ScoreMapper {
    BigDecimal getScoreByStudentCourseId(@Param("studentId")Long studentId, @Param("courseId")Long courseId);
    int save(Score score);
    int update(Score score);
    int delete(@Param("studentId")Long studentId, @Param("courseId")Long courseId);
    int getScoreCountByCourseId(Long courseId);
}
