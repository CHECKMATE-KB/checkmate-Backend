package com.kb.team.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TeamHistory {
    private int teamNo;
    private int userNo;
    private String userName;
    private int cardNo;
    private List<CardUsage> cardUsages;
}
