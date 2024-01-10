package com.example.postgreSQL.repositories;

import com.example.postgreSQL.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
