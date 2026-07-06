package org.example.kb7spring.student.repository;

import org.example.kb7spring.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryV2 extends JpaRepository<Student, Long> {
    List<Student> findByNameOrRole(String name, String role);
    @Query("select s " +
            "from Student s " +
            "join fetch s.classroom")
    List<Student> findAllFetchJoin();
}
