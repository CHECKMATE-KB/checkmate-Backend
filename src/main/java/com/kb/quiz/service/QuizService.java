package com.kb.quiz.service;

import com.kb.quiz.dto.Quiz;
import com.kb.quiz.mapper.QuizMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final QuizMapper mapper;

    public List<Quiz> getAllQuizzes(){
        List<Quiz> quizzes=mapper.getAllQuiz();
        if(quizzes==null){
            throw new NoSuchElementException();
        }
        return quizzes;
    }

    public List<Quiz> getQuizzesByCategory(int categoryId){
        List<Quiz> quizzes=mapper.getCategoryQuiz(categoryId);
        if(quizzes==null){
            throw new NoSuchElementException();
        }
        return quizzes;
    }
}
