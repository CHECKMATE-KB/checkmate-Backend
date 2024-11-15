package com.kb.buyhistory.dto;

import java.time.LocalDateTime; // 변경된 타입
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BuyHistoryDTO {

    private Long historyNo;
    private Date historyDate;
    private Long price;
    private String historyCategory;
    private Long cardNo;

}
