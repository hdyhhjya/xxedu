package com.daisy.xxedu.controller;

import com.daisy.xxedu.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        String userRole = user.getAuthorities().toString();
        if (userRole.contains("ADMIN")) {
            return "redirect:/admin/users";
        } else {
            return "redirect:/teacher/courses";
        }
    }
}
