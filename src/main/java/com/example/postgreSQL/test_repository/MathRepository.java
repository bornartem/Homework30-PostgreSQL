package com.example.postgreSQL.test_repository;

import com.example.postgreSQL.test_model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class MathRepository implements QuestionRepository {
    private Set<Question> questions;

    @PostConstruct
    public void init() {
        questions = new HashSet<>(Set.of(
                new Question("mathQuestion 1", "mathAnswer 1"),
                new Question("mathQuestion 2", "mathAnswer 2"),
                new Question("mathQuestion 3", "mathAnswer 3"),
                new Question("mathQuestion 4", "mathAnswer 4")));
    }

    @Override
    public Question addQA(String question, String answer) {
        Question question1 = new Question(question, answer);
        if (!questions.contains(question1)) {
            questions.add(question1);
            return question1;
        }
        return null;
    }

    @Override
    public Question findQA(String question, String answer) {
        Question question1 = new Question(question, answer);
        if (questions.contains(question1)) {
            return question1;
        }
        return null;
    }

    @Override
    public Question removeQA(String question, String answer) {
        Question question1 = new Question(question, answer);
        if (questions.contains(question1)) {
            questions.remove(question1);
            return question1;
        }
        return null;
    }

    @Override
    public Set<Question> getAll() {
        return questions;
    }
}
