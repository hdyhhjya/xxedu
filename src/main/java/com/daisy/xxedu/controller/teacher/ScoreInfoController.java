package com.daisy.xxedu.controller.teacher;

import com.daisy.xxedu.entity.ScoreForm;
import com.daisy.xxedu.entity.User;
import com.daisy.xxedu.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/teacher")
public class ScoreInfoController {
    @Autowired
    private ScoreService scoreService;


    @GetMapping("/scores")
    public String grades(Model model, @AuthenticationPrincipal User user, Long classId) {
        model.addAttribute("teachingSubjects", scoreService.getTeachingSubjects(user.getTeacherId(), classId));
        model.addAttribute("scoreTable", scoreService.getScoreTable(user.getTeacherId(), classId));
        model.addAttribute("classId", classId);
        model.addAttribute("username", user.getUsername());
        return "teacher/scores";
    }

    @GetMapping("/updateScore")
    public String update(Model model, @AuthenticationPrincipal User user, Long classId, Integer subjectId) {
        model.addAttribute("scoreForm", scoreService.getScoreForm(user.getTeacherId(), classId, subjectId));
        model.addAttribute("username", user.getUsername());
        return "teacher/updateScore";
    }

    @PostMapping("/updateScore")
    public String update(ScoreForm form, @AuthenticationPrincipal User user, RedirectAttributes attr) {
        scoreService.saveScore(form, user.getTeacherId());
        attr.addAttribute("classId", form.getClassId());
        return "redirect:/teacher/scores";
    }
}
