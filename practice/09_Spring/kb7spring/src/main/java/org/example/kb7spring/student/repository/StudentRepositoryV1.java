package org.example.kb7spring.student.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kb7spring.student.domain.Student;
import org.example.kb7spring.student.dto.StudentSearchDto;
import org.example.kb7spring.student.mapper.StudentMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Slf4j
@Repository
@RequiredArgsConstructor
public class StudentRepositoryV1 implements StudentRepository {
    private final StudentMapper studentMapper;

    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    public void save(Student student) {
        studentMapper.insert(student);
        log.info("저장 된 교육생 정보: {}", student);
    }

    public List<Student> search(StudentSearchDto studentSearchDto) {
        return studentMapper.search(studentSearchDto);
    }

}
