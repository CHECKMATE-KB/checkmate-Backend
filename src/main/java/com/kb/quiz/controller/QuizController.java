package com.kb.quiz.controller;

import com.kb.quiz.dto.Quiz;
import com.kb.quiz.service.QuizService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
@Api(value = "QuizController", tags = "퀴즈 정보") // swagger
public class QuizController {
    private final QuizService service;

    @GetMapping("")
    public ResponseEntity<List<Quiz>> getAllQuiz() {
        return ResponseEntity.ok(service.getAllQuizzes());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<Quiz>> getAllQuiz(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(service.getQuizzesByCategory(categoryId));
    }

    @GetMapping("/update-point/{userNo}/{correctCount}")
    public ResponseEntity<Integer> updateUserPoint(@PathVariable Long userNo, @PathVariable int correctCount) {
        int pointsToAdd = correctCount * 10; // 맞춘 문제 개수당 10점
        service.updateUserPoint(userNo, pointsToAdd);
        return ResponseEntity.ok(pointsToAdd);
    }



}
