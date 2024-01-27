package com.example.postgreSQL.test_controller;

import com.example.postgreSQL.test_model.Question;
import com.example.postgreSQL.test_service.MathQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/exam/math")
@RequiredArgsConstructor
public class MathQuestionController {

    private final MathQuestionService mathQuestionService;

    @PostMapping("/add")
    public ResponseEntity<Question> addQA(@RequestParam String question, @RequestParam String answer) {
        Question question1 = mathQuestionService.addQA(question, answer);
        return ResponseEntity.ok(question1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Question> deleteQA(@RequestParam String question, @RequestParam String answer) {
        mathQuestionService.removeQA(question, answer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<Question>> getAll() {
        return ResponseEntity.ok(mathQuestionService.getAll());
    }
}
