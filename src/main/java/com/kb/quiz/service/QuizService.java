package com.kb.quiz.service;

import com.kb.member.mapper.UserMapper;
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
    private final UserMapper userMapper;

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

    public void updateUserPoint(Long userNo, int pointsToAdd) {
        // 현재 포인트를 가져와서 업데이트
        Integer currentPoint = userMapper.getUserPoint(userNo);
        if (currentPoint == null) {
            currentPoint = 0; // 기본값 설정
        }
        int newPoint = currentPoint + pointsToAdd;
        userMapper.updateUserPoint(userNo, newPoint);
    }
}
