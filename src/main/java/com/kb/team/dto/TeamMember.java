package com.kb.team.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeamMember {
    private int teamNo;
    private int userNo;
    private String userName;
    private String userImg;
    private int challengePoint;
    private int qzPoint;
    private int totalPoint;
}
