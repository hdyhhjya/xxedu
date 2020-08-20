package com.daisy.xxedu.controller.teacher;

import com.daisy.xxedu.entity.ClassInfo;
import com.daisy.xxedu.entity.User;
import com.daisy.xxedu.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/teacher")
public class ClassInfoController {
    @Autowired
    private ClassService classService;

    @GetMapping("/classes")
    public String classView(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        Map<Long, String> classes = classService.getClasses(user.getTeacherId());
        model.addAttribute("classes", classes);
        return "teacher/classes";
    }

    @GetMapping("/newClass")
    public String newClass(Model model,  @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        return "teacher/newClass";
    }

    @PostMapping("/newClass")
    public String newClass(@AuthenticationPrincipal User user, ClassInfo form) {
        classService.insertClassInfo(form, user.getTeacherId());
        return "redirect:/teacher/classes";
    }

    @GetMapping("/deleteClass")
    public String deleteClass(Long classId) {
        classService.deleteClass(classId);
        return "redirect:/teacher/classes";
    }
}
