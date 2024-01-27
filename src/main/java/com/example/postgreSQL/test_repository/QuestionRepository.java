package com.example.postgreSQL.test_repository;

import com.example.postgreSQL.test_model.Question;

import java.util.Set;

public interface QuestionRepository {
    Question addQA(String question, String answer);

    Question findQA(String question, String answer);

    Question removeQA(String question, String answer);

    Set<Question> getAll();
}
