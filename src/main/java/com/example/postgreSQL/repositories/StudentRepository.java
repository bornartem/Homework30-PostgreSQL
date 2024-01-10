package com.example.postgreSQL.repositories;

import com.example.postgreSQL.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
