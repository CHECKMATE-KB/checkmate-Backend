package com.kb.quiz.mapper;

import com.kb.quiz.dto.Quiz;

import java.util.List;

public interface QuizMapper {
    public List<Quiz> getAllQuiz();
    public List<Quiz> getCategoryQuiz(int categoryId);
}
