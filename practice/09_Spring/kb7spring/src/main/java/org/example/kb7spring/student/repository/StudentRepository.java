package org.example.kb7spring.student.repository;

import org.example.kb7spring.student.domain.Student;
import org.example.kb7spring.student.dto.StudentSearchDto;

import java.util.List;

public interface StudentRepository {
    public List<Student> findAll();
    public void save(Student student);
    List<Student> search(StudentSearchDto studentSearchDto);
}
