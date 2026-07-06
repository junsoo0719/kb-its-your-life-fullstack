package org.example.kb7spring.student.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kb7spring.student.dto.StudentDto;
import org.example.kb7spring.student.dto.StudentSearchDto;
import org.example.kb7spring.student.service.StudentService;
import org.example.kb7spring.student.service.StudentServiceV2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/student/v2")
public class StudentControllerV2 {
    private final StudentServiceV2 studentService;

    @GetMapping("")
    public String home() {
        log.info("==================> StudentControllerV2, /");
        return "/student/index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        log.info("==================> StudentControllerV2, /list");
        model.addAttribute("studentList", studentService.getStudentList());
        return "/student/list";
    }

    @GetMapping("/add")
    public String addForm() {
        log.info("===================> StudentControllerV2, /add");
        return "/student/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute StudentDto studentDto) {
        studentService.addStudent(studentDto);
        return "redirect:/student/v1/list";
    }

    @GetMapping("/search")
    public String search(
            @ModelAttribute StudentSearchDto studentSearchDto,
            Model model
    ) {
        model.addAttribute("studentList", studentService.searchStudentList(studentSearchDto));
        return "/student/search";
    }

//    @PostMapping("/add")
//    public String add(
//            @RequestParam("name") String name,
//            @RequestParam String role,
//            @RequestParam(required = false) String specialty,
//            @RequestParam(required = false) String status
//    ) {
//        StudentDto studentDto = new StudentDto(name, role, specialty, status);
//        studentService.addStudent(studentDto);
//        return "redirect:/student/v1/list";
//    }

//    @PostMapping("/add")
//    public String add(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String role = request.getParameter("role");
//        String specialty = request.getParameter("specialty");
//        String status = request.getParameter("status");
//
//        StudentDto studentDto = new StudentDto(name, role, specialty, status);
//        studentService.addStudent(studentDto);
//        return "redirect:/student/v1/list";
//    }

    @GetMapping("/error")
    public void error() {
        throw new RuntimeException("알 수 없는 에러");
    }
}
