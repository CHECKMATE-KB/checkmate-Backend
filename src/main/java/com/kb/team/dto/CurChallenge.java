package com.kb.team.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CurChallenge {
    private int teamNo;
    private int ccNo;
    private String ccName;
    private int chLimit;
    private String chStart;
    private String chEnd;
}
