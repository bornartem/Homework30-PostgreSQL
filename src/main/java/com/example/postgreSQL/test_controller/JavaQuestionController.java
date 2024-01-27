package com.example.postgreSQL.test_controller;

import com.example.postgreSQL.test_model.Question;
import com.example.postgreSQL.test_service.JavaQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    @PostMapping("/add")
    public ResponseEntity<Question> addQA(@RequestParam String question, @RequestParam String answer) {
        Question question1 = javaQuestionService.addQA(question, answer);
        return ResponseEntity.ok(question1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Question> deleteQA(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.removeQA(question, answer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<Question>> getAll() {
        return ResponseEntity.ok(javaQuestionService.getAll());
    }
}
