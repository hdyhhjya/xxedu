package com.daisy.xxedu.controller.teacher;

import com.daisy.xxedu.entity.Semester;
import com.daisy.xxedu.entity.User;
import com.daisy.xxedu.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class SemesterInfoController {
    @Autowired
    private SemesterService semesterService;

    @GetMapping("/semesters")
    public String semesters(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("semesters", semesterService.getSemestersByUserId(user.getId()));
        return "teacher/semesters";
    }

    @GetMapping("/updateSemester")
    public String updateSemester(Model model, @AuthenticationPrincipal User user, Long semesterId) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("semester", semesterService.getSemesterById(semesterId));
        return "teacher/updateSemester";
    }

    @PostMapping("/updateSemester")
    public String updateSemester(Semester semester, @AuthenticationPrincipal User user) {
        semesterService.updateSemester(semester, user.getId());
        return "redirect:/teacher/semesters";
    }

    @GetMapping("/deleteSemester")
    public String deleteSemester(Long semesterId) {
        semesterService.deleteSemester(semesterId);
        return "redirect:/teacher/semesters";
    }
}
