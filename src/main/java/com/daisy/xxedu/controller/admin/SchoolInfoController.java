package com.daisy.xxedu.controller.admin;

import com.daisy.xxedu.entity.School;
import com.daisy.xxedu.entity.User;
import com.daisy.xxedu.service.SchoolService;
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
public class SchoolInfoController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/schools")
    public String schools(Model model, @AuthenticationPrincipal User user, Integer pageNum) {
        if (pageNum == null) pageNum = 1;
        model.addAttribute("username", user.getUsername());
        Map<String, Object> schoolMap = schoolService.getSchools(pageNum);
        model.addAttribute("schools", schoolMap.get("schools"));
        model.addAttribute("pageInfo", schoolMap.get("pageInfo"));
        return "admin/schools";
    }

    @GetMapping("newSchool")
    public String newSchool(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        return "admin/newSchool";
    }

    @PostMapping("/newSchool")
    public String newSchool(School school) {
        schoolService.insertSchool(school);
        return "redirect:/admin/schools";
    }

    @GetMapping("/updateSchool")
    public String updateSchool(Long id, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        School school = schoolService.getSchoolById(id);
        model.addAttribute("school", school);
        return "admin/updateSchool";
    }

    @PostMapping("/updateSchool")
    public String updateSchool(School school) {
        schoolService.updateSchool(school);
        return "redirect:/admin/schools";
    }
}
