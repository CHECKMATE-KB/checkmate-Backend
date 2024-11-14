package com.kb.member.dto;

import java.sql.Date;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String userId;      // 사용자 ID
    private String userPw;      // 비밀번호
    private String userName;    // 사용자 이름
    private String email;       // 이메일
    private Date birth;         // 생년월일 (DATE 타입)
    private String userImg;     // 프로필 이미지
    private String nickname;    // 닉네임
    private long accountNo;     // 계좌 번호

    public User toUser() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .email(email)
                .birth(birth)
                .userImg(userImg)
                .nickname(nickname)
                .accountNo(accountNo)
                .build();
    }
}
