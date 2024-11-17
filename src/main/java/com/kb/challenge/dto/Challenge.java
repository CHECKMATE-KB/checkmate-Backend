package com.kb.challenge.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Challenge {
    private int ccNo;
    private int teamNo;
    private int chLimit;
    private String chStart;
    private String chEnd;
}
