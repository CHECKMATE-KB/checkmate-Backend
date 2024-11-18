package com.kb.quiz.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Quiz {
    private int qzNo;
    private int qzCategory;
    private String qzContent;
    private String qzAnswer;
    private int timeLimit;
}
