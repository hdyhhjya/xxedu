package com.daisy.xxedu.controller.teacher;

import com.daisy.xxedu.entity.Student;
import com.daisy.xxedu.entity.StudentForm;
import com.daisy.xxedu.entity.User;
import com.daisy.xxedu.service.ClassService;
import com.daisy.xxedu.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/teacher")
public class StudentInfoController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;

    @GetMapping("/students")
    public String students(Model model, @AuthenticationPrincipal User user, Long classId) {
        model.addAttribute("students", studentService.getStudents(classId));
        model.addAttribute("username", user.getUsername());
        model.addAttribute("className", classService.getClassName(classId));
        model.addAttribute("classId", classId);
        return "teacher/students";
    }

    @PostMapping("/newStudent")
    public String newStudent(Model model, @AuthenticationPrincipal User user, Long classId, Integer studentCount) {
        model.addAttribute("classId", classId);
        model.addAttribute("className", classService.getClassName(classId));
        model.addAttribute("studentCount", studentCount);
        model.addAttribute("username", user.getUsername());
        return "teacher/newStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(StudentForm form, RedirectAttributes attr) {
        studentService.newStudent(form);
        attr.addAttribute("classId", form.getClassId());
        return "redirect:/teacher/students";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(Long studentId, @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("student", studentService.getStudentById(studentId));
        return "teacher/updateStudent";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(Student student, RedirectAttributes attr) {
        studentService.updateStudent(student);
        attr.addAttribute("classId", student.getClassId());
        return "redirect:/teacher/students";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(Long studentId, RedirectAttributes attr, Long classId) {
        studentService.deleteStudent(studentId);
        attr.addAttribute("classId", classId);
        return "redirect:/teacher/students";
    }
}
