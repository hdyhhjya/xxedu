package com.daisy.xxedu.controller.teacher;

import com.daisy.xxedu.entity.CourseForm;
import com.daisy.xxedu.entity.Semester;
import com.daisy.xxedu.entity.User;
import com.daisy.xxedu.service.CourseService;
import com.daisy.xxedu.service.SemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/teacher")
public class CourseInfoController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SemesterService semesterService;

    @GetMapping("/courses")
    public String courses(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        Semester semester = semesterService.getCurrentSemester(user.getTeacherId());
        model.addAttribute("courses", courseService.getCurrentCourses(user.getTeacherId(), semester.getId()));
        return "teacher/courses";
    }

    @GetMapping("/updateCourse")
    public String updateCourser(Model model, @AuthenticationPrincipal User user, Long classId) {
        Semester semester = semesterService.getCurrentSemester(user.getTeacherId());
        if (semester == null) {
            return "teacher/updateSemester";
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("classId", classId);
        model.addAttribute("subjects", courseService.getSubjects());
        model.addAttribute("semester", semester);
        model.addAttribute("courseList", courseService.getUpdatingCourses(user.getTeacherId(), semester.getId(), classId));
        return "teacher/updateCourse";
    }

    @PostMapping("/updateCourse")
    public String updateCourse(CourseForm form, @AuthenticationPrincipal User user) {
        form.setTeacherId(user.getTeacherId());
        courseService.updateCourses(form);
        return "redirect:/teacher/courses";
    }
}
