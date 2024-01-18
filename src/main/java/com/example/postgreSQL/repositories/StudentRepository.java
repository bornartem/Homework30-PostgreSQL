package com.example.postgreSQL.repositories;

import com.example.postgreSQL.model.Faculty;
import com.example.postgreSQL.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentByAgeBetween(int age, int age1);
}
