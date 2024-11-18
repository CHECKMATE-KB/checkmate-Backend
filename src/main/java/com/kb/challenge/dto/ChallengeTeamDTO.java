package com.kb.challenge.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChallengeTeamDTO {
    private List<Integer> ccNo;
    private List<Integer> chLimit;
    private String teamName;
    private String teamStart;
    private String teamEnd;
    private List<String> members;
}
