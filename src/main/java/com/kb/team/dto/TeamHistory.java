package com.kb.team.dto;
import lombok.*;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TeamHistory {
    private int teamNo;
    private int userNo;
    private String userName;
    private int cardNo;
    private List<CardUsage> cardUsages;
}
