package org.example.kb7spring.student.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kb7spring.student.dto.StudentDto;
import org.example.kb7spring.student.dto.StudentSearchDto;
import org.example.kb7spring.student.service.StudentServiceV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/student/v2")
public class StudentApiControllerV2 {
    private final StudentServiceV2 studentService;

    @GetMapping("/list")
    public ResponseEntity<List<StudentDto>> list() {
        log.info("==================> StudentApiControllerV2, /list");
        List<StudentDto> list = studentService.getStudentList();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/test", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("요청을 처리할 수 없습니다");
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
