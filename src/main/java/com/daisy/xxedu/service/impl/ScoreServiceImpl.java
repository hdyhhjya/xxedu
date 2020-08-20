package com.daisy.xxedu.service.impl;

import com.daisy.xxedu.entity.*;
import com.daisy.xxedu.mapper.CourseMapper;
import com.daisy.xxedu.mapper.ScoreMapper;
import com.daisy.xxedu.mapper.StudentMapper;
import com.daisy.xxedu.mapper.SubjectMapper;
import com.daisy.xxedu.service.ClassService;
import com.daisy.xxedu.service.ScoreService;
import com.daisy.xxedu.service.SemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    @Transactional
    public ScoreForm getScoreForm(Long teacherId, Long classId, Integer subjectId) {
        ScoreForm scoreForm = new ScoreForm();
        Semester semester = getCurrentSemester(teacherId);
        scoreForm.setSemesterId(semester.getId());
        scoreForm.setSemesterName(semester.getName());

        scoreForm.setClassId(classId);
        scoreForm.setClassName(classService.getClassName(classId));

        Course course = courseMapper.getCourses(new Course(null, subjectId, semester.getId(), classId)).get(0);
        Long courseId = course.getId();
        scoreForm.setFullScore(course.getFullScore());
        scoreForm.setExcellentScore(course.getExcellentScore());
        scoreForm.setPassingScore(course.getPassingScore());
        scoreForm.setSubject(subjectMapper.getSubjectById(subjectId));

        List<Student> studentList = studentMapper.getStudentsByClassId(classId);
        for (Student student : studentList) {
            scoreForm.getScores().add(new ScoreInfo(student.getId(), student.getName(), scoreMapper.getScoreByStudentCourseId(student.getId(), courseId)));
        }

        return scoreForm;
    }

    @Override
    @Transactional
    public boolean saveScore(ScoreForm form, Long teacherId) {
        List<Course> courseList = courseMapper.getCourses(new Course(teacherId, form.getSubjectId(), form.getSemesterId(), form.getClassId()));
        Long courseId = courseList.get(0).getId();
        courseMapper.updateScoresById(new Course(courseId, form.getFullScore(), form.getExcellentScore(), form.getPassingScore()));
        for (ScoreInfo info: form.getScores()) {
            scoreMapper.delete(info.getStudentId(), courseId);
            scoreMapper.save(new Score(info.getStudentId(), courseId, info.getScore()));
        }
        return true;
    }

    @Override
    @Transactional
    public List<List<String>> getScoreTable(Long teacherId, Long classId) {
        Long semesterId = getCurrentSemester(teacherId).getId();
        List<Course> courseList = courseMapper.getCourses(new Course(null, null, semesterId, classId));
        List<Student> studentList = studentMapper.getStudentsByClassId(classId);

        List<List<String>> scoreTable = new ArrayList<>();
        List<String> subjectLine = new ArrayList<>();
        subjectLine.add("#");
        subjectLine.add("姓名");
        Iterator<Course> courseIterator = courseList.iterator();
        while(courseIterator.hasNext()) {
            Course course = courseIterator.next();
            if (scoreMapper.getScoreCountByCourseId(course.getId()) > 0) {
                Subject subject = subjectMapper.getSubjectById(course.getSubjectId());
                subjectLine.add(subject.getName());
            } else {
                courseIterator.remove();
            }
        }
        scoreTable.add(subjectLine);

        int count = 1;
        for (Student student : studentList) {
            List<String> studentLine = new ArrayList<>();
            studentLine.add(String.valueOf(count++));
            studentLine.add(student.getName());
            for(Course course : courseList) {
                    BigDecimal score = scoreMapper.getScoreByStudentCourseId(student.getId(), course.getId());
                    studentLine.add(score == null ? null : score.toString());
                }
            scoreTable.add(studentLine);
        }

        return scoreTable;
    }

    @Override
    public List<Subject> getTeachingSubjects(Long teacherId, Long classId) {
        List<Course> teachingCourses = courseMapper.getCourses(new Course(teacherId, null, getCurrentSemester(teacherId).getId(), classId));
        List<Subject> teachingSubject = new ArrayList<>();
        for (Course course : teachingCourses) {
            teachingSubject.add(subjectMapper.getSubjectById(course.getSubjectId()));
        }
        return teachingSubject;
    }

    private Semester getCurrentSemester(Long teacherId) {
        return semesterService.getCurrentSemester(teacherId);
    }

}
