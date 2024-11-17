package com.kb.team.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CardUsage {
    private int historyNo;
    private String historyDate;
    private int price;
    private String historyCategory;
}
