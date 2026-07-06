package org.example.kb7spring.student.service;

import org.example.kb7spring.student.domain.Student;
import org.example.kb7spring.student.dto.StudentDto;
import org.example.kb7spring.student.dto.StudentSearchDto;

import java.util.List;

public interface StudentService {
    public List<StudentDto> getStudentList();
    public void addStudent(StudentDto studentDto);
    public List<StudentDto> searchStudentList(StudentSearchDto studentSearchDto);
}
