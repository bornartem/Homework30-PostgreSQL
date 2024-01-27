package com.example.postgreSQL.test_service;

import com.example.postgreSQL.test_exception.AmountException;
import com.example.postgreSQL.test_model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    @Autowired
    public ExaminerServiceImpl(@Qualifier("java") QuestionService javaQuestionService,
                               @Qualifier("math") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        if (amount > javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) {
            throw new AmountException("Wrong response");
        }
        Set<Question> newQA = new HashSet<>();
        while (newQA.size() != amount) {
            newQA.add(javaQuestionService.getRandomQuestion());
            newQA.add(mathQuestionService.getRandomQuestion());
        }
        return newQA;
    }
}
