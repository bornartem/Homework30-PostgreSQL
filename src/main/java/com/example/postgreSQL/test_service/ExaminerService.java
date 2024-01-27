package com.example.postgreSQL.test_service;

import com.example.postgreSQL.test_model.Question;

import java.util.Set;

public interface ExaminerService {
    Set<Question> getQuestions(int amount);
}
