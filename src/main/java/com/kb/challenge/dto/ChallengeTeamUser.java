package com.kb.challenge.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChallengeTeamUser {
    private long teamId;
    private long userId;
    private int chPoint;
    private int qzPoint;
    private int chTotal;
}
