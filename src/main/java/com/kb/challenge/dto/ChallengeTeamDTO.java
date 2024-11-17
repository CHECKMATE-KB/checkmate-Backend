package com.kb.challenge.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChallengeTeamDTO {
    private int ccNo;
    private int chLimit;
    private String teamName;
    private String teamStart;
    private String teamEnd;
    private List<Integer> members;
}
