package com.example.postgreSQL.test_service;

import com.example.postgreSQL.test_model.Question;
import com.example.postgreSQL.test_repository.JavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("java")
public class JavaQuestionService implements QuestionService {

    private final JavaRepository javaRepository;

    @Autowired
    public JavaQuestionService(JavaRepository javaRepository) {
        this.javaRepository = javaRepository;
    }

    private Random random = new Random();


    public Question addQA(String question, String answer) {
        return javaRepository.addQA(question, answer);
    }

    public Question findQA(String question, String answer) {
        return javaRepository.findQA(question, answer);
    }

    public Question removeQA(String question, String answer) {
        return javaRepository.removeQA(question, answer);
    }

    @Override
    public Set<Question> getAll() {
        return javaRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> listQA = javaRepository.getAll().stream().toList();
        int randomNumber = random.nextInt(listQA.size());
        return listQA.get(randomNumber);
    }
}
