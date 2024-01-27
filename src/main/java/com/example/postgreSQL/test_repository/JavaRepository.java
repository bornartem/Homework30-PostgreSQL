package com.example.postgreSQL.test_repository;

import com.example.postgreSQL.test_model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
@Repository
public class JavaRepository implements QuestionRepository {
    private Set<Question> questions;

    @PostConstruct
    public void init() {
        questions = new HashSet<>(Set.of(
                new Question("Question 1", "Answer 1"),
                new Question("Question 2", "Answer 2"),
                new Question("Question 3", "Answer 3"),
                new Question("Question 4", "Answer 4")));
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
