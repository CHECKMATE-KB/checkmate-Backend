package com.kb.team.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TeamRank {
    private int teamNo;
    private String userName;
    private int challengePoint;
    private int qzPoint;
    private int totalPoint;

}
