package com.kb.team.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeamSpend {
    private String userName;
    private int spendTotal;
    private int category;
}
