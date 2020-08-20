package com.daisy.xxedu.controller.admin;

import com.daisy.xxedu.entity.User;
import com.daisy.xxedu.entity.UserInformationForm;
import com.daisy.xxedu.service.SchoolService;
import com.daisy.xxedu.service.UserService;
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
@RequestMapping("/admin")
public class UserInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/users")
    public String readUsers(Model model, @AuthenticationPrincipal User user, Integer pageNum) {
        if(pageNum == null) pageNum = 1;
        model.addAttribute("username", user.getUsername());
        Map<String, Object> userMap = userService.getUsers(pageNum);
        model.addAttribute("users", userMap.get("users"));
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageInfo",userMap.get("pageInfo"));
        return "admin/users";
    }

    @GetMapping("/register")
    public String registrationForm(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("schools", schoolService.getSchools(0).get("schools"));
        return "admin/newUser";
    }

    @PostMapping("/register")
    public String processRegistration(UserInformationForm form) {
        userService.register(form);
        return "redirect:/admin/users";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(Long id, Model model) {
        if(userService.resetPassword(id)) {
            model.addAttribute("resetSuccess", true);
        } else {
            model.addAttribute("resetSuccess", false);
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
