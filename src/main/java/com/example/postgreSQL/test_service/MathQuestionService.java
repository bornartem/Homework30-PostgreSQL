package com.example.postgreSQL.test_service;

import com.example.postgreSQL.test_model.Question;
import com.example.postgreSQL.test_repository.MathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@Qualifier("math")
public class MathQuestionService implements QuestionService {

    private final MathRepository mathRepository;
    @Autowired
    public MathQuestionService(MathRepository mathRepository) {
        this.mathRepository = mathRepository;
    }
    private Random random = new Random();

    public Question addQA(String question, String answer) {
        return mathRepository.addQA(question, answer);
    }

    public Question findQA(String question, String answer) {
        return mathRepository.findQA(question, answer);
    }

    public Question removeQA(String question, String answer) {
        return mathRepository.removeQA(question, answer);
    }
    @Override
    public Set<Question> getAll() {
        return mathRepository.getAll();
    }
    @Override
    public Question getRandomQuestion() {
        List<Question> listQA = mathRepository.getAll().stream().toList();
        int randomNumber = random.nextInt(listQA.size());
        return listQA.get(randomNumber);
    }
}
