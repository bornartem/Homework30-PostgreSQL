package com.example.postgreSQL.test_controller;

import com.example.postgreSQL.test_model.Question;
import com.example.postgreSQL.test_service.ExaminerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExaminerController {

    private final ExaminerService examinerService;
    @GetMapping
    public Set<Question> getRandomQuestion(@RequestParam int amount) {
        return examinerService.getQuestions(amount);
    }
}
