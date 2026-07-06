package org.example.kb7spring.student.service;

import lombok.RequiredArgsConstructor;
import org.example.kb7spring.student.domain.Student;
import org.example.kb7spring.student.dto.StudentDto;
import org.example.kb7spring.student.dto.StudentSearchDto;
import org.example.kb7spring.student.repository.StudentRepository;
import org.example.kb7spring.student.repository.StudentRepositoryV2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceV2 {
    private final StudentRepositoryV2 studentRepository;

    public List<StudentDto> getStudentList() {
        List<Student> entityList = studentRepository.findAll();
        List<StudentDto> dtoList = new ArrayList<>();

        for (Student student : entityList) {
            StudentDto dto = new StudentDto();
            dto.setName(student.getName());
            dto.setRole(student.getRole());
            dto.setSpecialty(student.getSpecialty());
            dto.setStatus(student.getStatus());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void addStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setRole(studentDto.getRole());
        student.setSpecialty(studentDto.getSpecialty());
        student.setStatus(studentDto.getStatus());
        studentRepository.save(student);
    }

    public List<StudentDto> searchStudentList(StudentSearchDto studentSearchDto) {
        List<Student> entityList = studentRepository.findByNameOrRole(studentSearchDto.getName(), studentSearchDto.getRole());
        List<StudentDto> dtoList = new ArrayList<>();

        for (Student student : entityList) {
            StudentDto dto = new StudentDto();
            dto.setName(student.getName());
            dto.setRole(student.getRole());
            dto.setSpecialty(student.getSpecialty());
            dto.setStatus(student.getStatus());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
