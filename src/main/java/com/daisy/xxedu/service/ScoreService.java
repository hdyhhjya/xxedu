package com.daisy.xxedu.service;

import com.daisy.xxedu.entity.ScoreForm;
import com.daisy.xxedu.entity.Subject;

import java.util.List;

public interface ScoreService {
    ScoreForm getScoreForm(Long teacherId, Long classId, Integer subjectId);
    boolean saveScore(ScoreForm form, Long teacherId);
    List<List<String>> getScoreTable(Long teacherId, Long classId);
    List<Subject> getTeachingSubjects(Long teacherId, Long classId);
}
