package org.example.kb7spring.student.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kb7spring.student.dto.StudentDto;
import org.example.kb7spring.student.dto.StudentSearchDto;
import org.example.kb7spring.student.service.StudentServiceV2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/student/v1")
public class StudentApiControllerV1 {
    private final StudentServiceV2 studentService;

    @GetMapping("/list")
    public List<StudentDto> list() {
        log.info("==================> StudentApiControllerV1, /list");
        return studentService.getStudentList();
    }

    @GetMapping("/search")
    public String search(
            @ModelAttribute StudentSearchDto studentSearchDto,
            Model model
    ) {
        model.addAttribute("studentList", studentService.searchStudentList(studentSearchDto));
        return "/student/search";
    }

    @GetMapping({"/search2", "/search2/{name}", "/search2/{name}/{role}"})
    public List<StudentDto> search2(
            @PathVariable(required = false) String name,
            @PathVariable(required = false) String role
    ) {
        StudentSearchDto searchDto = new StudentSearchDto(name, role);
        return studentService.searchStudentList(searchDto);
    }

    @PostMapping("/add")
    public void add(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
    }
}
