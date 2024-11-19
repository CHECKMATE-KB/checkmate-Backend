package com.kb.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private Long cardNo;          // 카드 번호 (Primary Key)
    private Long userNo;          // 사용자 번호 (Foreign Key)
    private String cardName;      // 카드 이름
    private String cardNumber;    // 카드 번호
    private String expiryDate;      // 유효기간
    private String cardType;      // 카드 타입
    private String cardCompany;   // 카드 발급사
    private String billingDate;  // 청구일
    private Timestamp registerDate; // 카드 등록일
}
